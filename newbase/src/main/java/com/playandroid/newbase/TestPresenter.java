package com.playandroid.newbase;

import com.playandroid.newbase.base.BasePresenter;

public class TestPresenter extends BasePresenter<TestActivity, TestModel> {

    public String getTest(){
        return getModel().getTest();
    }
}
