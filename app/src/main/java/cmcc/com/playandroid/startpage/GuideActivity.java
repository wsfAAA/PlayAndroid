package cmcc.com.playandroid.startpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.blankj.utilcode.util.SPUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.main.MainActivity;
import playandroid.cmcc.com.baselibrary.util.GlideImageLoader;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.m_banner)
    Banner mBanner;
    @BindView(R.id.mBtn_start)
    Button mBtnStart;

    private ArrayList<Integer> dataPic = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        dataPic.clear();
        dataPic.add(R.drawable.one);
        dataPic.add(R.drawable.tow);
        dataPic.add(R.drawable.three);


        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(dataPic);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(false);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (dataPic.size()-1==position&&mBtnStart!=null){
                    mBtnStart.setVisibility(View.VISIBLE);
                }else {
//                    mBtnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick(R.id.mBtn_start)
    public void onViewClicked() {
        SPUtils.getInstance().put(WelcomeActivity.IS_GUIDE, true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
