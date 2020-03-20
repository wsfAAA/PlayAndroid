package com.playandroid.newbase.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class BaseMvpActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBefore();
//        ActivityMainBinding binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
//        setContentView(binding.getRoot());
    }


    /**
     * 初始化view 之前
     */
    protected void initViewBefore(){}
}
