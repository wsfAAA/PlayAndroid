package playandroid.cmcc.com.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.mvp.IBaseView;


/**
 * Created by wsf on 2018/11/6.
 */

public abstract class BaseActivity extends FragmentActivity implements IBaseView{

    protected final String TAG = getClass().getSimpleName();

    protected Context mContext;

    private Unbinder mUnbind;
    private FrameLayout mFlContent;
    private RelativeLayout mBaseBarRoot;
    private boolean isActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView();
        mContext = this;
        BaseApplication.getApplication().getActivityManage().addActivity(this);
        Log.i("cesi---->", "BaseActivity onCreate");
    }

    private void setContentView() {
        setContentView(R.layout.activity_base);
        mFlContent = findViewById(R.id.fl_content);
        mBaseBarRoot = findViewById(R.id.base_bar_root);
        mFlContent.addView(getLayoutInflater().inflate(getLayoutResID(), null));
        mUnbind = ButterKnife.bind(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i("cesi---->", "BaseActivity onAttachedToWindow");
        initView();
        if (isActionBar) {
            mBaseBarRoot.setVisibility(View.VISIBLE);
        } else {
            mBaseBarRoot.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示 isActionBar
     *
     * @param isActionBar
     */
    protected void isActionBar(boolean isActionBar) {
        this.isActionBar = isActionBar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("cesi---->", "BaseActivity onDestroy");
        if (mUnbind != null) {
            mUnbind.unbind();
        }
        if (mFlContent != null) {
            mFlContent.removeAllViews();
            mFlContent = null;
        }
        BaseApplication.getApplication().getActivityManage().removeActivity(this);
    }

    protected abstract int getLayoutResID();

    protected abstract void initView();
}
