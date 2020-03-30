package com.live.base.net.interceptor;

import java.io.IOException;
import java.util.Set;
import java.util.WeakHashMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加公共请求头 和 cookies
 */
public class HeaderInterceptor implements Interceptor {
    private WeakHashMap<String, String> mHeaders;

    public HeaderInterceptor(WeakHashMap<String, String> headers) {
        this.mHeaders = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request()
                .newBuilder();
        HttpUrl.Builder newUrlBuilder = chain.request().url().newBuilder();
//        newUrlBuilder.addQueryParameter("deviceid", deviceId);
        if (mHeaders != null && mHeaders.size() > 0) {
            // TODO: 2018/12/14  添加公共请求头
            Set<String> keys = mHeaders.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, mHeaders.get(headerKey)).build();
            }
        }
//        builder.addHeader("",)
        return chain.proceed(builder.build());

    }
}