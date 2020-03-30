package com.live.base.net.http;

import android.app.Application;
import android.content.Context;

import com.live.base.net.callback.DownloadListener;
import com.live.base.net.callback.RxCallBack;

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

    /**
     * 下载
     *
     * @param url                下载地址，去除 baseurl 后的url
     * @param download_path      存储路径
     * @param download_file_name 文件名称
     * @param downloadListener   下载回调
     */
    void rxDownload(String url, String download_path, String download_file_name, final DownloadListener downloadListener);

    /**
     * 开启 IntentService 断点续联
     *
     * @param url                下载地址，去除 baseurl 后的url
     * @param download_path      存储路径
     * @param download_file_name 文件名称
     */
    void rxDownloadService(Application application, String url, String download_path, String download_file_name);

    void rxDownloadService(Application application, String baseUrl, String url, String download_path, String download_file_name);
}
