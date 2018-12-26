package playandroid.cmcc.com.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.ui.BaseLoadingView;


/**
 * Created by wsf on 2018/11/6.
 */

public abstract class BaseActivity extends FragmentActivity {

    protected final String TAG = getClass().getSimpleName();

    protected Context mContext;

    private Unbinder mUnbind;
    private FrameLayout mFlContent;
    private RelativeLayout mBaseBarRoot;
    protected BaseLoadingView mBaseLoadView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView();
        mContext = this;
        BaseApplication.getApplication().getActivityManage().addActivity(this);
    }

    private void setContentView() {
        setContentView(R.layout.activity_base);
        mFlContent = findViewById(R.id.fl_content);
        mBaseBarRoot = findViewById(R.id.base_bar_root);
        mBaseLoadView = findViewById(R.id.base_load_view);
        mFlContent.addView(getLayoutInflater().inflate(getLayoutResID(), null));
        if (isActionBar()) {
            mBaseBarRoot.setVisibility(View.VISIBLE);
        } else {
            mBaseBarRoot.setVisibility(View.GONE);
        }
        mUnbind = ButterKnife.bind(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();

    }

    protected void showEmptyData() {
        if (mBaseLoadView != null) {
            mBaseLoadView.showEmptyData();
        }
    }

    protected void showLoading() {
        if (mBaseLoadView != null) {
            mBaseLoadView.showLoading();
        }
    }

    protected void showContent() {
        if (mBaseLoadView != null) {
            mBaseLoadView.showContent();
        }
    }

    protected void showNetWorkError() {
        if (mBaseLoadView != null) {
            mBaseLoadView.showNetWorkError();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbind != null) {
            mUnbind.unbind();
        }
        if (mFlContent != null) {
            mFlContent.removeAllViews();
            mFlContent = null;
        }
        if (mBaseLoadView != null) {
            mBaseLoadView.stopLoadAnimator();
        }
        BaseApplication.getApplication().getActivityManage().removeActivity(this);
    }

    protected abstract int getLayoutResID();

    protected abstract void initView();

    /**
     * 是否需要ActionBar
     */
    protected boolean isActionBar() {
        return false;
    }

}
