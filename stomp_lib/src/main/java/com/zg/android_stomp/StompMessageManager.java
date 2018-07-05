package com.zg.android_stomp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.zg.android_stomp.stomp.StompClientWrapper;
import com.zg.android_stomp.stomp.StompMessageParser;
import com.zg.android_stomp.subscriber.DefaultSubscriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

/**
 * Created by wangqi on 2017/4/14.
 */

public class StompMessageManager {

    public interface OnMessageListener<T> {
        void onMessage(T message);
    }

    private static short MESSAGE_CALLBACK_INTERVAL = 1000;
    private static final String TAG = StompMessageManager.class.getSimpleName();
    private static Map<String, Set<OnMessageListener>> msgListeners;
    private static Map<String, StompMessageParser> msgParsers;
    private static Map<String, Messages> msgCache;
    private static Subscription messageLooperSubscription;
    private static Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    static {
        //使用HashMap而不是ArrayMap是为了防止多线程时出现的一个系统级的BUG：https://issuetracker.google.com/issues/37114373
        msgListeners = new HashMap<>();
        msgCache = new HashMap<>();
        msgParsers = new HashMap<>();
    }

    public static void setWebSocketAddress(String webSocketAddress) {
        StompClientWrapper.setWebSocketAddress(webSocketAddress);
    }

    public static void connect() {
        StompClientWrapper.connect();
    }

    private static void validateMessageLooper() {
        if (msgListeners.keySet().isEmpty() && messageLooperSubscription != null) {
            messageLooperSubscription.unsubscribe();
            return;
        }
        messageLooperSubscription = Observable.interval(0L, MESSAGE_CALLBACK_INTERVAL, TimeUnit.MILLISECONDS).subscribe(new DefaultSubscriber<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                for (String topic : new HashSet<>(msgCache.keySet())) {
                    Messages messages = msgCache.get(topic);
                    if (messages == null || messages.isEmpty()) {
                        continue;
                    }
                    StompMessageParser parser = msgParsers.get(topic);
                    for (OnMessageListener l : msgListeners.get(topic)) {
                        List<String> callbackMessages = new ArrayList<>(messages.messages);
                        messages.messages.clear();
                        for (String message : callbackMessages) {
                            if (parser == null) {
                                callbackListener(l, message);
                            } else {
                                Object parsedMessage = parser.onParseMessage(topic, message);
                                callbackListener(l, parsedMessage);
                            }
                        }
                    }
                }
            }

            private void callbackListener(final OnMessageListener l, final Object message) {
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        l.onMessage(message);
                        Log.i(TAG, "StompMessageManager.callbackListener() " + message.toString());
                    }
                });
            }
        });
    }

    public static void addMessageListener(String topic, OnMessageListener l) {
        addMessageListener(topic, null, l);
    }

    /**
     * @param topic
     * @param parser
     * @param l
     */
    public static void addMessageListener(String topic, StompMessageParser parser, OnMessageListener l) {
        addMessageListener(topic, true, parser, l);
    }

    /**
     * @param topic
     * @param forceCallbackAllMessages 是否缓存并回调所有消息. if false, 只在一秒轮询后回调最新一条消息。
     * @param parser
     * @param l
     */
    public static void addMessageListener(String topic, boolean forceCallbackAllMessages, StompMessageParser parser, OnMessageListener l) {
        if (l == null || TextUtils.isEmpty(topic)) {
            throw new NullPointerException("topic and listener must not be null");
        }
        validateMessageLooper();
        msgParsers.put(topic, parser);
        putListenerToCache(topic, l);
        initMessageCache(topic, forceCallbackAllMessages);
        startReceiveMessage(topic);
    }

    private static void putListenerToCache(String topic, OnMessageListener l) {
        Set<OnMessageListener> listeners = msgListeners.get(topic);
        if (listeners == null) {
            listeners = new HashSet<>();
            msgListeners.put(topic, listeners);
        }
        listeners.add(l);
    }

    public static void removeMessageListener(String topic, OnMessageListener listener) {
        if (listener == null || topic == null) {
            return;
        }
        Set<OnMessageListener> listeners = msgListeners.get(topic);
        if (listeners != null) {
            listeners.remove(listener);
        }
        if (!isHasListeners(topic)) {
            StompClientWrapper.unsubscribe(topic);
            msgCache.remove(topic);
            msgListeners.remove(topic);
            msgParsers.remove(topic);
        }
        validateMessageLooper();
    }

    public static void sendMessage(String topic, String msg) {
        StompClientWrapper.send(topic, msg);
    }

    private static void startReceiveMessage(final String topic) {
        //StompClientWrapper 已经保证了同一个topic只会被注册一次
        StompClientWrapper.subscribe(topic, new StompClientWrapper.OnMessageListener() {
            @Override
            public void onMessage(String msg) {
                if (!TextUtils.isEmpty(msg)) {
                    putMessageToCache(topic, msg.trim());
                }
            }
        });
    }

    private static void putMessageToCache(String topic, String message) {
        Messages messages = msgCache.get(topic);
        if (messages == null) {
            return;
        }
        messages.addMessage(message);
    }

    private static void initMessageCache(String topic, boolean forceCallbackAllMessages) {
        if (msgCache.get(topic) != null) {
            return;
        }
        msgCache.put(topic, new Messages(forceCallbackAllMessages));
    }

    private static boolean isHasListeners(String topic) {
        return msgListeners != null && msgListeners.get(topic) != null && msgListeners.get(topic).size() > 0;
    }

    public static <T> List<T> parseListMessage(String msg, Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType ct = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        try {
            return (List<T>) mapper.readValue(msg, ct);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return new ArrayList<>();
    }

    public static <T> T parseMessage(String msg, Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return (T) mapper.readValue(msg, clazz);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    private static class Messages {
        boolean forceCallbackAllMessages = false;
        CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<String>() {
            @Override
            public boolean add(String o) {
                if (o == null || contains(o)) {
                    return false;
                }
                return super.add(o);
            }
        };

        Messages(boolean forceCallbackAllMessages) {
            this.forceCallbackAllMessages = forceCallbackAllMessages;
        }

        void addMessage(String message) {
            if (TextUtils.isEmpty(message)) {
                return;
            }
            if (!forceCallbackAllMessages) {
                messages.clear();
            }
            messages.add(message.trim());
        }

        boolean isEmpty() {
            return messages == null || messages.isEmpty();
        }
    }
}