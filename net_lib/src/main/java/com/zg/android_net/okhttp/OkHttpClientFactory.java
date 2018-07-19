package com.zg.android_net.okhttp;

import com.zg.android_net.bean.NetConfig;
import com.zg.android_net.util.BaseUtil;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * OkHttpClient 生成器
 */
public class OkHttpClientFactory {
    public static OkHttpClient createHttps() {
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {

            private final PersistentCookieStore cookieStore = new PersistentCookieStore(BaseUtil.getContext());

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                if (cookies != null && cookies.size() > 0) {
                    for (Cookie item : cookies) {
                        cookieStore.add(url, item);
                    }
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url);
                return cookies;
            }
        }).addInterceptor(NetConfig.getInterceptor())
                .connectTimeout(NetConfig.getConnectionTimeout(), TimeUnit.SECONDS)// 连接超时
                .readTimeout(NetConfig.getReadTimeout(), TimeUnit.SECONDS)// 读取超时
                .sslSocketFactory(sslContext.getSocketFactory())
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .build();
        return okHttpClient;
    }

    public static OkHttpClient createHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    private final PersistentCookieStore cookieStore = new PersistentCookieStore(
                            BaseUtil.getContext());

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        if (cookies != null && cookies.size() > 0) {
                            for (Cookie item : cookies) {
                                cookieStore.add(url, item);
                            }
                        }
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url);
                        return cookies;
                    }
                })
                .addInterceptor(NetConfig.getInterceptor())
                .connectTimeout(NetConfig.getConnectionTimeout(), TimeUnit.SECONDS)
                .readTimeout(NetConfig.getReadTimeout(), TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

}
