package playandroid.cmcc.com.loginmodule.login;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import playandroid.cmcc.com.baselibrary.base.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.DataServiceManager;
import playandroid.cmcc.com.baselibrary.net.MgBaseObserver;
import playandroid.cmcc.com.baselibrary.net.service.RetrofitService;
import playandroid.cmcc.com.loginmodule.api.LoginApi;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/5.
 */

public class LoginModel extends BaseModel<LoginPresenter> {

    private Disposable loginDisposable;

    public void login(String username, String password){
        LoginApi serviceAPI = DataServiceManager.getServiceAPI(RetrofitService.baseUrl, LoginApi.class);
        serviceAPI.login(username,password).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new MgBaseObserver<LoginRegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        loginDisposable =d;
                    }

                    @Override
                    public void onNext(LoginRegisterBean bean) {
                        if (bean.getData()!=null&&bean.getErrorCode()==0){
                            mPresenter.loginSucceed(bean);
                        }else {
                            mPresenter.loginFialuer(bean.getErrorMsg()+"    "+bean.getErrorCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            mPresenter.loginFialuer(e.toString());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loginDisposable!=null){
            loginDisposable.dispose();
        }
    }
}
