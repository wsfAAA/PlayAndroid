package com.playandroid.newbase.test;

import com.playandroid.newbase.databinding.ActivityTestBinding;
import com.playandroid.newbase.mvp.BaseMvpActivity;
import com.playandroid.newbase.mvp.InjectPresenter;
import com.playandroid.newbase.test.contract.TestViewContract;

import androidx.fragment.app.FragmentTransaction;

/**
 * BaseMvpActivity 使用
 */
public class TestActivity extends BaseMvpActivity<ActivityTestBinding> implements TestViewContract {

    @InjectPresenter
    TestPresenter testPresenter;
    @InjectPresenter
    TestPresenterTow testPresenterTow;

    @Override
    protected void initView() {
        viewBinding.tvContent.setText(testPresenterTow.getTest() + "  activity测试数据");
        testPresenter.getTest();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(viewBinding.fragment.getId(), new TestFragment(), "test");
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected ActivityTestBinding getViewBinding() {
        return ActivityTestBinding.inflate(getLayoutInflater());
    }

    @Override
    public void setTest(String message) {
        viewBinding.tvTextTest.setText(message + "   activity测试数据");
    }
}
