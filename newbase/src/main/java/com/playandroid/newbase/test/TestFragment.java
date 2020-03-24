package com.playandroid.newbase.test;

import com.playandroid.newbase.databinding.FratmentTestLayoutBinding;
import com.playandroid.newbase.mvp.BaseMvpFragment;
import com.playandroid.newbase.mvp.InjectPresenter;

/**
 * BaseMvpFragment 使用
 */
public class TestFragment extends BaseMvpFragment<FratmentTestLayoutBinding> {

    @InjectPresenter
    TestPresenter testPresenter;

    @Override
    protected FratmentTestLayoutBinding getViewBinding() {
        return FratmentTestLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        viewBinding.tvFragmentText.setText(testPresenter.getTestTow()+"    fragment测试");
    }
}
