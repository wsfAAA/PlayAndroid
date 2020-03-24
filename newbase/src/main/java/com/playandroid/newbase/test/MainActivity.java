package com.playandroid.newbase.test;

import android.content.Intent;
import android.view.View;

import com.playandroid.newbase.databinding.ActivityMainBinding;
import com.playandroid.newbase.mvp.BaseMvpActivity;

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
    }

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}