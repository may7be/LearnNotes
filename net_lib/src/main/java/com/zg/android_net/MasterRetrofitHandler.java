package com.zg.android_net;

import android.os.Handler;
import android.os.Looper;
import android.util.Config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zg.android_net.bean.JsonResult;
import com.zg.android_net.okhttp.OkHttpClientFactory;
import com.zg.android_net.retrofit.RetrofitException;
import com.zg.android_net.retrofit.RetrofitHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/19
 * TODO:
 */
public class MasterRetrofitHandler {
    private static ObjectMapper sObjectMapper;
    private static Retrofit retrofit;
    private static Map<Class<?>, Object> serviceCache = new HashMap<>();

    static {
        sObjectMapper = new ObjectMapper();
        sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private MasterRetrofitHandler() {
    }

    public static void init(String baseUrl) {
        if (retrofit == null) {
            synchronized (RetrofitHandler.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(OkHttpClientFactory.createHttp())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(JacksonConverterFactory.create(sObjectMapper))
                            .build();
                }
            }
        }
    }

    private static class ThreadTransformer<T> implements Observable.Transformer<T, T> {
        @Override
        public Observable<T> call(Observable<T> tObservable) {
            return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }

    private static class ServerErrorInterceptor<T> implements Func1<T, Observable<T>> {
        @Override
        public Observable<T> call(T t) {
            if (t == null) {
                return Observable.error(new Throwable("Parse response failed"));
            }
            if (t instanceof JsonResult) {
                JsonResult jsonResult = (JsonResult) t;
                int code = jsonResult.getCode();
                if (code == -3) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            /*if (!(ActivityManager.getCurrentActivity() instanceof NewLoginActivity) && !(ActivityManager.getCurrentActivity() instanceof EditServerActivity)) {
                                ActivityManager.closeAllActivity();
                                Intent intent = new Intent(App.getContext(), NewLoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                App.getContext().startActivity(intent);
                            }*/
                        }
                    });
                    return Observable.error(new RetrofitException("验证错误：" + jsonResult.getErrorMsg()));
                } else if (code < 0) {
                    String debugInfo = Config.DEBUG ? "请求失败: Code:" + code + " " : "";
                    return Observable.error(new RetrofitException(debugInfo + "信息: " + jsonResult.getErrorMsg()));
                }
            }
            return Observable.just(t);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getService(final Class<T> service) {
        T result = (T) serviceCache.get(service);
        if (result == null) {
            result = (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
                T t = (T) retrofit.create(service);

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return ((Observable) Proxy.getInvocationHandler(t).invoke(t, method, args))
                            .compose(new MasterRetrofitHandler.ThreadTransformer<T>())
                            .flatMap(new MasterRetrofitHandler.ServerErrorInterceptor<T>());
                }
            });
            serviceCache.put(service, result);
        }
        return result;
    }
}
