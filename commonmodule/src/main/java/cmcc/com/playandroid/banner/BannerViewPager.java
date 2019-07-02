package cmcc.com.playandroid.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import cmcc.com.playandroid.banner.callback.IBannerOnClick;
import cmcc.com.playandroid.banner.callback.ILoaderImage;
import cmcc.com.playandroid.banner.transformer.ZoomPageTransformer;
import playandroid.cmcc.com.commonmodule.R;

/**
 * Created by wsf on 2019/1/11.
 */

public class BannerViewPager extends FrameLayout implements View.OnTouchListener, ViewPager.OnPageChangeListener {

    public static final int BANNER_3D_GALLERY_STYLE = 1; //3D 画廊效果  使用此效果 建议使用 addViewPagerPageMargin、addViewPagerRightLeftPadding
    public static final int BANNER_GALLERY_STYLE = 2; //平面 画廊效果   使用此效果 建议使用 addViewPagerPageMargin、addViewPagerRightLeftPadding
    public static final int BANNER_NO_STYLE = 3; //普通banner效果       使用此效果 不建议使用 addViewPagerPageMargin、addViewPagerRightLeftPadding

    public static final int DELAY_TIME = 4;

    private Context mContext;
    private List<Object> mImgaData;
    private ViewPager mViewPager;
    private View mLayout;
    private LinearLayout mLineIndicator;          //指示器 容器
    private ImageView[] mImageView;               //指示器图片 imageview
    private int mCurrentIndex = 0;             //当前page

    private int resId_indicator_press = R.drawable.ic_banner_point_press;
    private int resId_indicator = R.drawable.ic_banner_point;
    private boolean isAutoPlay = true;
    private int startCurrentIndex;
    private int mDelayTime = 2000;                        //自动轮播时间间隔
    private BannerPagerAdapter mBannerAdapter;
    private BannerHandler mBannerHandler;

    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    public BannerViewPager(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    private static class BannerHandler extends Handler {
        private final WeakReference<BannerViewPager> bannerHandler;

        private BannerHandler(BannerViewPager bannerViewPager) {
            bannerHandler = new WeakReference<BannerViewPager>(bannerViewPager);
        }

        @Override
        public void handleMessage(Message msg) {
            BannerViewPager bannerViewPager = bannerHandler.get();
            if (bannerViewPager.mViewPager == null) {
                return;
            }
            if (msg.what == DELAY_TIME) {
                int index = bannerViewPager.mViewPager.getCurrentItem() + 1;//下一个页
                bannerViewPager.mCurrentIndex = index % bannerViewPager.mImgaData.size();
                bannerViewPager.mViewPager.setCurrentItem(index);
                removeMessages(DELAY_TIME);
                sendEmptyMessageDelayed(DELAY_TIME, bannerViewPager.mDelayTime);
            }
        }
    }


    /**
     * 改变指示器
     *
     * @param selectItemsIndex
     */
    private void setImageBackground(int selectItemsIndex) {
        if (mLineIndicator != null && mLineIndicator.getVisibility() == View.VISIBLE && mImageView != null) {
            for (int i = 0; i < mImageView.length; i++) {
                if (i == selectItemsIndex) {
                    mImageView[i].setImageResource(resId_indicator_press);
                } else {
                    mImageView[i].setImageResource(resId_indicator);
                }
            }
        }
    }


    //////////////////////////////////////////对外公开的方法////////////////////////////////////////

    /**
     * initBanner首先调用  是否使用画廊效果
     *
     * @return
     */
    public BannerViewPager initBanner(List<Object> mImgaData) {
        this.mImgaData = mImgaData;
        mLayout = LayoutInflater.from(mContext).inflate(R.layout.banner_view_layout, null);
        mViewPager = (ViewPager) mLayout.findViewById(R.id.viewPager);
        mLineIndicator = (LinearLayout) mLayout.findViewById(R.id.lineIndicator);

        if (mBannerHandler == null) {
            mBannerHandler = new BannerHandler(this);
        }

        if (mBannerAdapter == null) {
            mBannerAdapter = new BannerPagerAdapter(mContext);
        }

        mBannerAdapter.setImageData(mImgaData);
        mViewPager.setAdapter(mBannerAdapter);

        startCurrentIndex = mImgaData.size() * 100;

        mCurrentIndex = startCurrentIndex % mImgaData.size(); // 初始化banner 初始位置 ，默认是 0

        mViewPager.setCurrentItem(startCurrentIndex);
        mViewPager.setOffscreenPageLimit(2);//设置预加载的数量，这里设置了2,会预加载中心item左边两个Item和右边两个Item
        mViewPager.setOnTouchListener(this);
        mViewPager.setOnPageChangeListener(this);


        if (mLayout != null) {
            removeAllViews();
            this.addView(mLayout);
        }
        return this;
    }


    /**
     * banner
     *
     * @return
     */
    public BannerViewPager addStyle(int styleType) {
        if (mViewPager != null) {
            switch (styleType) {
                case BANNER_3D_GALLERY_STYLE:
                    // 画廊效果 android:clipChildren="false" 必须添加 不限制View的布局，已达到边界绘制效果
                    mViewPager.setClipChildren(false);
                    mViewPager.setPageTransformer(true, new ZoomPageTransformer());
                    break;
                case BANNER_GALLERY_STYLE:
                    mViewPager.setClipChildren(false);
                    break;
                case BANNER_NO_STYLE:
                    mViewPager.setClipChildren(true);
                    break;
            }
        }
        return this;
    }

    /**
     * 添加 viewpager item 间距
     *
     * @param margin itme之间间隙
     */
    public BannerViewPager addViewPagerPageMargin(float margin) {
        if (mViewPager != null) {
            mViewPager.setPageMargin(SizeUtils.dp2px(margin));// viewpage item之间 间距
        }
        return this;
    }

    /**
     * 添加 viewpage 左右边距
     *
     * @param margin
     * @return
     */
    public BannerViewPager addViewPagerRightLeftPadding(float margin) {
        if (mViewPager != null) {
            mViewPager.setPadding(SizeUtils.dp2px(margin), 0, SizeUtils.dp2px(margin), 0);
        }
        return this;
    }

    /**
     * 添加指示器
     *
     * @param distance 指示器 左右间距
     * @return
     */
    public BannerViewPager addIndicator(int distance) {
        mLineIndicator.removeAllViews();
        mImageView = new ImageView[mImgaData.size()];
        for (int i = 0; i < mImgaData.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(SizeUtils.dp2px(distance) / 2, 0, SizeUtils.dp2px(distance) / 2, 0);
            imageView.setLayoutParams(params);
            if (i == mCurrentIndex) {
                imageView.setImageResource(resId_indicator_press);
            } else {
                imageView.setImageResource(resId_indicator);
            }
            mImageView[i] = imageView;
            mLineIndicator.addView(imageView);
        }
        return this;
    }

    /**
     * 添加指示器
     *
     * @param distance           指示器 左右间距
     * @param indicator_press    选择的图片地址
     * @param indicator_no_press 没有被选择地址
     * @return
     */
    public BannerViewPager addIndicator(int distance, int indicator_press, int indicator_no_press) {
        this.resId_indicator_press = indicator_press;
        this.resId_indicator = indicator_no_press;
        mLineIndicator.removeAllViews();
        mImageView = new ImageView[mImgaData.size()];
        for (int i = 0; i < mImgaData.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(SizeUtils.dp2px(distance) / 2, 0, SizeUtils.dp2px(distance) / 2, 0);
            imageView.setLayoutParams(params);
            if (i == mCurrentIndex) {
                imageView.setImageResource(resId_indicator_press);
            } else {
                imageView.setImageResource(resId_indicator);
            }
            mImageView[i] = imageView;
            mLineIndicator.addView(imageView);
        }
        return this;
    }

    /**
     * 添加 指示器距离底部 距离
     *
     * @param paddBottom 单位dp
     * @return
     */
    public BannerViewPager addIndicatorBottom(int paddBottom) {
        if (mLineIndicator != null) {
            mLineIndicator.setPadding(0, 0, 0, SizeUtils.dp2px(paddBottom));
        }
        return this;
    }

    /**
     * 是否显示指示器
     *
     * @return
     */
    public BannerViewPager isShowIndicator(int visible) {
        if (mLineIndicator != null) {
            mLineIndicator.setVisibility(visible);
        }
        return this;
    }

    /**
     * 添加指示器位置
     *
     * @param gravity
     * @return
     */
    public BannerViewPager addIndicatorGravity(int gravity) {
        if (mLineIndicator != null) {
            mLineIndicator.setGravity(gravity);
        }
        return this;
    }


    /**
     * 是否自动轮播
     *
     * @param isAutoPlay true是,fale不
     * @return
     */
    public BannerViewPager isAutoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
        return this;
    }

