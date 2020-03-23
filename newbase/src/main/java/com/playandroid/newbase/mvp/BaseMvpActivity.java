package com.playandroid.newbase.mvp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseMvpActivity<T extends ViewBinding> extends AppCompatActivity {

    private T viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBefore();
        viewBinding = getViewBinding();
        setContentView(viewBinding.getRoot());
        initView(viewBinding);
    }

    protected abstract void initView(T viewBinding);

    protected abstract T getViewBinding();

    /**
     * 初始化view 之前
     */
    protected void initViewBefore() {
    }


}
