package playandroid.cmcc.com.loginmodule;

import android.app.Application;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;


/**
 * Created by wsf on 2018/9/6.
 */

public class LoginApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);//单独调试时初始化 第三方工具类
        ToastUtils.showShort("初始化工具类");
    }
}
