package playandroid.cmcc.com.baselibrary.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.R2;

/**
 * Created by wsf on 2018/12/20.
 */

public class BaseLoadingView extends RelativeLayout {

    @BindView(R2.id.m_img_loading_anim)
    ImageView mImgLoadingAnim;                  //动画view
    @BindView(R2.id.m_img_loading_center)
    ImageView mImgLoadingCenter;                //动画view
    @BindView(R2.id.m_tv_load_text)
    TextView mTvLoadText;                       //正在加载中
    @BindView(R2.id.ll_loading)
    RelativeLayout mRLoading;                   //加载中 父布局
    @BindView(R2.id.m_img_no_data)
    ImageView mImgNoData;                       //没有数据图片
    @BindView(R2.id.m_img_no_data_title)
    TextView mImgNoDataTitle;                   //没有数据title
    @BindView(R2.id.m_img_no_data_reload)
    ImageView mImgNoDataReload;                 //没有数据 点击重试
    @BindView(R2.id.m_ll_no_data)
    LinearLayout mLlNoData;                     //没有数据 父布局
    @BindView(R2.id.m_img_reload)
    ImageView mImgReload;                       //没有网络 点击重试
    @BindView(R2.id.m_ll_no_net)
    LinearLayout mLlNoNet;                      //没有网络 父布局
    private Context mContext;
    private Unbinder mUnbinder;
    private AnimatorSet mAnimatorSet;

    public BaseLoadingView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BaseLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BaseLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_loading_layout, this);
//        View view = View.inflate(mContext,R.layout.base_loading_layout,null);
//        removeAllViews();
//        addView(view);
        mUnbinder = ButterKnife.bind(this, view);
        this.showContent();
    }

    /**
     * 正在加载中
     */
    public void showLoading() {
        this.setVisibility(View.VISIBLE);
        mRLoading.setVisibility(VISIBLE);
        mLlNoData.setVisibility(GONE);
        mLlNoNet.setVisibility(GONE);
        startLoadAnimator();
    }

    /**
     * 加载完成
     */
    public void showContent() {
        this.setVisibility(View.GONE);
        mRLoading.setVisibility(GONE);
        mLlNoData.setVisibility(GONE);
        mLlNoNet.setVisibility(GONE);
        stopLoadAnimator();
    }

    /**
     * 没有数据
     */
    public void showEmptyData() {
        this.setVisibility(View.VISIBLE);
        mRLoading.setVisibility(GONE);
        mLlNoData.setVisibility(VISIBLE);
        mLlNoNet.setVisibility(GONE);
        stopLoadAnimator();
    }

    /**
     * 没有网络
     */
    public void showNetWorkError() {
        this.setVisibility(View.VISIBLE);
        mRLoading.setVisibility(GONE);
        mLlNoData.setVisibility(GONE);
        mLlNoNet.setVisibility(VISIBLE);
        stopLoadAnimator();
    }


    /**
     * 无数据、无网络 点击监听
     *
     * @param listener
     */
    public void setAnewListener(OnClickListener listener) {
        mImgNoDataReload.setOnClickListener(listener);
        mImgReload.setOnClickListener(listener);
    }

    private void startLoadAnimator() {
        mAnimatorSet = new AnimatorSet();
        AnimatorSet animatorSet1 = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator roundingObjectAnimator = ObjectAnimator.ofFloat(mImgLoadingAnim, "rotation", 0f, 180f);
        ObjectAnimator scaleXObjectAnimator = ObjectAnimator.ofFloat(mImgLoadingAnim, "scaleX", 1, 0.8f);
        ObjectAnimator scaleYObjectAnimator = ObjectAnimator.ofFloat(mImgLoadingAnim, "scaleY", 1, 0.8f);

        ObjectAnimator centerscaleXObjectAnimator = ObjectAnimator.ofFloat(mImgLoadingCenter, "scaleX", 1, 0.8f);
        ObjectAnimator centerscaleYObjectAnimator = ObjectAnimator.ofFloat(mImgLoadingCenter, "scaleY", 1, 0.8f);

        ObjectAnimator roundingObjectAnimator2 = ObjectAnimator.ofFloat(mImgLoadingAnim, "rotation", 180f, 360f);
        ObjectAnimator scaleXObjectAnimator2 = ObjectAnimator.ofFloat(mImgLoadingAnim, "scaleX", 0.8f, 1f);
        ObjectAnimator scaleYObjectAnimator2 = ObjectAnimator.ofFloat(mImgLoadingAnim, "scaleY", 0.8f, 1f);

        ObjectAnimator centerscaleXObjectAnimator2 = ObjectAnimator.ofFloat(mImgLoadingCenter, "scaleX", 0.8f, 1f);
        ObjectAnimator centerscaleYObjectAnimator2 = ObjectAnimator.ofFloat(mImgLoadingCenter, "scaleY", 0.8f, 1f);

        animatorSet1.setDuration(500);
        animatorSet1.playTogether(roundingObjectAnimator, scaleXObjectAnimator, scaleYObjectAnimator, centerscaleXObjectAnimator, centerscaleYObjectAnimator);
        animatorSet1.setInterpolator(new LinearInterpolator());
        animatorSet2.setDuration(500);
        animatorSet2.setInterpolator(new LinearInterpolator());
        animatorSet2.playTogether(roundingObjectAnimator2, scaleXObjectAnimator2, scaleYObjectAnimator2, centerscaleXObjectAnimator2, centerscaleYObjectAnimator2);
        mAnimatorSet.playSequentially(animatorSet1, animatorSet2);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mRLoading.getVisibility() == View.VISIBLE) {
                    startLoadAnimator();
                } else {
//                    stopLoadAnimator();  //StackOverflowError异常 部分手机
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimatorSet.start();
    }

    public void stopLoadAnimator() {
        if (mAnimatorSet != null && mAnimatorSet.isRunning()) {
            mAnimatorSet.cancel();
        }
    }

    public void destroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        stopLoadAnimator();
        removeAllViews();
    }
}
