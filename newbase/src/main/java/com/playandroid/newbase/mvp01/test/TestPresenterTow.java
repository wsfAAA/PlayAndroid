package com.playandroid.newbase.mvp01.test;

import com.playandroid.newbase.mvp01.BasePresenter;

public class TestPresenterTow extends BasePresenter<TestActivity, TestModelTow> {

    public String getTest(){
        return getModel().getTest();
    }
}
