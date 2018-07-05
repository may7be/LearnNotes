package com.zg.android_net.bean;

import android.util.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZhaoKeqiang on 2017/9/1.
 * TODO:
 */

class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "close")
                .addHeader("Accept-Encoding", "gzip, deflate")
                //.addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", NetConfig.getAuthorization())
                .addHeader("cookie", String.format("pad_code=%s", NetConfig.getDeviceId()))
                .build();
        //long start = System.currentTimeMillis();
        Response response = chain.proceed(request);
        //LogUtil.p(start, AuthManager.getAuthorization() + ", OkHttp: " + request.url().toString());
        Log.i("OkHttpClient", NetConfig.getAuthorization() + ", OkHttp: " + request.url().toString());
        return response;
    }
}