    /**
     * 自动轮播时间间隔
     *
     * @param delayTime true是,fale不
     * @return
     */
    public BannerViewPager addDelayTime(int delayTime) {
        this.mDelayTime = delayTime;
        return this;
    }


    /**
     * 开始轮播
     *
     * @return
     */
    public void startBanner() {
        if (isAutoPlay && mBannerHandler != null) {
            mBannerHandler.removeMessages(DELAY_TIME);
            mBannerHandler.sendEmptyMessageDelayed(DELAY_TIME, mDelayTime);
        }
    }

    /**
     * 停止轮播
     *
     * @return
     */
    public void stopBanner() {
        if (isAutoPlay && mBannerHandler != null) {
            mBannerHandler.removeMessages(DELAY_TIME);
            mBannerHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                stopBanner();
                break;
            case MotionEvent.ACTION_DOWN:
                stopBanner();
                break;
            case MotionEvent.ACTION_UP:
                startBanner();
                break;
            case MotionEvent.ACTION_CANCEL:
                startBanner();
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int i = position % mImgaData.size();
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrolled(i, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentIndex = position % mImgaData.size();
        setImageBackground(mCurrentIndex);
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(mCurrentIndex);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    /**
     * banner itme 点击监听
     *
     * @param iBannerOnClick
     * @return
     */
    public BannerViewPager addBannerOnClick(IBannerOnClick iBannerOnClick) {
        if (mBannerAdapter != null) {
            mBannerAdapter.setBannerOnClick(iBannerOnClick);
        }
        return this;
    }

    /**
     * 添加图片加载器
     *
     * @param loaderImage
     */
    public BannerViewPager addLoaderImage(ILoaderImage loaderImage) {
        if (mBannerAdapter != null) {
            mBannerAdapter.addLoaderImage(loaderImage);
        }
        return this;
    }

    /**
     * viewpager OnPageChangeListener 监听
     *
     * @param onPageChangeListener
     */
    public BannerViewPager addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
        return this;
    }
}
