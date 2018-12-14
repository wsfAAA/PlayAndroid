package playandroid.cmcc.com.baselibrary.net;

import android.text.TextUtils;

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import playandroid.cmcc.com.baselibrary.BuildConfig;
import playandroid.cmcc.com.baselibrary.base.BaseApplication;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;
import playandroid.cmcc.com.baselibrary.net.interceptor.CacheInterceptor;
import playandroid.cmcc.com.baselibrary.net.interceptor.HeaderInterceptor;
import playandroid.cmcc.com.baselibrary.util.BaseUtils;
import retrofit2.Retrofit;

public final class RxClient {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private final WeakHashMap<String, String> HEADER_PARAMS = new WeakHashMap<>();
    private final RequestBody BODY;
    private final File FILE;
    private final String BASEURL;
    private final int CONNECT_TIME_OUT;
    private final int READ_TIME_OUT;
    private final boolean IS_CACHE;

    RxClient(Map<String, Object> params,
             WeakHashMap<String, String> headerPapams,
             RequestBody body,
             File file,
             String baseUrl,
             int connectTimeOut,
             int readTimeOut,
             boolean isCache) {
        this.PARAMS.putAll(params);
        this.HEADER_PARAMS.putAll(headerPapams);
        this.BODY = body;
        this.FILE = file;
        this.BASEURL = baseUrl;
        this.CONNECT_TIME_OUT = connectTimeOut;
        this.READ_TIME_OUT = readTimeOut;
        this.IS_CACHE = isCache;
    }

    public static RxClientBuilder builder() {
        return new RxClientBuilder();
    }

    private void request(HttpMethod method, String url, final RxCallBack rxCallBack) {
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
            case PUT:
                observable = rxService.put(url, PARAMS);
                break;
            case PUT_RAW:
                observable = rxService.putRaw(url, BODY);
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
                            Object o = BaseUtils.getGsonInstance().fromJson(s, rxCallBack.mType);
                            rxCallBack.rxOnNext(o);
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

    public final void rxGet(String url, RxCallBack rxCallBack) {
        request(HttpMethod.GET, url, rxCallBack);
    }

    public final void rxPost(String url, RxCallBack rxCallBack) {
        if (BODY == null) {
            request(HttpMethod.POST, url, rxCallBack);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW, url, rxCallBack);
        }
    }

    public final void rxPut(String url, RxCallBack rxCallBack) {
        if (BODY == null) {
            request(HttpMethod.PUT, url, rxCallBack);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW, url, rxCallBack);
        }
    }

    public final void rxDelete(String url, RxCallBack rxCallBack) {
        request(HttpMethod.DELETE, url, rxCallBack);
    }

    public final void rxUpload(String url, RxCallBack rxCallBack) {
        request(HttpMethod.UPLOAD, url, rxCallBack);
    }


    public final Observable<ResponseBody> rxDownload(String url) {
        return getRxService().download(url, PARAMS);
    }

    private RxService getRxService() {
        Retrofit.Builder retrofitBuilde = RxCreator.getRetrofitBuilde();
        OkHttpClient.Builder okhttpBuilder = RxCreator.getOkhttpBuilder();

        if (!TextUtils.isEmpty(BASEURL)) {
            retrofitBuilde.baseUrl(BASEURL);
        } else {
            retrofitBuilde.baseUrl(RxCreator.BASE_URL);
        }

        if (CONNECT_TIME_OUT > 0) {
            okhttpBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        }
        if (READ_TIME_OUT > 0) {
            okhttpBuilder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        }

        if (IS_CACHE) {
            okhttpBuilder.addInterceptor(new CacheInterceptor(BaseApplication.getApplication()));
        }

        okhttpBuilder.addInterceptor(new HeaderInterceptor(HEADER_PARAMS));

        if (BuildConfig.DEBUG) {
            // TODO: 2018/12/14  网络日志打印输出
            okhttpBuilder.addInterceptor(new LogInterceptor());
        }
        retrofitBuilde.client(okhttpBuilder.build());
        Retrofit build = retrofitBuilde.build();
        return build.create(RxService.class);
    }

}
