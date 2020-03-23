package com.playandroid.newbase.test;

import com.playandroid.newbase.TestPresenter;
import com.playandroid.newbase.databinding.FratmentTestLayoutBinding;
import com.playandroid.newbase.mvp.BaseMvpFragment;
import com.playandroid.newbase.mvp.InjectPresenter;

public class TestFragment extends BaseMvpFragment<FratmentTestLayoutBinding> {

    @InjectPresenter
    TestPresenter testPresenter;

    @Override
    protected FratmentTestLayoutBinding getViewBinding() {
        return FratmentTestLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        viewBinding.tvFragmentText.setText(testPresenter.getTest()+"    fragment测试");
    }
}
