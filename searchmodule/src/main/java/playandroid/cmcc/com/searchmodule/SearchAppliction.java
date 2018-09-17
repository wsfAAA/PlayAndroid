package playandroid.cmcc.com.searchmodule;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by wsf on 2018/9/17.
 */

public class SearchAppliction extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);//单独调试时初始化 第三方工具类
    }
}
