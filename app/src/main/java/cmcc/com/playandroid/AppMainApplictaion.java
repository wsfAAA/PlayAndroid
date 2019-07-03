package cmcc.com.playandroid;


import android.util.Log;

import playandroid.cmcc.com.baselibrary.base.BaseApplication;

/**
 * Created by wsf on 2018/9/6.
 * 主项目的 Application
 */
public class AppMainApplictaion extends BaseApplication  {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("wsf","AppMainApplictaion  onCreate");
    }
}
