package playandroid.cmcc.com.loginmodule.login;


import playandroid.cmcc.com.baselibrary.basemvp.BaseModel;
import playandroid.cmcc.com.loginmodule.api.LoginApi;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/5.
 */

public class LoginModel extends BaseModel<LoginPresenter> {


    public void login(String username, String password){
//        LoginApi serviceAPI = DataServiceManager.getServiceAPI(RetrofitService.baseUrl, LoginApi.class);
//        serviceAPI.login(username,password).subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new MgBaseObserver<LoginRegisterBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        loginDisposable =d;
//                    }
//
//                    @Override
//                    public void onNext(LoginRegisterBean bean) {
//                        if (bean.getData()!=null&&bean.getErrorCode()==0){
//                            mBasePresenter.loginSucceed(bean);
//                        }else {
//                            mBasePresenter.loginFialuer(bean.getErrorMsg()+"    "+bean.getErrorCode());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mBasePresenter.loginFialuer(e.toString());
//                    }
//                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
