package com.live.base.net.http;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.ViewUtils;
import com.google.gson.Gson;
import com.live.base.BaseApplication;
import com.live.base.net.HttpMethod;
import com.live.base.net.RetrofitService;
import com.live.base.net.RxCreator;
import com.live.base.net.RxService;
import com.live.base.net.api.ApiService;
import com.live.base.net.callback.DownloadListener;
import com.live.base.net.callback.RxCallBack;
import com.live.base.net.interceptor.CacheInterceptor;
import com.live.base.net.interceptor.HeaderInterceptor;
import com.live.base.net.service.DownloadIntentService;
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.BuildConfig;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OkhttpRequest implements IHttpRequest {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private final WeakHashMap<String, String> HEADER_PARAMS = new WeakHashMap<>();
    private RequestBody BODY;
    private File FILE;
    private String BASEURL;
    private int CONNECT_TIME_OUT;
    private int READ_TIME_OUT;
    private boolean IS_CACHE;
    private boolean IS_COOKIES;

    @Override
    public void init(Map<String, Object> params,
                     WeakHashMap<String, String> headerPapams,
                     RequestBody body,
                     File file,
                     String baseUrl,
                     int connectTimeOut,
                     int readTimeOut,
                     boolean isCache,
                     boolean addCookies) {
        this.PARAMS.putAll(params);
        this.HEADER_PARAMS.putAll(headerPapams);
        this.BODY = body;
        this.FILE = file;
        this.BASEURL = baseUrl;
        this.CONNECT_TIME_OUT = connectTimeOut;
        this.READ_TIME_OUT = readTimeOut;
        this.IS_CACHE = isCache;
        this.IS_COOKIES = addCookies;
    }

    @Override
    public void get(String url, RxCallBack rxCallBack) {
        request(HttpMethod.GET, url, rxCallBack);
//        retrofitrequest(HttpMethod.GET, url, rxCallBack);
    }

    @Override
    public void rxPost(String url, RxCallBack rxCallBack) {
        request(HttpMethod.POST, url, rxCallBack);
    }

    @Override
    public void rxPostRaw(String url, RxCallBack rxCallBack) {
        request(HttpMethod.POST_RAW, url, rxCallBack);
    }

    @Override
    public void rxUpload(String url, RxCallBack rxCallBack) {
        request(HttpMethod.UPLOAD, url, rxCallBack);
    }

    private long range = 0;

    @Override
    public void rxDownload(final String downloadUrl, final String download_path, final String download_file_name, final DownloadListener downloadListener) {
        if (downloadListener == null) {
            return;
        }
        final File file = new File(download_path + download_file_name);
        int progress = 0;
        String totalLength = "-"; //断点续传时请求的总长度
        if (file.exists()) {
            range = SPUtils.getInstance().getLong(downloadUrl, 0);
            progress = (int) (range * 100 / file.length());
            totalLength += file.length();
            if (range == file.length()) {   //检测到已经下载完成
                downloadListener.onCompleted(file.getAbsolutePath());
                return;
            }
        }
//        Log.e("wsf", "range = " + range + "    已下载:  " + progress + "  % " + "     totalLength:  " + totalLength);
        downloadListener.onStart(progress);
        final Call<ResponseBody> download = getRetrofitService().download("bytes=" + Long.toString(range) + totalLength, downloadUrl);
        download.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    writeResponseBodyToDisk(response.body(), range, downloadUrl, download_path, download_file_name, downloadListener);
                } else {
                    downloadListener.onError("onResponse : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                downloadListener.onError(t.getMessage());
            }
        });
    }

    @Override
    public void rxDownloadService(Application application, String url, String download_path, String download_file_name) {
        Intent intent = new Intent(application, DownloadIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("download_url", url);
        bundle.putString("download_file_name", download_file_name);
        bundle.putString("download_path", download_path);
        intent.putExtras(bundle);
        application.startService(intent);
    }

    @Override
    public void rxDownloadService(Application application, String baseUrl, String url, String download_path, String download_file_name) {
        Intent intent = new Intent(application, DownloadIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("download_url", url);
        bundle.putString("download_file_name", download_file_name);
        bundle.putString("download_path", download_path);
        bundle.putString("download_base_url", baseUrl);
        intent.putExtras(bundle);
        application.startService(intent);
    }

    /////////////////////////////////////////// retrofit 配合 rxjava 请求  ////////////////////////////////////
    public void request(HttpMethod method, String url, final RxCallBack rxCallBack) {
        RxService rxService = getRxService();
        Observable<String> observable = null;
        switch (method) {
            case GET:
                observable = rxService.get(url, PARAMS);
                break;
            case POST:
                observable = rxService.post(url, PARAMS);
                break;
            case POST_RAW:
                observable = rxService.postRaw(url, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = rxService.upload(url, body);
                break;
            default:
                break;
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (rxCallBack.mType == String.class) {
                            rxCallBack.rxOnNext(s);
                        } else {
                            try {
                                rxCallBack.rxOnNext(new Gson().fromJson(s, rxCallBack.mType));
                            } catch (Exception e) {
                                Log.e("wsf", "Exception异常-----------> " + e.toString() + "         " + rxCallBack.mType + "       " + s);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (rxCallBack != null) {
                            rxCallBack.rxOnError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private RxService getRxService() {
        Retrofit.Builder retrofitBuilde = RxCreator.getRetrofitBuilde();
        OkHttpClient.Builder okhttpBuilder = RxCreator.getOkhttpBuilder();

        if (!TextUtils.isEmpty(BASEURL)) {
            retrofitBuilde.baseUrl(BASEURL);
        } else {
            retrofitBuilde.baseUrl(ApiService.BASE_URL);
        }

        if (CONNECT_TIME_OUT > 0) {
            okhttpBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        }
        if (READ_TIME_OUT > 0) {
            okhttpBuilder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        }

        if (BuildConfig.DEBUG) {
            okhttpBuilder.cache(RxCreator.getCache());
        }
        // TODO: 2018/12/14  网络日志打印输出
        okhttpBuilder.addInterceptor(new LogInterceptor());

        if (IS_CACHE) { //添加okhttp网络数据缓存
            okhttpBuilder.addInterceptor(new CacheInterceptor(BaseApplication.getBaseApplication()));
        }

        //添加公用请求参数 和 请求参数
        okhttpBuilder.addInterceptor(new HeaderInterceptor(HEADER_PARAMS));

//        if (IS_COOKIES) {
//            okhttpBuilder.addInterceptor(new AddCookiesInterceptor());
//        }
        retrofitBuilde.client(okhttpBuilder.build());
        Retrofit build = retrofitBuilde.build();
        return build.create(RxService.class);
    }

    private void writeResponseBodyToDisk(final ResponseBody responseBody, final long range, final String downloadUrl,
                                         final String download_path, final String download_file_name,
                                         final DownloadListener downloadListener) {
        //创建单一线程池（线程里面只有一个线程，如果该线程意外死亡，那么系统会自动创建一个新的线程来代替）
        ExecutorService singlePool = ThreadUtils.getSinglePool();
        singlePool.execute(new Runnable() {
            @Override
            public void run() {
                RandomAccessFile randomAccessFile = null;
                InputStream inputStream = null;
                long total = range;
                long responseLength = 0;
                try {
                    byte[] buf = new byte[2048];
                    int len = 0;
                    responseLength = responseBody.contentLength();
                    inputStream = responseBody.byteStream();
                    final File file = new File(download_path, download_file_name);
                    File dir = new File(download_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    randomAccessFile = new RandomAccessFile(file, "rwd");
                    if (range == 0) {
                        randomAccessFile.setLength(responseLength);
                    }
                    randomAccessFile.seek(range);

                    int progress = 0;
                    int lastProgress = 0;

                    while ((len = inputStream.read(buf)) != -1) {
                        randomAccessFile.write(buf, 0, len);
                        total += len;
                        SPUtils.getInstance().put(downloadUrl, total);
                        lastProgress = progress;
                        progress = (int) (total * 100 / randomAccessFile.length());
                        if (progress > 0 && progress != lastProgress) {
                            final int finalProgress = progress;
                            ViewUtils.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downloadListener.onProgress(finalProgress);
                                }
                            });
                        }
                    }
                    ViewUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            downloadListener.onCompleted(file.getAbsolutePath());
                        }
                    });
                } catch (final Exception e) {
                    ViewUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            downloadListener.onError(e.getMessage());
                        }
                    });
                    e.printStackTrace();
                } finally {
                    try {
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }

                        if (inputStream != null) {
                            inputStream.close();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    /////////////////////////////////////////// retrofit 请求  ////////////////////////////////////
    public void retrofitrequest(HttpMethod method, String url, final RxCallBack rxCallBack) {
        RetrofitService retrofitService = getRetrofitService();
        Call<String> call = null;
        switch (method) {
            case GET:
                call = retrofitService.get(url, PARAMS);
                break;
            case POST:
                call = retrofitService.post(url, PARAMS);
                break;
            case POST_RAW:
                call = retrofitService.postRaw(url, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = retrofitService.upload(url, body);
                break;
            default:
                break;
        }
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body())) {
                    String body = response.body();
                    if (rxCallBack.mType == String.class) {
                        rxCallBack.rxOnNext(body);
                    } else {
                        try {
                            rxCallBack.rxOnNext(new Gson().fromJson(body, rxCallBack.mType));
                        } catch (Exception e) {
                        }
                    }
                } else {
                    rxCallBack.rxOnError(new Throwable("response is null or response.body() is null "));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                rxCallBack.rxOnError(throwable);
            }
        });
    }

    private RetrofitService getRetrofitService() {
        Retrofit.Builder retrofitBuilde = RxCreator.getRetrofitBuilde();
        OkHttpClient.Builder okhttpBuilder = RxCreator.getOkhttpBuilder();

        if (!TextUtils.isEmpty(BASEURL)) {
            retrofitBuilde.baseUrl(BASEURL);
        } else {
            retrofitBuilde.baseUrl(ApiService.BASE_URL);
        }

        if (CONNECT_TIME_OUT > 0) {
            okhttpBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        }
        if (READ_TIME_OUT > 0) {
            okhttpBuilder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        }

        if (BuildConfig.DEBUG) {
            okhttpBuilder.cache(RxCreator.getCache());
        }
        // TODO: 2018/12/14  网络日志打印输出
        okhttpBuilder.addInterceptor(new LogInterceptor());

        if (IS_CACHE) { //添加okhttp网络数据缓存
            okhttpBuilder.addInterceptor(new CacheInterceptor(BaseApplication.getBaseApplication()));
        }

        //添加公用请求参数 和 请求参数
        okhttpBuilder.addInterceptor(new HeaderInterceptor(HEADER_PARAMS));

//        if (IS_COOKIES) {
//            okhttpBuilder.addInterceptor(new AddCookiesInterceptor());
//        }
        retrofitBuilde.client(okhttpBuilder.build());

        Retrofit build = retrofitBuilde.build();
        return build.create(RetrofitService.class);
    }
}
