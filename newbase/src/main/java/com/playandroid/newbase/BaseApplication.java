package com.playandroid.newbase;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        Utils.init(this);
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }
}
