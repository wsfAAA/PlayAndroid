package playandroid.cmcc.com.loginmodule.login;


import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import playandroid.cmcc.com.baselibrary.base.bk.MgMvpXVu;
import playandroid.cmcc.com.loginmodule.R;
import playandroid.cmcc.com.loginmodule.R2;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;
import playandroid.cmcc.com.loginmodule.register.RegisterActivity;
import playandroid.cmcc.com.loginmodule.register.RegisterVu;

/**
 * Created by wsf on 2018/9/5.
 */

public class LoginVu extends MgMvpXVu<LoginPresenter> {

    @BindView(R2.id.et_username)
    EditText etUsername;
    @BindView(R2.id.et_userpassword)
    EditText etUserpassword;
    @BindView(R2.id.bt_login)
    Button btLogin;
    @BindView(R2.id.tv_register)
    TextView tvRegister;

    public static final int REQUEST = 1;
    public static final int RESULTCODE = 2;

    @Override
    public int getLayoutId() {
        return R.layout.loginactivity_main;
    }

    @Override
    public void bindView() {
        super.bindView();

    }

    @OnClick({R2.id.bt_login, R2.id.tv_register})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.bt_login) {
            String username = etUsername.getText().toString().trim();
            String userpassword = etUserpassword.getText().toString().trim();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpassword)) {
                ToastUtils.showShort("请输入账号或密码");
            } else {
                mPresenter.login(username, userpassword);
            }
        } else if (view.getId() == R.id.tv_register) {
            if (context instanceof LoginActivity) {
                ((LoginActivity) context).startActivityForResult(new Intent(context, RegisterActivity.class), REQUEST);
            }
        }
    }

    /**
     * 注册返回结果
     *
     * @param data
     */
    public void registerResult(Intent data) {
        etUsername.setText(data.getStringExtra(RegisterVu.USERNAME));
        etUserpassword.setText(data.getStringExtra(RegisterVu.USERPASSWORD));
    }

    /**
     * 登录成功
     *
     * @param bean
     */
    public void loginSucceed(LoginRegisterBean bean) {
        ToastUtils.showShort("登录成功:  " + bean.getData().getUsername() + "   " + bean.getData().getPassword());
        if ((Activity) context instanceof LoginActivity) {
            ((Activity) context).finish();
        }
    }
}
