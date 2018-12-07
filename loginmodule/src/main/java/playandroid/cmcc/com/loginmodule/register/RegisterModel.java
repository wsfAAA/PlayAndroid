package playandroid.cmcc.com.loginmodule.register;

import playandroid.cmcc.com.baselibrary.basemvp.BaseModel;

/**
 * Created by wsf on 2018/9/6.
 */

public class RegisterModel extends BaseModel<RegisterPresenter> {


    public void requestRegister(String username, String userpassword, String repassword) {
//        LoginApi serviceAPI = DataServiceManager.getServiceAPI(RetrofitService.baseUrl, LoginApi.class);
//        serviceAPI.register(username, userpassword, repassword).subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new MgBaseObserver<LoginRegisterBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        registerDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(LoginRegisterBean bean) {
//                        if (bean.getData() != null && bean.getErrorCode() == 0) {
//                            mBasePresenter.onRegisterSucceed(bean);
//                        } else {
//                            mBasePresenter.onRegisterFailure(bean.getErrorMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mBasePresenter.onRegisterFailure(e.toString());
//                    }
//                });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
