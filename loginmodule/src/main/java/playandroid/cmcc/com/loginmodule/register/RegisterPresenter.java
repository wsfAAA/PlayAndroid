package playandroid.cmcc.com.loginmodule.register;

import com.blankj.utilcode.util.ToastUtils;

import playandroid.cmcc.com.baselibrary.base.jadapter.basemvp.BasePresenter;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/6.
 */

public class RegisterPresenter extends BasePresenter<RegisterActivity, RegisterModel> {

    public void register(String username, String userpassword, String repassword) {
        mBaseModel.requestRegister(username, userpassword, repassword);
    }


    /**
     * 注册成功
     */
    public void onRegisterSucceed(LoginRegisterBean bean) {
        mBaseView.registerSucceed(bean);
    }

    /**
     * 注册失败
     */
    public void onRegisterFailure(String mes) {
        ToastUtils.showShort("注册失败: " + mes);
    }
}
