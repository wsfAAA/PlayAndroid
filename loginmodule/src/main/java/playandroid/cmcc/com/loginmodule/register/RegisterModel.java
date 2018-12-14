package playandroid.cmcc.com.loginmodule.register;

import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.basemvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/6.
 */

public class RegisterModel extends BaseModel<RegisterPresenter> {


    public void requestRegister(String username, String userpassword, String repassword) {
        RxClient.builder()
                .addParams("username", username)
                .addParams("password", userpassword)
                .addParams("repassword", repassword)
                .build()
                .rxPost(BaseApiService.REGISTER, new RxCallBack<LoginRegisterBean>() {
                    @Override
                    public void rxOnNext(LoginRegisterBean response) {
                        if (response.getData() != null && response.getErrorCode() == 0) {
                            mBasePresenter.onRegisterSucceed(response);
                        } else {
                            mBasePresenter.onRegisterFailure(response.getErrorMsg());
                        }
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        mBasePresenter.onRegisterFailure(e.toString());
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
