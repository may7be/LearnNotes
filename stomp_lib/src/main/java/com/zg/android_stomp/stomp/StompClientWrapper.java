package com.zg.android_stomp.stomp;

import android.text.TextUtils;
import android.util.Log;

import org.java_websocket.WebSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class StompClientWrapper {

    private static Subscription lifecycleSubscription;

    public interface OnMessageListener {
        void onMessage(String msg);
    }

    private static final String TAG = StompClientWrapper.class.getSimpleName();
    private static final String HEADER_KEY = "TOKEN";
    private static final String HEADER_VALUE = "123";
    private static String URL = "";
    private static Map<String, Subscription> topicSubscriptionMapping = new ConcurrentHashMap<>();
    private static Map<String, OnMessageListener> topicCache = new ConcurrentHashMap<>();
    private static StompClient stompClient;
    private static boolean isConnecting = false;
    private static Map<String, String> header;

    static {
        header = new HashMap<>();
        header.put(HEADER_KEY, HEADER_VALUE);
    }

    private StompClientWrapper() {
    }

    private synchronized static void innerConnect() {
        if (isConnecting || stompClient.isConnected()) {
            return;
        }
        Log.d(TAG, "StompClient connecting...");
        isConnecting = true;
        clearAllTopicSubscription();
        if (lifecycleSubscription != null) lifecycleSubscription.unsubscribe();
        lifecycleSubscription = stompClient.lifecycle().subscribe(new Subscriber<LifecycleEvent>() {
            @Override
            public void onCompleted() {
                isConnecting = false;
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
                isConnecting = false;
            }

            @Override
            public void onNext(LifecycleEvent lifecycleEvent) {
                isConnecting = false;
                switch (lifecycleEvent.getType()) {
                    case OPENED:
                        Log.d(TAG, "StompClientWrapper Opened");
                        if (topicCache.size() > 0) {
                            for (String topic : topicCache.keySet()) {
                                subscribe(topic, topicCache.get(topic));
                            }
                            topicCache.clear();
                        }
                        break;
                    case CLOSED:
                        Log.d(TAG, "StompClientWrapper Closed");
                        connect();
                        break;
                    case ERROR:
                        Log.d(TAG, "StompClientWrapper Error");
                        connect();
                        break;
                }
            }
        });
        stompClient.connect();
    }

    private static Timer reconnectTimer = new Timer();

    public static void setWebSocketAddress(String webSocketAddress) {
        URL = webSocketAddress;
        stompClient = Stomp.over(WebSocket.class, URL, header);
    }
    public static void connect() {
        if (reconnectTimer != null) {
            reconnectTimer.cancel();
        }
        reconnectTimer = new Timer();
        reconnectTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (stompClient != null && stompClient.isConnected()) {
                    reconnectTimer.cancel();
                } else {
                    Log.d(TAG, "StompClientWrapper connect to: " + URL);
                    //新线程运行重连，防止占用主线程时间过长导致界面无响应
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            innerConnect();
                        }
                    }).start();
                }
            }
        }, 0, 5000);
    }

    public synchronized static void subscribe(String topic, final OnMessageListener listener) {
        topic = topic.replaceAll("//", "/").trim();
        if (!stompClient.isConnected()) {
            topicCache.put(topic, listener);
            connect();
            return;
        }
        Log.i(TAG, "StompClientWrapper subscribe: " + topic);
        if (topicSubscriptionMapping.get(topic) != null) {
            return;
        }
        Subscription subscription = stompClient.topic(topic).onBackpressureBuffer(5000)
                .observeOn(Schedulers.io()).subscribe(new Subscriber<StompMessage>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(StompMessage stompMessage) {
                        if (stompMessage == null || TextUtils.isEmpty(stompMessage.getPayload())) {
                            return;
                        }
                        String message = stompMessage.getPayload().trim();
                        Log.i(TAG, ": StompClientWrapper message received: " + message);
                        listener.onMessage(message);
                    }
                });
        topicSubscriptionMapping.put(topic, subscription);
    }

    public static void unsubscribe(String topic) {
        if (topicSubscriptionMapping != null) {
            Subscription subscription = topicSubscriptionMapping.get(topic);
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
                topicSubscriptionMapping.remove(topic);
            }
        }
    }

    public static void send(String destination, String message) {
        stompClient.send("/app/" + destination, message);
    }

    private static void clearAllTopicSubscription() {
        for (String topic : topicSubscriptionMapping.keySet()) {
            unsubscribe(topic);
        }
        topicSubscriptionMapping.clear();
    }
}
