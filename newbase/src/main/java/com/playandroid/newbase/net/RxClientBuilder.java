package com.playandroid.newbase.net;


import com.playandroid.newbase.net.http.IHttpRequest;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 使用构建者模式
 */
public final class RxClientBuilder {

    private WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();//请求参数  WeakHashMap 不在使用时会被gc
    private final WeakHashMap<String, String> mHeaderPapams = new WeakHashMap<>();//请求头
    private RequestBody mBody = null;
    private File mFile = null;
    private String mBaseUrl;     //更换baseurl
    private int mConnectTimeOut;
    private int mReadTimeOut;
    private boolean mIsCache = false;   //是否使用okhttp缓存
    private boolean addCookies = false; //添加cookies
    private IHttpRequest httpRequest;

    RxClientBuilder() {

    }

    public final RxClient build() {
        return new RxClient(PARAMS, mHeaderPapams, mBody, mFile, mBaseUrl, mConnectTimeOut, mReadTimeOut, mIsCache, addCookies,httpRequest);
    }

    /**
     * 参数
     *
     * @param params
     * @return
     */
    public final RxClientBuilder addParams(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxClientBuilder addParams(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    /**
     * 请求头
     *
     * @param params
     * @return
     */
    public final RxClientBuilder addHeaderParams(WeakHashMap<String, String> params) {
        mHeaderPapams.putAll(params);
        return this;
    }

    public final RxClientBuilder addHeaderParams(String key, String value) {
        mHeaderPapams.put(key, value);
        return this;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public final RxClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * json 格式
     *
     * @param raw
     * @return
     */
    public final RxClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 替换baseurl
     *
     * @param baseUrl
     * @return
     */
    public final RxClientBuilder baseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        return this;
    }

    /**
     * 连接超时
     *
     * @param connectTimeOut
     * @return
     */
    public final RxClientBuilder connectTimeOut(int connectTimeOut) {
        this.mConnectTimeOut = connectTimeOut;
        return this;
    }

    /**
     * 读取超时
     *
     * @param readTimeOut
     * @return
     */
    public final RxClientBuilder readTimeOut(int readTimeOut) {
        this.mReadTimeOut = readTimeOut;
        return this;
    }

    /**
     * 是否使用 okhttp 缓存
     *
     * @param isCache
     * @return
     */
    public final RxClientBuilder cache(boolean isCache) {
        this.mIsCache = isCache;
        return this;
    }

    /**
     * 是否添加 Cookies
     *
     * @param addCookies
     * @return
     */
    public final RxClientBuilder addCookies(boolean addCookies) {
        this.addCookies = addCookies;
        return this;
    }

    public final RxClientBuilder addHttpRequest(IHttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        return this;
    }
}
