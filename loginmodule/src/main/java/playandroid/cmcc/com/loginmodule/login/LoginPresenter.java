package playandroid.cmcc.com.loginmodule.login;


import com.blankj.utilcode.util.ToastUtils;

import playandroid.cmcc.com.baselibrary.base.mvp.BasePresenterX;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/5.
 */

public class LoginPresenter extends BasePresenterX<LoginVu,LoginModel> {

    public void login(String username,String password){
        baseModel.login(username,password);
    }

    public void loginSucceed(LoginRegisterBean bean){
        baseView.loginSucceed(bean);
    }

    public void loginFialuer(String meg){
        ToastUtils.showShort("登录失败： "+meg);
    }
}
