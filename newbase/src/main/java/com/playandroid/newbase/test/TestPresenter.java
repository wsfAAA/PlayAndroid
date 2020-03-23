package com.playandroid.newbase.test;

import com.playandroid.newbase.mvp.BasePresenter;

public class TestPresenter extends BasePresenter<TestActivity, TestModel> {

    public String getTest(){
        return getModel().getTest();
    }
}
