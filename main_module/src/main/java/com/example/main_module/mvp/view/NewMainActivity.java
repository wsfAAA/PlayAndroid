package com.example.main_module.mvp.view;

import android.content.Intent;
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
import com.ccm.idataservice.IServiceManager;
import com.ccm.idataservice.TestCallBack;
import com.ccm.idataservice.search.ISearchService;
import com.example.main_module.R;
import com.example.main_module.R2;
import com.example.main_module.adapter.NewMainViewPageAdapter;
import com.example.main_module.mvp.presenter.NewMainPresenter;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.activity.DetailsContentActivity;
import cmcc.com.playandroid.common.CommonFinal;
import cmcc.com.playandroid.common.EventBusMessage;
import cmcc.com.playandroid.view.CustomScrollViewPager;
import cmcc.com.playandroid.view.ScrollRecyclerView;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpActivity;

public class NewMainActivity extends BaseMvpActivity<NewMainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener ,TestCallBack {

    @BindView(R2.id.m_img_open_deawer)
    ImageView mImgOpenDeawer;
    @BindView(R2.id.m_img_search)
    ImageView mImgSearch;
    @BindView(R2.id.m_new_main_toolbar)
    Toolbar mNewMainToolbar;
    @BindView(R2.id.m_fab)
    FloatingActionButton mFab;
    @BindView(R2.id.m_nav_view)
    NavigationView mNavView;
    @BindView(R2.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R2.id.m_new_main_viewpager)
    CustomScrollViewPager mNewMainViewpager;
    @BindView(R2.id.m_bottomNavigation)
    BottomNavigationView mBottomNavigation;
    @BindView(R2.id.m_tv_page_title)
    TextView mTvPageTitle;

    private Fragment[] mFragments;
    private NewMainViewPageAdapter mNewMainViewPageAdapter;
    private ISearchService iSearchService;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_new_main;
    }

    //    @Subscribe(sticky = true)  通过eventbus 组件通信
    @Subscribe
    public void onEvent(EventBusMessage eventBusMessage) {
        if (eventBusMessage.getType().equals(EventBusMessage.EVENT_LOGIN_SUCCEED)) {
            ToastUtils.showShort(eventBusMessage.getMessage());
        } else if (eventBusMessage.getType().equals(EventBusMessage.EVENT_LOGIN_FIALUER)) {
            ToastUtils.showShort(eventBusMessage.getMessage());
        }
    }

    @Override
    protected boolean isReceiveEvent() {
        return true;
    }

    @Override
    protected void initView() {
        iSearchService = IServiceManager.getInstance().getISearchService();
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

    public void isVisible(int visible) {
        mFab.setVisibility(visible);
    }

    @Override
    public NewMainPresenter creatPersenter() {
        return new NewMainPresenter();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);   //关闭侧滑菜单
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // TODO: 2018/12/29 侧滑页面菜单id
        int id = item.getItemId();
        if (id == R.id.nav_login) { //登录
            ARouter.getInstance().build(CommonFinal.AROUTER_LOGIN).navigation();
        } else if (id == R.id.nav_collect) {//收藏
            Intent intent = new Intent(this, DetailsContentActivity.class);
            intent.putExtra(CommonFinal.INTENT_TYPE, 3);
            intent.putExtra(CommonFinal.PAGE_TITLE, "我的收藏列表");
            startActivity(intent);
        } else if (id == R.id.nav_kotlin) {
            ARouter.getInstance().build(CommonFinal.AROUTER_KOTLIN).navigation();
        }
        return true;
    }

    private void changeFragment(int itemId, String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvPageTitle.setText(title);
        }
        //发现
        //首页
        //知识体系
        if (itemId == R.id.new_main_menu_discover) {
            mNewMainViewpager.setCurrentItem(0);

        } else if (itemId == R.id.new_main_menu_home) {
            mNewMainViewpager.setCurrentItem(1);

        } else if (itemId == R.id.new_main_menu_knowledge) {
            mNewMainViewpager.setCurrentItem(2);

        }
    }

    @OnClick({R2.id.m_img_open_deawer, R2.id.m_img_search, R2.id.m_fab})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.m_img_open_deawer) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START); //打开侧滑菜单
            }

        } else if (i == R.id.m_img_search) {// TODO: 2019/3/22 使用路由跳转
//                ARouter.getInstance().build(CommonFinal.AROUTER_SEARCH).navigation();

            // TODO: 2019/3/22 组件通信 接口跳转
            //方式一
                ISearchService navigation = (ISearchService) ARouter.getInstance().build(CommonFinal.AROUTER_SEARCH_TEST).navigation();
//                navigation.goToSearch(this, "欢迎收搜");
//                方式二
            iSearchService.goToSearch(this, "欢迎收搜");
            iSearchService.testCallBack(NewMainActivity.this);
            Log.i("wsf", "iSearchService:  " + iSearchService);

        } else if (i == R.id.m_fab) {
            if (mNewMainViewPageAdapter != null) {
                mNewMainViewPageAdapter.scrollToPosition(0);
            }
        }
    }

    @Override
    public void testString(String mes) {
        if (!TextUtils.isEmpty(mes)){
            ToastUtils.showShort(mes);
        }
    }
}
