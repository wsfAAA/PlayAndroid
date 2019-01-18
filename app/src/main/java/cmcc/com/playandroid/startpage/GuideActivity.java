package cmcc.com.playandroid.startpage;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.activity.NewMainActivity;
import playandroid.cmcc.com.baselibrary.banner.BannerViewPager;
import playandroid.cmcc.com.baselibrary.banner.callback.ILoaderImage;
import playandroid.cmcc.com.baselibrary.base.BaseActivity;
import playandroid.cmcc.com.baselibrary.util.BaseUtils;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.m_banner)
    BannerViewPager mBanner;
    @BindView(R.id.mBtn_start)
    Button mBtnStart;

    private ArrayList<Object> dataPic = new ArrayList<>();


    @Override
    protected void initView() {
        dataPic.clear();
        dataPic.add(R.drawable.one);
        dataPic.add(R.drawable.tow);
        dataPic.add(R.drawable.three);

        mBanner.initBanner(dataPic)
                .addIndicator(20)
                .addIndicatorBottom(130)
                .isAutoPlay(false)
                .isShowIndicator(View.VISIBLE)
                .addIndicatorGravity(Gravity.CENTER)
                .addLoaderImage(new ILoaderImage() {
                    @Override
                    public void loaderImage(Context context, Object url, ImageView imageView) {
                        BaseUtils.loaderGlideImage(context, url, imageView);
                    }
                })
                .addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (dataPic.size() - 1 == position && mBtnStart != null) {
                            mBtnStart.setVisibility(View.VISIBLE);
                        } else {
                            mBtnStart.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                })
                .startBanner();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_guide;
    }

    @OnClick(R.id.mBtn_start)
    public void onViewClicked() {
        SPUtils.getInstance().put(WelcomeActivity.IS_GUIDE, true);
        startActivity(new Intent(this, NewMainActivity.class));
        finish();
    }
}
