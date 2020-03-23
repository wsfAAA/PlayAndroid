package com.playandroid.newbase;

import com.playandroid.newbase.databinding.ActivityTestBinding;
import com.playandroid.newbase.mvp.BaseMvpActivity;

public class TestActivity extends BaseMvpActivity<ActivityTestBinding> {

    @Override
    protected void initView() {
        viewBinding.tvTextTest.setText("bbbbbbbbbbbbbbbbbbbbbbb");
    }

    @Override
    protected ActivityTestBinding getViewBinding() {
        return ActivityTestBinding.inflate(getLayoutInflater());
    }
}
