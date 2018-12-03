package cmcc.com.playandroid.startpage;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.main.MainActivity;
import playandroid.cmcc.com.baselibrary.base.jadapter.basemvp.BaseActivity;
import playandroid.cmcc.com.baselibrary.timertask.BaseTimerTask;
import playandroid.cmcc.com.baselibrary.timertask.ITimerListener;

public class WelcomeActivity extends BaseActivity implements ITimerListener {

    public final static String IS_GUIDE = "IS_GUIDE";

    @BindView(R.id.mImg_welcome)
    ImageView mImgWelcome;
    @BindView(R.id.mBtn_start)
    Button mBtnStart;
    @BindView(R.id.mTv_time)
    TextView mTvTime;
    private Timer mTimer;
    private long mCount = 5;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
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


    @OnClick({R.id.mTv_time, R.id.mBtn_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTv_time:
            case R.id.mBtn_start:
                mTimer.cancel();
                startActivtiy();
                break;
        }
    }

    private void startActivtiy() {
        boolean aBoolean = SPUtils.getInstance().getBoolean(IS_GUIDE);
        if (!aBoolean) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }
}
