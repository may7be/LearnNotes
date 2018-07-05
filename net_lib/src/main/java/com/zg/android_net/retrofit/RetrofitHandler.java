package com.zg.android_net.retrofit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zg.android_net.bean.NetConfig;
import com.zg.android_net.okhttp.OkHttpClientFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

public class RetrofitHandler {

    private static Retrofit retrofit;
    private static ObjectMapper objectMapper;
    private static Map<Class<?>, Object> serviceCache = new HashMap<>();
    private static OkHttpClient client;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        client = OkHttpClientFactory.createHttps();
    }

    public static void init(String baseUrl, boolean isHttps) {
        if (isHttps) {
            client = OkHttpClientFactory.createHttps();
        } else {
            client = OkHttpClientFactory.createHttp();
        }
       init(baseUrl);
    }
    public static void init(String baseUrl) {
        if (retrofit == null) {
            synchronized (RetrofitHandler.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                            .build();
                }
            }
        }
    }

    private RetrofitHandler() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getService(final Class<T> service) {
        T result = (T) serviceCache.get(service);
        if (result == null) {
            result = (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                    new InvocationHandler() {
                        T t = (T) retrofit.create(service);

                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (NetConfig.getServerErrorInterceptor() == null) {
                                return ((Observable) Proxy.getInvocationHandler(t).invoke(t, method, args))
                                        .compose(NetConfig.getTransformer());
                            } else {
                                return ((Observable) Proxy.getInvocationHandler(t).invoke(t, method, args))
                                        .compose(NetConfig.getTransformer())
                                        .flatMap(NetConfig.getServerErrorInterceptor());
                            }
                        }
                    });
            serviceCache.put(service, result);
        }
        return result;
    }

}
