package com.playandroid.newbase;

import com.playandroid.newbase.databinding.ActivityTestBinding;
import com.playandroid.newbase.mvp.BaseMvpActivity;
import com.playandroid.newbase.mvp.InjectPresenter;
import com.playandroid.newbase.test.TestFragment;

import androidx.fragment.app.FragmentTransaction;

public class TestActivity extends BaseMvpActivity<ActivityTestBinding> {

    @InjectPresenter
    TestPresenter testPresenter;
    @InjectPresenter
    TestPresenterTow testPresenterTow;

    @Override
    protected void initView() {
        viewBinding.tvTextTest.setText(testPresenter.getTest());
        viewBinding.tvContent.setText(testPresenterTow.getTest());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(viewBinding.fragment.getId(), new TestFragment(), "test");
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected ActivityTestBinding getViewBinding() {
        return ActivityTestBinding.inflate(getLayoutInflater());
    }
}
