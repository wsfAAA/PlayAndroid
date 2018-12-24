package test.opendingding.com.othermodule.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import test.opendingding.com.othermodule.net.callback.IError;
import test.opendingding.com.othermodule.net.callback.IFailure;
import test.opendingding.com.othermodule.net.callback.IRequest;
import test.opendingding.com.othermodule.net.callback.ISuccess;
import test.opendingding.com.othermodule.net.callback.RequestCallBack;
import test.opendingding.com.othermodule.net.download.DownloadHandler;
import test.opendingding.com.othermodule.net.ui.LoaderStyle;
import test.opendingding.com.othermodule.net.ui.RetrofitLoader;

/**
 * Created by wsf on 2018/11/26.
 */

public class RetrofitClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;

    private final String DOWNLOAD_DIR; //文件夹
    private final String EXTENSION; //文件后缀
    private final String NAME;  //文件名

    public RetrofitClient(String url, WeakHashMap<String, Object> params, IRequest request,
                          ISuccess iSuccess, IFailure iFailure, IError iError, RequestBody booy,
                          Context context, LoaderStyle loaderStyle, File file, String downloadDir,
                          String extension, String name) {
        this.URL = url;
        this.IREQUEST = request;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.BODY = booy;
        PARAMS.putAll(params);
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public static RetrofitClientBuilder builder() {
        return new RetrofitClientBuilder();
    }

    private void request(HttpMethod method) {
        RetrofitService service = RetrofitCreator.getRestService();
        Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                if (FILE == null) {
                    throw new RuntimeException("You have to pass in the file or file path ");
                }
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        if (LOADER_STYLE != null) {
            RetrofitLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBack(IREQUEST, IERROR, IFAILURE, ISUCCESS, LOADER_STYLE);
    }

    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public void delete() {
        request(HttpMethod.DELETE);
    }

    public void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, IREQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                ISUCCESS, IFAILURE, IERROR)
                .handleDownload();
    }
}
