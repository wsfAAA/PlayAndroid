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
     * 断点续联参考：https://www.jianshu.com/p/d82ae1774694
     *
     * 其实下载文件就是一个get请求，而断点续传则是要把发生异常时，已经下载的位置记录下来，
     * 再次下载时从这个位置继续下载。此时就要涉及到两个知识点了，
     * 一个Range的请求头字段（有了这个字段就可以读取服务端该文件字的节范围，从而实现从断点处继续下载）。
     * 一个RandomAccessFile类，这个类可以通过seek方法设置指针位置，从而来实现从断点处继续写入文件。
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
