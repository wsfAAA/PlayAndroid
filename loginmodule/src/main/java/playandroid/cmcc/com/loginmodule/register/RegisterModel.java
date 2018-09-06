package playandroid.cmcc.com.loginmodule.register;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import playandroid.cmcc.com.baselibrary.base.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.DataServiceManager;
import playandroid.cmcc.com.baselibrary.net.MgBaseObserver;
import playandroid.cmcc.com.loginmodule.api.LoginApi;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/6.
 */

public class RegisterModel extends BaseModel<RegisterPresenter> {

    private Disposable registerDisposable;

    public void requestRegister(String username, String userpassword, String repassword) {
        LoginApi serviceAPI = DataServiceManager.getServiceAPI(LoginApi.baseUrl, LoginApi.class);
        serviceAPI.register(username, userpassword, repassword).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new MgBaseObserver<LoginRegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        registerDisposable =d;
                    }

                    @Override
                    public void onNext(LoginRegisterBean bean) {
                        if (bean.getData()!=null&&bean.getErrorCode()==0){
                            mPresenter.onRegisterSucceed(bean);
                        }else {
                            mPresenter.onRegisterFailure(bean.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresenter.onRegisterFailure(e.toString());
                    }
                });

//        LoginApi serviceAPI = DataServiceManager.getServiceAPI(LoginApi.baseUrl, LoginApi.class);
//        serviceAPI.register(username, userpassword, repassword).subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new Observer<Object>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        LogUtils.iTag("wsf",d);
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//                        LogUtils.iTag("wsf",TAG+"    "+o);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.iTag("wsf",TAG+"    "+e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        LogUtils.iTag("wsf","onComplete");
//                    }
//                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (registerDisposable!=null){
            registerDisposable.dispose();
        }
    }
}
