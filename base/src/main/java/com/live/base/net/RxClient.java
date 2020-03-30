package com.live.base.net;


import com.live.base.net.callback.RxCallBack;
import com.live.base.net.http.IHttpRequest;
import com.live.base.net.http.OkhttpRequest;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

public final class RxClient {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private final WeakHashMap<String, String> HEADER_PARAMS = new WeakHashMap<>();
    private final RequestBody BODY;
    private final File FILE;
    private final String BASEURL;
    private final int CONNECT_TIME_OUT;
    private final int READ_TIME_OUT;
    private final boolean IS_CACHE;
    private final boolean IS_COOKIES;
    private IHttpRequest httpRequest;

    RxClient(Map<String, Object> params,
             WeakHashMap<String, String> headerPapams,
             RequestBody body,
             File file,
             String baseUrl,
             int connectTimeOut,
             int readTimeOut,
             boolean isCache,
             boolean addCookies,
             IHttpRequest httpRequest) {
        this.PARAMS.putAll(params);
        this.HEADER_PARAMS.putAll(headerPapams);
        this.BODY = body;
        this.FILE = file;
        this.BASEURL = baseUrl;
        this.CONNECT_TIME_OUT = connectTimeOut;
        this.READ_TIME_OUT = readTimeOut;
        this.IS_CACHE = isCache;
        this.IS_COOKIES = addCookies;
        this.httpRequest = httpRequest;

        if (httpRequest == null) {
            this.httpRequest = new OkhttpRequest();
        }
        this.httpRequest.init(PARAMS, HEADER_PARAMS, BODY, FILE, BASEURL, CONNECT_TIME_OUT, READ_TIME_OUT, IS_CACHE, IS_COOKIES);
    }

    public static RxClientBuilder builder() {
        return new RxClientBuilder();
    }

    public final void rxGet(String url, RxCallBack rxCallBack) {
        if (httpRequest == null) {
            throw new NullPointerException("httpRequest  is null!");
        }
        httpRequest.get(url, rxCallBack);
    }

    public final void rxPost(String url, RxCallBack rxCallBack) {
        if (httpRequest == null) {
            throw new NullPointerException("httpRequest  is null!");
        }
        httpRequest.rxPost(url, rxCallBack);
    }

    public final void rxPostRaw(String url, RxCallBack rxCallBack) {
        if (httpRequest == null) {
            throw new NullPointerException("httpRequest  is null!");
        }
        httpRequest.rxPostRaw(url, rxCallBack);
    }

    public final void rxUpload(String url, RxCallBack rxCallBack) {
        if (httpRequest == null) {
            throw new NullPointerException("httpRequest  is null!");
        }
        httpRequest.rxUpload(url, rxCallBack);
    }


    public final void rxDownload(String url) {
        if (httpRequest == null) {
            throw new NullPointerException("httpRequest  is null!");
        }
        httpRequest.rxDownload(url);
    }
}
