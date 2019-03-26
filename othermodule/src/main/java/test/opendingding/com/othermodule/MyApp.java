package test.opendingding.com.othermodule;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by wsf on 2018/12/24.
 */

public class MyApp extends Application {

    public static MyApp MY_APP = null;

    @Override
    public void onCreate() {
        super.onCreate();
        this.MY_APP = this;
        Utils.init(this);
        ZXingLibrary.initDisplayOpinion(this);
    }
}
