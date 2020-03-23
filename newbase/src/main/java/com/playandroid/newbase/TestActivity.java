package com.playandroid.newbase;

import com.playandroid.newbase.mvp.InjectPresenter;
import com.playandroid.newbase.mvp.BaseMvpActivity;
import com.playandroid.newbase.databinding.ActivityTestBinding;

public class TestActivity extends BaseMvpActivity<ActivityTestBinding> {

    @InjectPresenter
    TestPresenter testPresenter;

    @Override
    protected void initView() {
        viewBinding.tvTextTest.setText(testPresenter.getTest());
    }

    @Override
    protected ActivityTestBinding getViewBinding() {
        return ActivityTestBinding.inflate(getLayoutInflater());
    }
}
