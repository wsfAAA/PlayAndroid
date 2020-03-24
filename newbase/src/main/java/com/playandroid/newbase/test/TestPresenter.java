package com.playandroid.newbase.test;

import com.playandroid.newbase.mvp.BasePresenter;
import com.playandroid.newbase.test.contract.TestPresenterContract;
import com.playandroid.newbase.test.contract.TestViewContract;

public class TestPresenter extends BasePresenter<TestViewContract, TestModel> implements TestPresenterContract {
    @Override
    public void getTest() {
        getView().setTest(getModel().getTest());
    }

    public String getTestTow(){
        return getModel().getTest();
    }
}
