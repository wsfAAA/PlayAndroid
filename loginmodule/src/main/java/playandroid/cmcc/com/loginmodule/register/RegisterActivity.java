package playandroid.cmcc.com.loginmodule.register;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpActivity;
import playandroid.cmcc.com.loginmodule.R;
import playandroid.cmcc.com.loginmodule.R2;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;
import playandroid.cmcc.com.loginmodule.login.LoginActivity;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> {

    @BindView(R2.id.et_username)
    EditText etUsername;
    @BindView(R2.id.et_userpassword)
    EditText etUserpassword;
    @BindView(R2.id.bt_register)
    Button btRegister;
    @BindView(R2.id.tv_login)
    TextView tvLogin;
    @BindView(R2.id.et_repassword)
    EditText etRepassword;

    public static final String USERNAME = "USERNAME";
    public static final String USERPASSWORD = "USERPASSWORD";
    private LoginRegisterBean loginRegisterBean;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R2.id.bt_register, R2.id.tv_login})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.bt_register) {
            String username = etUsername.getText().toString().trim();
            String userpassword = etUserpassword.getText().toString().trim();
            String repassword = etRepassword.getText().toString().trim();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpassword) || TextUtils.isEmpty(repassword)) {
                ToastUtils.showShort("请输入用户名或密码");
            }
            mBasePresenter.register(username, userpassword, repassword);
        } else if (view.getId() == R.id.tv_login) {
            startLogin();
        }
    }


    public void registerSucceed(LoginRegisterBean loginRegisterBean) {
        this.loginRegisterBean = loginRegisterBean;
        startLogin();
    }

    private void startLogin() {
        Intent intent = new Intent();
        if (loginRegisterBean != null) {
            intent.putExtra(USERNAME, loginRegisterBean.getData().getUsername());
            intent.putExtra(USERPASSWORD, loginRegisterBean.getData().getPassword());
        }
        setResult(LoginActivity.RESULTCODE, intent);
        finish();
    }

    @Override
    public RegisterPresenter creatPersenter() {
        return new RegisterPresenter();
    }
}
