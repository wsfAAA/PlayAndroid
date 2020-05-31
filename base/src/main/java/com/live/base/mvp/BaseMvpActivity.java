package com.live.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMvpActivity extends FragmentActivity implements BaseView {
    private List<BasePresenter> mPresenters = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBefore();
        setContentView(getLayoutRes());
        mPresenters = MvpUtil.initPresenter(this);
        initView();
    }

    protected abstract void initView();

    protected abstract int getLayoutRes();

    /**
     * 初始化view 之前
     */
    protected void initViewBefore() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();  // 解绑
        }
    }
}
