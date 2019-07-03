package com.example.main_module;


import android.util.Log;

import playandroid.cmcc.com.baselibrary.base.BaseApplication;

/**
 * Created by wsf on 2018/9/6.
 * 主项目的 Application
 */
public class MainApplictaion extends BaseApplication  {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("wsf","MainApplictaion  onCreate");
    }
}
