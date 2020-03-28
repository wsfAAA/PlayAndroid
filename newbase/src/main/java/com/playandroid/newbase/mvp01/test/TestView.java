package com.playandroid.newbase.mvp01.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.playandroid.newbase.R;
import com.playandroid.newbase.databinding.TestLayoutViewBinding;
import com.playandroid.newbase.mvp01.BaseMvpViewGroup;
import com.playandroid.newbase.mvp01.InjectPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * BaseViewGroup 使用
 */
public class TestView extends BaseMvpViewGroup {
    @InjectPresenter
    TestPresenter testPresenter;

    public TestView(@NonNull Context context) {
        super(context);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(View view) {
        TestLayoutViewBinding viewBinding = TestLayoutViewBinding.bind(view);
        viewBinding.tvTextView.setText(testPresenter.getTestTow() + "    viewGroup 测试使用...");
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.test_layout_view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
