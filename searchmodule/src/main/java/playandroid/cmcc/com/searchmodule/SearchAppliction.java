package playandroid.cmcc.com.searchmodule;

import android.util.Log;

import playandroid.cmcc.com.baselibrary.base.BaseApplication;

/**
 * Created by wsf on 2018/9/17.  SearchAppliction 只有在单独调试 初始化
 */

public class SearchAppliction extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("wsf","SearchAppliction  onCreate");
    }
}
