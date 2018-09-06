package playandroid.cmcc.com.loginmodule.login;


import android.content.Intent;

import playandroid.cmcc.com.baselibrary.base.vu.BasePresenterActivity;

public class LoginActivity extends BasePresenterActivity<LoginVu> {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LoginVu.REQUEST && resultCode == LoginVu.RESULTCODE&&data!=null) {
            vu.registerResult(data);
        }
    }
}
