package com.playandroid.newbase.test;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.playandroid.newbase.databinding.ActivityMainBinding;
import com.playandroid.newbase.mvp.BaseMvpActivity;
import com.playandroid.newbase.net.RxClient;
import com.playandroid.newbase.net.callback.RxCallBack;
import com.playandroid.newbase.net.http.OkhttpRequest;

public class MainActivity extends BaseMvpActivity<ActivityMainBinding> {

    @Override
    protected void initView() {
        viewBinding.tvText.setText("点击跳转");
        viewBinding.tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
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
