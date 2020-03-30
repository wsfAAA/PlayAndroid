package com.live.base.mvp;

import android.util.Log;

/**
 * Model 基类
 */
public class BaseModel {
    public void onDestroy() {
        Log.e("wsf","BaseModel 被销毁");
    }
}
