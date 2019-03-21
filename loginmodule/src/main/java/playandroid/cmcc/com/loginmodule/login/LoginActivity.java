package playandroid.cmcc.com.loginmodule.login;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.common.EventBusMessage;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpActivity;
import playandroid.cmcc.com.loginmodule.R;
import playandroid.cmcc.com.loginmodule.R2;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;
import playandroid.cmcc.com.loginmodule.register.RegisterActivity;


@Route(path = "/account/login")
public class LoginActivity extends BaseMvpActivity<LoginPresenter> {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == RESULTCODE && data != null) {
            registerResult(data);
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.loginactivity_main;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R2.id.bt_login, R2.id.tv_register})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.bt_login) {
            String username = etUsername.getText().toString().trim();
            String userpassword = etUserpassword.getText().toString().trim();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpassword)) {
                ToastUtils.showShort("请输入账号或密码");
            } else {
                mBasePresenter.login(username, userpassword);
            }
        } else if (view.getId() == R.id.tv_register) {
            startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST);
        }
    }

    /**
     * 注册返回结果
     *
     * @param data
     */
    public void registerResult(Intent data) {
        etUsername.setText(data.getStringExtra(RegisterActivity.USERNAME));
        etUserpassword.setText(data.getStringExtra(RegisterActivity.USERPASSWORD));
    }


    @Override
    public LoginPresenter creatPersenter() {
        return new LoginPresenter();
    }
}
