package playandroid.cmcc.com.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import playandroid.cmcc.com.baselibrary.base.BaseApplication;


/**
 * Created by wsf on 2018/11/6.
 */

public abstract class BaseActivity extends FragmentActivity {

    protected final String TAG = getClass().getSimpleName();

    protected Context mContext;

    private Unbinder mUnbind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //消除actionbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //设置全屏
        setContentView(getLayoutResID());
        mUnbind = ButterKnife.bind(this);
        mContext = this;
        initView();

        BaseApplication.getApplication().getActivityManage().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbind != null) {
            mUnbind.unbind();
        }
        BaseApplication.getApplication().getActivityManage().removeActivity(this);
    }

    protected abstract int getLayoutResID();

    protected abstract void initView();

}
