package com.playandroid.newbase.mvp01.test;

import com.playandroid.newbase.mvp01.BasePresenter;
import com.playandroid.newbase.mvp02.InjectModel;
import com.playandroid.newbase.mvp01.test.contract.TestPresenterContract;
import com.playandroid.newbase.mvp01.test.contract.TestViewContract;

public class TestPresenter extends BasePresenter<TestViewContract, TestModel> implements TestPresenterContract {
    @Override
    public void getTest() {
        getView().setTest(getModel().getTest());
    }

    public String getTestTow() {
        return getModel().getTest();
    }
}
