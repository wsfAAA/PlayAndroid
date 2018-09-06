package playandroid.cmcc.com.loginmodule.register;

import com.blankj.utilcode.util.ToastUtils;

import playandroid.cmcc.com.baselibrary.base.mvp.BasePresenterX;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;

/**
 * Created by wsf on 2018/9/6.
 */

public class RegisterPresenter extends BasePresenterX<RegisterVu,RegisterModel> {

    public void register(String username,String userpassword,String repassword){
         baseModel.requestRegister(username,userpassword,repassword);
    }


    /**
     * 注册成功
     */
    public void onRegisterSucceed(LoginRegisterBean bean){
        baseView.registerSucceed(bean);
    }

    /**
     * 注册失败
     */
    public void onRegisterFailure(String mes){
        ToastUtils.showShort("注册失败: "+mes);
    }
}
