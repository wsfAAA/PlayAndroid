package com.playandroid.newbase.mvp;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseMvpActivity<T extends ViewBinding> extends FragmentActivity {

    protected T viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBefore();
        viewBinding = getViewBinding();
        setContentView(viewBinding.getRoot());
        initView();
    }

    protected abstract void initView();

    protected abstract T getViewBinding();

    /**
     * 初始化view 之前
     */
    protected void initViewBefore() {
    }


}
