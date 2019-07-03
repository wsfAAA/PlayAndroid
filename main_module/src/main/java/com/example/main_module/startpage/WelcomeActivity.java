package com.example.main_module.startpage;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.example.main_module.R;
import com.example.main_module.R2;
import com.example.main_module.mvp.view.NewMainActivity;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import playandroid.cmcc.com.baselibrary.base.BaseActivity;
import playandroid.cmcc.com.baselibrary.timertask.BaseTimerTask;
import playandroid.cmcc.com.baselibrary.timertask.ITimerListener;

public class WelcomeActivity extends BaseActivity implements ITimerListener {

    public final static String IS_GUIDE = "IS_GUIDE";

    @BindView(R2.id.mImg_welcome)
    ImageView mImgWelcome;
    @BindView(R2.id.mBtn_start)
    Button mBtnStart;
    @BindView(R2.id.mTv_time)
    TextView mTvTime;
    @BindView(R2.id.rootView)
    RelativeLayout mRootView;
    private Timer mTimer;
    private long mCount = 1;
    private boolean aBoolean;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        aBoolean = SPUtils.getInstance().getBoolean(IS_GUIDE);
        if (!aBoolean) {
            mBtnStart.setVisibility(View.GONE);
            mTvTime.setVisibility(View.GONE);
        } else {
            mBtnStart.setVisibility(View.VISIBLE);
            mTvTime.setVisibility(View.VISIBLE);
        }
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }


    @Override
    public void onTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTime != null) {
                    mTvTime.setText(MessageFormat.format("跳过 {0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            startActivtiy();
                        }
                    }
                }
            }
        });
    }


    @OnClick({R2.id.mTv_time, R2.id.mBtn_start})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.mTv_time || i == R.id.mBtn_start) {
            if (mTimer != null) {
                mTimer.cancel();
            }
            startActivtiy();

        }
    }

    private void startActivtiy() {
        if (!aBoolean) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, NewMainActivity.class));
        }
        finish();
    }
}
