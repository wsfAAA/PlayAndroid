package playandroid.cmcc.com.baselibrary.base.jadapter.basemvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.WindowManager;

import butterknife.ButterKnife;



/**
 * Created by wsf on 2018/11/6.
 */

public abstract class BaseActivity<P extends BasePresenter> extends FragmentActivity {

    protected final String TAG = getClass().getSimpleName();

    protected P mBasePresenter;

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        mBasePresenter = TUtil.getT(this, 0);
        mBasePresenter.addActivityInstanc(this);
        initView();
    }


    /**
     * 获得Layout文件id
     *
     * @return
     */
    protected abstract int getLayoutResID();


    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("wsf", TAG + " onDestroy");
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
            mBasePresenter = null;
        }
    }

}
