package com.playandroid.newbase;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.playandroid.newbase.databinding.ActivityMainBinding;
import com.playandroid.newbase.mvp01.BaseMvpActivity;
import com.playandroid.newbase.mvp02.test.Test02Activity;
import com.playandroid.newbase.net.RxClient;
import com.playandroid.newbase.net.callback.RxCallBack;
import com.playandroid.newbase.mvp01.test.TestActivity;

public class MainActivity extends BaseMvpActivity<ActivityMainBinding> {

    @Override
    protected void initView() {
        viewBinding.btnTextOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });

        viewBinding.btnTextTow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test02Activity.class));
            }
        });


        RxClient.builder()
                .baseUrl("https://www.wanandroid.com")
                .build()
                .rxGet("/wxarticle/chapters/json ", new RxCallBack<String>() {
                    @Override
                    public void rxOnNext(String response) {
                        Log.e("wsf", "数据:  " + response);
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        Log.e("wsf", "错误:  " + e);

                    }
                });
    }

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}
