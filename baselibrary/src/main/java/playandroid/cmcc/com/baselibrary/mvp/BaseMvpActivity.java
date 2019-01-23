package playandroid.cmcc.com.baselibrary.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.base.BaseActivity;
import playandroid.cmcc.com.baselibrary.ui.BaseLoadingView;

/**
 * Created by wsf on 2018/11/14.
 * mvp BaseMvpActivity
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mBasePresenter;
    public BaseLoadingView mBaseLoadView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mBasePresenter = TUtil.getT(this, 0);//通过反射 绑定Presenter
        Log.i("cesi---->", "BaseMvpActivity onCreate");
        mBaseLoadView = findViewById(R.id.base_load_view);
        mBasePresenter = creatPersenter();
        if (mBasePresenter != null) {
            mBasePresenter.addActivityInstanc(this);
            mBasePresenter.setContext(mContext);
        }
    }

//    public void showContent() {
//        if (mBaseLoadView != null) {
//            mBaseLoadView.showContent();
//        }
//    }
//
//    public void showLoading() {
//        if (mBaseLoadView != null) {
//            mBaseLoadView.showLoading();
//        }
//    }
//
//    public void showEmptyData() {
//        if (mBaseLoadView != null) {
//            mBaseLoadView.showEmptyData();
//        }
//    }
//
//    public void showNetWorkError() {
//        if (mBaseLoadView != null) {
//            mBaseLoadView.showNetWorkError();
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("cesi---->", "BaseMvpActivity onDestroy");
        if (mBaseLoadView != null) {
            mBaseLoadView.stopLoadAnimator();
        }
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
            mBasePresenter = null;
        }
    }

    public abstract P creatPersenter();
}
