package playandroid.cmcc.com.loginmodule;

import android.util.Log;

import playandroid.cmcc.com.baselibrary.base.BaseApplication;


/**
 * Created by wsf on 2018/9/6.  LoginApplication 只有在单独调试 初始化
 */

public class LoginApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("wsf ","LoginApplication  onCreate");
    }
}
