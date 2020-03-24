package com.playandroid.newbase.test;

import com.playandroid.newbase.mvp.BasePresenter;

public class TestPresenterTow extends BasePresenter<TestActivity, TestModelTow> {

    public String getTest(){
        return getModel().getTest();
    }
}
