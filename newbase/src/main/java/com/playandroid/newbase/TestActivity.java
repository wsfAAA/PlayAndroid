package com.playandroid.newbase;

import android.widget.TextView;

import com.playandroid.newbase.base.InjectPresenter;
import com.playandroid.newbase.base.TestBaseMvpActivity;

public class TestActivity extends TestBaseMvpActivity {

    @InjectPresenter
    TestPresenter testPresenter;

    @Override
    protected void initView() {
        TextView textView = findViewById(R.id.tv_text_test);
        textView.setText(testPresenter.getTest());
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test);
    }

}
