package playandroid.cmcc.com.baselibrary.net;


import android.text.TextUtils;

import com.tamic.novate.Novate;
import com.tamic.novate.callback.ResponseCallback;
import com.tamic.novate.callback.RxFileCallBack;
import com.tamic.novate.callback.RxStringCallback;

import java.io.File;
import java.util.List;
import java.util.WeakHashMap;

import playandroid.cmcc.com.baselibrary.BuildConfig;
import playandroid.cmcc.com.baselibrary.base.BaseApplication;
import playandroid.cmcc.com.baselibrary.util.BaseUtils;

/**
 * Created by wsf on 2018/12/6.
 * <p>
 * 相关使用 https://github.com/Tamicer/Novate/wiki/Api%E6%96%87%E6%A1%A3
 */

public class HttpClient {

    private static HttpClient mHttpClient;
    private Novate.Builder mNovateBuilder;
    private String mBaseUrl="";
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 5;
    private WeakHashMap<String, Object> mParams = new WeakHashMap<>();


    public static synchronized HttpClient getInstance() {
        if (mHttpClient == null) {
            mHttpClient = new HttpClient();
        }
        return new HttpClient();
    }

    public HttpClient() {
        mNovateBuilder = new Novate.Builder(BaseApplication.getApplication());
        mNovateBuilder.baseUrl(mBaseUrl);   //base URL
        mNovateBuilder.connectTimeout(CONNECT_TIMEOUT);  //连接时间 单位s
        mNovateBuilder.readTimeout(READ_TIMEOUT);      //读取超时 单位 s
        if (BuildConfig.DEBUG) {
            mNovateBuilder.addLog(true);    //是否开启log
        } else {
            mNovateBuilder.addLog(false);
        }
//                .addHeader(headers) //添加公共请求头
//                .addParameters(parameters)//公共参数
//                .addCookie(false)  //是否同步cooike 默认不同步
//                .addCache(true)  //是否缓存 默认缓存
//                .addCache(cache, cacheTime)   //自定义缓存
//                .cookieManager(new NovateCookieManager()) // 自定义cooike，可以忽略
//                .addInterceptor() // 自定义Interceptor
//                .addNetworkInterceptor() // 自定义NetworkInterceptor
//                .proxy(proxy) //代理
//                .client(client)  //clent 默认不需要
    }

    /**
     * 获取Novate 对象
     *
     * @return
     */
    public Novate getNovate() {
        if (mNovateBuilder == null) {
            new NullPointerException("mNovateBuilder is null!");
        }
        checkBaseUrl();
        return mNovateBuilder.build();
    }

    public HttpClient baseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        return this;
    }

    public HttpClient params(String key, String value) {
        mParams.put(key, value);
        return this;
    }

    public HttpClient params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    private void request(HttpMethod method, String url, WeakHashMap<String, Object> params, ResponseCallback callback) {
        checkBaseUrl();
        switch (method) {
            case GET:
                mNovateBuilder.build().rxGet(url, params, callback);
                break;
            case POST:
                mNovateBuilder.build().rxPost(url, params, callback);
                break;
            case PUT:
                mNovateBuilder.build().rxPut(url, params, callback);
                break;
            case DELETE:
                mNovateBuilder.build().rxDelete(url, params, callback);
                break;
            case JSON:
                String json = BaseUtils.toJson(params);
                mNovateBuilder.build().rxJson(url, json, callback);
                break;
        }
        mParams.clear();
    }


    ///////////////////////////////////////相关请求/////////////////////////////////////////////////
    public void rxGet(String url, ResponseCallback callback) {
        request(HttpMethod.GET, url, mParams, callback);
    }

    public void rxPost(String url, ResponseCallback callback) {
        request(HttpMethod.POST, url, mParams, callback);
    }

    public void rxPut(String url, ResponseCallback callback) {
        request(HttpMethod.PUT, url, mParams, callback);
    }

    public void rxDelete(String url, ResponseCallback callback) {
        request(HttpMethod.DELETE, url, mParams, callback);
    }

    public void rxJson(String url, ResponseCallback callback) {
        request(HttpMethod.JSON, url, mParams, callback);
    }

    ///////////////////////////////////////文件上传////////////////////////////////////////////////

    /**
     * 上传单个文件  上传文件，默认的key是 image
     * 上传其他类型参考：https://github.com/Tamicer/Novate/wiki/%E4%B8%8A%E4%BC%A0%E6%96%87%E4%BB%B6
     *
     * @param url      接口
     * @param file     文件
     * @param callback 回调
     */
    public void upLoadFile(String url, File file, RxStringCallback callback) {
        checkBaseUrl();
        mNovateBuilder.build().rxUploadWithPart(url, file, callback);
    }

    /**
     * 上传多个文件
     *
     * @param url      接口
     * @param files    文件
     * @param callback 回调
     */
    public void upLoadFile(String url, List<File> files, RxStringCallback callback) {
        checkBaseUrl();
        mNovateBuilder.build().rxUploadWithPartListByFile(url, files, callback);
    }

    ////////////////////////////////////////// 下载文件 /////////////////////////////////////////////

    /**
     * 文件下载
     *
     * @param url      文件路径
     * @param callBack 回调
     */
    public void downloadFile(String url, RxFileCallBack callBack) {
        checkBaseUrl();
        mNovateBuilder.build().rxDownload(url, callBack);
    }


    /**
     * 检查是否设置baseURL
     */
    private void checkBaseUrl() {
        if (TextUtils.isEmpty(mBaseUrl)) {
            mBaseUrl = BaseApi.BASE_URL;
        }
        mNovateBuilder.baseUrl(mBaseUrl);
    }
}
