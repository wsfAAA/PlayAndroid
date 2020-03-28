package com.playandroid.newbase.mvp01.test;

import com.playandroid.newbase.databinding.FratmentTestLayoutBinding;
import com.playandroid.newbase.mvp01.BaseMvpFragment;
import com.playandroid.newbase.mvp01.InjectPresenter;

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
