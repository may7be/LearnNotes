package com.zg.android_net.bean;

import android.provider.Settings;
import com.zg.android_net.util.BaseUtil;
import okhttp3.Interceptor;
import rx.Observable;
import rx.functions.Func1;

public class NetConfig {
    private static String DEVICE_ID = Settings.Secure.getString(BaseUtil.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    private static String authorization = "";
    private static int connectionTimeout = 30;
    private static int readTimeout = 10;
    private static Class loginClass;
    private static Observable.Transformer transformer;
    private static Func1 serverErrorInterceptor;
    private static Interceptor interceptor;
    static {
        transformer = new ThreadTransformer<>();
//        serverErrorInterceptor = new ServerErrorInterceptor<>();
        interceptor = new MyInterceptor();
    }

    public static String getDeviceId() {
        return DEVICE_ID;
    }

    public static void setDeviceId(String deviceId) {
        DEVICE_ID = deviceId;
    }

    public static String getAuthorization() {
        return authorization;
    }

    public static void setAuthorization(String authorization) {
        NetConfig.authorization = authorization;
    }

    public static int getConnectionTimeout() {
        return connectionTimeout;
    }

    public static void setConnectionTimeout(int connectionTimeout) {
        NetConfig.connectionTimeout = connectionTimeout;
    }

    public static int getReadTimeout() {
        return readTimeout;
    }

    public static void setReadTimeout(int readTimeout) {
        NetConfig.readTimeout = readTimeout;
    }

    public static Class getLoginClass() {
        return loginClass;
    }

    public static void setLoginClass(Class loginClass) {
        NetConfig.loginClass = loginClass;
    }

    public static Observable.Transformer getTransformer() {
        return transformer;
    }

    public static void setTransformer(Observable.Transformer transformer) {
        NetConfig.transformer = transformer;
    }

    public static Func1 getServerErrorInterceptor() {
        return serverErrorInterceptor;
    }

    public static void setServerErrorInterceptor(Func1 serverErrorInterceptor) {
        NetConfig.serverErrorInterceptor = serverErrorInterceptor;
    }

    public static Interceptor getInterceptor() {
        return interceptor;
    }

    public static void setInterceptor(Interceptor interceptor) {
        NetConfig.interceptor = interceptor;
    }
}
