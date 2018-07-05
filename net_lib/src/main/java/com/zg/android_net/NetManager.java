package com.zg.android_net;

import com.zg.android_net.bean.NetConfig;
import com.zg.android_net.retrofit.RetrofitHandler;
import okhttp3.Interceptor;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ZhaoKeqiang on 2017/9/1.
 * TODO:网络请求工具类
 * 1.setLoginClass
 * 2.init
 * 3.getService
 * 注意:可以设置http认证,需要适当的时候setAuthorization。智能工厂是在登陆成功后设置。
 */

public class NetManager {
    /**
     * 初始化https
     *
     * @param baseUrl
     */
    public static void init(String baseUrl) {
        RetrofitHandler.init(baseUrl);
    }

    public static void init(String baseUrl, boolean isHttps) {
        RetrofitHandler.init(baseUrl, isHttps);
    }

    /**
     * 实例化service
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> service) {
        return RetrofitHandler.getService(service);
    }

    /**
     * 认证信息:token等
     * 默认是"",需要的时候自己set即可
     *
     * @param authorization
     */
    public static void setAuthorization(String authorization) {
        NetConfig.setAuthorization(authorization);
    }

    /**
     * 连接超时。
     * 默认是30s
     *
     * @param connectionTimeout
     */
    public static void setConnectionTimeOut(int connectionTimeout) {
        NetConfig.setConnectionTimeout(connectionTimeout);
    }

    /**
     * 读取超时。
     * 默认是10s
     *
     * @param readTimeOut
     */
    public static void setReadTimeOut(int readTimeOut) {
        NetConfig.setReadTimeout(readTimeOut);
    }

    /**
     * 网络拦截器
     * 有默认实现 new MyInterceptor()
     *
     * @param interceptor
     */
    public static void setInterceptor(Interceptor interceptor) {
        NetConfig.setInterceptor(interceptor);
    }

    /**
     * 切换器
     * 有默认实现 new ThreadTransformer()
     *
     * @param transformer
     */
    public static void setTrnasformer(Observable.Transformer transformer) {
        NetConfig.setTransformer(transformer);
    }

    public static void setLoginClass(Class loginClass) {
        NetConfig.setLoginClass(loginClass);
    }

    /**
     * 服务错误拦截器
     * 有默认实现 new ServerErrorInterceptor();
     *
     * @param serverErrorInterceptor
     */
    public static void setServerErrorInterceptor(Func1 serverErrorInterceptor) {
        NetConfig.setServerErrorInterceptor(serverErrorInterceptor);
    }

}
