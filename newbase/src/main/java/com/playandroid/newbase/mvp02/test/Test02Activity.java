package com.playandroid.newbase.mvp02.test;

import com.playandroid.newbase.databinding.ActivityTest02Binding;
import com.playandroid.newbase.mvp02.BaseMvpActivity;
import com.playandroid.newbase.mvp02.InjectPresenter;

public class Test02Activity extends BaseMvpActivity<ActivityTest02Binding> implements Test02Callback {

    @InjectPresenter
    Test02Presenter test02Presenter;

    @Override
    protected void initView() {
        test02Presenter.getTest();
    }

    @Override
    protected ActivityTest02Binding getViewBinding() {
        return ActivityTest02Binding.inflate(getLayoutInflater());
    }

    @Override
    public void setTest(String message) {
        viewBinding.tvTextOne.setText(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
