package playandroid.cmcc.com.loginmodule.login;


import com.blankj.utilcode.util.ToastUtils;

import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/5.
 */

public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel> {

    public void login(String username, String password) {
        mBaseModel.login(username, password);
    }

    public void loginSucceed(LoginRegisterBean bean) {
        mBaseView.loginSucceed(bean);
    }

    public void loginFialuer(String meg) {
        ToastUtils.showShort("登录失败： " + meg);
    }

    @Override
    public LoginModel creatModel() {
        return new LoginModel();
    }
}
