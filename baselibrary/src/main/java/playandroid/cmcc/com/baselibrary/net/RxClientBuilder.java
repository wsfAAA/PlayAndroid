package playandroid.cmcc.com.baselibrary.net;


import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 使用构建者模式
 */
public final class RxClientBuilder {

    private WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();//WeakHashMap 不在使用时会被gc
    private RequestBody mBody = null;
    private File mFile = null;
    private String mBaseUrl;
    private boolean mIsObservable;//是否返回 Observable 进行rxjava操作符处理

    RxClientBuilder() {

    }

    public final RxClient build() {
        return new RxClient(PARAMS, mBody, mFile, mBaseUrl, mIsObservable);
    }

    public final RxClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxClientBuilder baseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        return this;
    }

    public final RxClientBuilder isObservable(boolean isObservable) {
        this.mIsObservable = isObservable;
        return this;
    }


}
