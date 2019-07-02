package playandroid.cmcc.com.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.ccm.idataservice.IServiceManager;
import com.ccm.idataservice.TestCallBack;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.mvp.IBaseView;


/**
 * Created by wsf on 2018/11/6.
 */

public abstract class BaseActivity extends FragmentActivity implements IBaseView {

    protected final String TAG = getClass().getSimpleName();

    protected Context mContext;

    private Unbinder mUnbind;
    private FrameLayout mFlContent;        //布局容器
    private RelativeLayout mBaseBarRoot;   //顶部标题栏
    private boolean isActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView();
        mContext = this;
        BaseApplication.getApplication().getActivityManage().addActivity(this);

        if (isReceiveEvent()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }

        Log.i("cesi---->", "BaseActivity onCreate");
    }

    private void setContentView() {
        setContentView(R.layout.activity_base);
        mFlContent = findViewById(R.id.fl_content);
        mBaseBarRoot = findViewById(R.id.base_bar_root);
        /**
         * 添加布局到容器
         */
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

    /**
     * 是否接收eventBust事件 重写该方法 ,false不接收 true接收
     */
    protected boolean isReceiveEvent() {
        return false;
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
        if (isReceiveEvent()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        }
        BaseApplication.getApplication().getActivityManage().removeActivity(this);
    }


    protected abstract int getLayoutResID();

    protected abstract void initView();
}
