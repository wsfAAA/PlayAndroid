package com.playandroid.newbase.net.http;

import com.playandroid.newbase.net.HttpMethod;
import com.playandroid.newbase.net.callback.RxCallBack;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

public interface IHttpRequest {
    void init(Map<String, Object> params,
              WeakHashMap<String, String> headerPapams,
              RequestBody body,
              File file,
              String baseUrl,
              int connectTimeOut,
              int readTimeOut,
              boolean isCache,
              boolean addCookies);

    void get(String url, RxCallBack callback);

    void rxPost(String url, RxCallBack rxCallBack);

    void rxPostRaw(String url, RxCallBack rxCallBack);

    void rxUpload(String url, RxCallBack rxCallBack);

    void rxDownload(String url);

//     具体相关请求处理
//    <T> void request(HttpMethod method, String url, RxCallBack callback);
}
