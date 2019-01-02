package playandroid.cmcc.com.loginmodule.login;


import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/5.
 */

public class LoginModel extends BaseModel<LoginPresenter> {


    public void login(String username, String password) {
        RxClient.builder()
                .addParams("username", username)
                .addParams("password", password)
                .build()
                .rxPost(BaseApiService.LOGIN, new RxCallBack<LoginRegisterBean>() {
                    @Override
                    public void rxOnNext(LoginRegisterBean response) {
                        if (response.getData() != null && response.getErrorCode() == 0) {
                            mBasePresenter.loginSucceed(response);
                        } else {
                            mBasePresenter.loginFialuer(response.getErrorMsg() + "    " + response.getErrorCode());
                        }
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        mBasePresenter.loginFialuer(e.toString());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
