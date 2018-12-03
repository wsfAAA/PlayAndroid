package playandroid.cmcc.com.baselibrary.net.callback;

import playandroid.cmcc.com.baselibrary.net.ui.LoaderStyle;
import playandroid.cmcc.com.baselibrary.net.ui.RetrofitLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wsf on 2018/11/27.
 */

public class RequestCallBack implements Callback<String> {

    private final IRequest IREQUEST;
    private final IError IERROR;
    private final IFailure IFAILURE;
    private final ISuccess ISUCCESS;
    private final LoaderStyle LOADER_STYLE;
//    private static final Handler HANDLER = new Handler();

    public RequestCallBack(IRequest irequest, IError ierror, IFailure ifailure, ISuccess isuccess, LoaderStyle loaderStyle) {
        IREQUEST = irequest;
        IERROR = ierror;
        IFAILURE = ifailure;
        ISUCCESS = isuccess;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful() && call.isExecuted()) {
            if (ISUCCESS != null) {
                ISUCCESS.onSuccess(response.body());
            }
        } else {
            if (IERROR != null) {
                IERROR.onError(response.code(), response.message());
            }
        }
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE != null) {
            IFAILURE.onFailure(t);
        }
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    private void onRequestFinish() {
        if (LOADER_STYLE != null) {
//            HANDLER.postDelayed(new Runnable() {
//                @Override
//                public void run() {
            RetrofitLoader.stopLoading();
//                }
//            }, 2000);
        }
    }
}
