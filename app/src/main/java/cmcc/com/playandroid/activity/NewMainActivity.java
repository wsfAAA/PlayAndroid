package cmcc.com.playandroid.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.adapter.NewMainViewPageAdapter;
import cmcc.com.playandroid.presenter.NewMainPresenter;
import playandroid.cmcc.com.baselibrary.common.CommonFinal;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpActivity;
import playandroid.cmcc.com.baselibrary.ui.ScrollRecyclerView;
import playandroid.cmcc.com.baselibrary.view.CustomScrollViewPager;

public class NewMainActivity extends BaseMvpActivity<NewMainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.m_img_open_deawer)
    ImageView mImgOpenDeawer;
    @BindView(R.id.m_img_search)
    ImageView mImgSearch;
    @BindView(R.id.m_new_main_toolbar)
    Toolbar mNewMainToolbar;
    @BindView(R.id.m_fab)
    FloatingActionButton mFab;
    @BindView(R.id.m_nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.m_new_main_viewpager)
    CustomScrollViewPager mNewMainViewpager;
    @BindView(R.id.m_bottomNavigation)
    BottomNavigationView mBottomNavigation;
    @BindView(R.id.m_tv_page_title)
    TextView mTvPageTitle;

    private Fragment[] mFragments;
    private NewMainViewPageAdapter mNewMainViewPageAdapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_new_main;
    }

    @Override
    protected void initView() {
        mFragments = mBasePresenter.initFragment();
        mNewMainToolbar.setContentInsetsAbsolute(0, 0);  //去除Toolbar默认左边距
        mNavView.setNavigationItemSelectedListener(this);

        mNewMainViewPageAdapter = new NewMainViewPageAdapter(getSupportFragmentManager(), mFragments);
        mNewMainViewpager.setAdapter(mNewMainViewPageAdapter);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                changeFragment(item.getItemId(), item.getTitle().toString());
                return true;
            }
        });
        mNewMainViewpager.setOffscreenPageLimit(2);

        mBottomNavigation.setSelectedItemId(R.id.new_main_menu_home);
        changeFragment(R.id.new_main_menu_home, "首页");

        mNewMainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBottomNavigation.setSelectedItemId(R.id.new_main_menu_discover);
                        mTvPageTitle.setText("发现");
                        break;
                    case 1:
                        mBottomNavigation.setSelectedItemId(R.id.new_main_menu_home);
                        mTvPageTitle.setText("首页");
                        break;
                    case 2:
                        mBottomNavigation.setSelectedItemId(R.id.new_main_menu_knowledge);
                        mTvPageTitle.setText("知识体系");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mNewMainViewpager.setNoScroll(true);

        ScrollRecyclerView.setRecyclerCallBack(new ScrollRecyclerView.RecyclerCallBack() {
            @Override
            public void onScrolledUp() {
                mFab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onScrolledDown() {
                mFab.setVisibility(View.GONE);
            }

            @Override
            public void onScrolledToTop() {
                mFab.setVisibility(View.GONE);
            }

            @Override
            public void onScrolledToBottom() {
                mFab.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public NewMainPresenter creatPersenter() {
        return new NewMainPresenter();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);   //关闭侧拉
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // TODO: 2018/12/29 侧拉页面菜单id
        int id = item.getItemId();
        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        return true;
    }

    private void changeFragment(int itemId, String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvPageTitle.setText(title);
        }
        switch (itemId) {
            case R.id.new_main_menu_discover:   //发现
                mNewMainViewpager.setCurrentItem(0);
                break;
            case R.id.new_main_menu_home:       //首页
                mNewMainViewpager.setCurrentItem(1);
                break;
            case R.id.new_main_menu_knowledge:   //知识体系
                mNewMainViewpager.setCurrentItem(2);
                break;
        }
    }

    @OnClick({R.id.m_img_open_deawer, R.id.m_img_search, R.id.m_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_img_open_deawer:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START); //打开侧拉
                }
                break;
            case R.id.m_img_search:
                ToastUtils.showShort("搜索");
                ARouter.getInstance().build(CommonFinal.AROUTER_SEARCH).navigation();
                break;
            case R.id.m_fab:
                if (mNewMainViewPageAdapter != null) {
                    mNewMainViewPageAdapter.scrollToPosition(0);
                }
                break;
        }
    }
}
