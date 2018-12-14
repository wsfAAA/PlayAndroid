package playandroid.cmcc.com.baselibrary.net;

import android.text.TextUtils;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;
import playandroid.cmcc.com.baselibrary.util.BaseUtils;
import retrofit2.Retrofit;

public final class RxClient {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private final RequestBody BODY;
    private final File FILE;
    private final String BASEURL;

    RxClient(Map<String, Object> params,
             RequestBody body,
             File file,
             String baseUrl,
             boolean isObservable) {
        this.PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.BASEURL = baseUrl;
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
        if (!TextUtils.isEmpty(BASEURL)) {
            retrofitBuilde.baseUrl(BASEURL);
        } else {
            retrofitBuilde.baseUrl(RxCreator.BASE_URL);
        }
        Retrofit build = retrofitBuilde.build();
        return build.create(RxService.class);
    }

}
