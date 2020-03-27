package com.live.studay;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private FrameLayout frameLayoutTx;
    private FrameLayout frameLayoutCamera;
    private float viewWidth;
    private float viewHeight;
    private float bbb;
    private float top;
    private float left;
    private int screenWidth;
    private int screenHeight;
    private float scaleXWidth;
    private float scaleYHeight;
    private float translationXWidth;
    private float translationXHeight;
    private FrameLayout doAnimView;
    private RelativeLayout rl_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        frameLayoutTx = findViewById(R.id.frame_tx);
        frameLayoutCamera = findViewById(R.id.frame_camera);
        rl_layout = findViewById(R.id.rl_layout);

        frameLayoutTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doAnimView == frameLayoutTx) {
                    starAnim(frameLayoutCamera, frameLayoutTx);
                }
            }
        });

        frameLayoutCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doAnimView == frameLayoutCamera) {
                    starAnim(frameLayoutTx, frameLayoutCamera);
                }
            }
        });
        starAnim(frameLayoutCamera, frameLayoutTx);
    }

    public void starAnim(FrameLayout frameLayout, FrameLayout fullFragment) {
        doAnimView = frameLayout;
        if (frameLayout == frameLayoutCamera) {
            frameLayoutCamera.bringToFront();
        } else {
            frameLayoutTx.bringToFront();
        }
        rl_layout.bringToFront();

        bbb = 16f / 9f;
        top = ConvertUtils.dp2px(80);
        left = ConvertUtils.dp2px(10);
        viewWidth = ConvertUtils.dp2px(80);
//        viewHeight = viewWidth * bbb;   // 根据16/9计算高度
        viewHeight = ConvertUtils.dp2px(130);
        screenWidth = ScreenUtils.getScreenWidth();
        screenHeight = ScreenUtils.getScreenHeight();

        scaleXWidth = viewWidth / screenWidth; //试装 x轴缩放
        scaleYHeight = viewHeight / screenHeight; //试装 Y轴缩放
        translationXWidth = -(screenWidth / 2 - viewWidth / 2 - left); //试装 x轴平移
        translationXHeight = -(screenHeight / 2 - (viewHeight / 2 + top));//试装 y轴平移


        AnimatorSet animatorSetsuofang = new AnimatorSet();//组合动画
        ObjectAnimator scaleXX = ObjectAnimator.ofFloat(frameLayout, "scaleX", 1f, scaleXWidth);
        ObjectAnimator scaleYY = ObjectAnimator.ofFloat(frameLayout, "scaleY", 1f, scaleYHeight);
        ObjectAnimator translationXX = ObjectAnimator.ofFloat(frameLayout, "translationX", 0f, translationXWidth);
        ObjectAnimator translationYY = ObjectAnimator.ofFloat(frameLayout, "translationY", 0f, translationXHeight);

        ObjectAnimator scaleXXtow = ObjectAnimator.ofFloat(fullFragment, "scaleX", scaleXWidth, 1f);
        ObjectAnimator scaleYYtow = ObjectAnimator.ofFloat(fullFragment, "scaleY", scaleYHeight, 1f);
        ObjectAnimator translationXXtow = ObjectAnimator.ofFloat(fullFragment, "translationX", translationXWidth, 0f);
        ObjectAnimator translationYYtow = ObjectAnimator.ofFloat(fullFragment, "translationY", translationXHeight, 0f);

        animatorSetsuofang.setDuration(500);
        animatorSetsuofang.setInterpolator(new DecelerateInterpolator());
        animatorSetsuofang.play(scaleXX).with(scaleYY).with(translationXX).with(translationYY)
                .with(scaleXXtow).with(scaleYYtow).with(translationXXtow).with(translationYYtow);//两个动画同时开始
        animatorSetsuofang.start();

        animatorSetsuofang.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                rl_layout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rl_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }
}
