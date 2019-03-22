package playandroid.cmcc.com.loginmodule.login;



import org.greenrobot.eventbus.EventBus;

import cmcc.com.playandroid.common.EventBusMessage;
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
//        ToastUtils.showShort("登录成功:  " + bean.getData().getUsername() + "   " + bean.getData().getPassword());
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.EVENT_LOGIN_SUCCEED,"登录成功"));
        mBaseView.finish();
    }

    public void loginFialuer(String meg) {
//        ToastUtils.showShort("登录失败： " + meg);
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.EVENT_LOGIN_FIALUER,meg));
        mBaseView.finish();
    }

    @Override
    public LoginModel creatModel() {
        return new LoginModel();
    }
}
