package com.playandroid.newbase.net.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;
import com.playandroid.newbase.BaseApplication;
import com.playandroid.newbase.net.HttpMethod;
import com.playandroid.newbase.net.RetrofitService;
import com.playandroid.newbase.net.RxCreator;
import com.playandroid.newbase.net.RxService;
import com.playandroid.newbase.net.api.ApiService;
import com.playandroid.newbase.net.callback.RxCallBack;
import com.playandroid.newbase.net.interceptor.CacheInterceptor;
import com.playandroid.newbase.net.interceptor.HeaderInterceptor;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
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

    @Override
    public void rxDownload(String url) {
        Observable<ResponseBody> download = getRxService().download(url, PARAMS);
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

//        retrofitBuilde.addConverterFactory()  //添加retrofit解析工厂
        Retrofit build = retrofitBuilde.build();
        return build.create(RetrofitService.class);
    }
}
