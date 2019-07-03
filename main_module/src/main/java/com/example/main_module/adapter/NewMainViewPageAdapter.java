package com.example.main_module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.main_module.mvp.view.DiscoverFragment;
import com.example.main_module.mvp.view.HomeFragment;
import com.example.main_module.mvp.view.KnowledgeFragment;

/**
 * Created by wsf on 2019/1/2.
 */

public class NewMainViewPageAdapter extends FragmentPagerAdapter {
    Fragment[] mFragmentData;
    private Fragment mCurrentFragment; //当前fragment

    public NewMainViewPageAdapter(FragmentManager fm, Fragment[] mFragments) {
        super(fm);
        this.mFragmentData = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentData[position];
    }

    @Override
    public int getCount() {
        return mFragmentData.length;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentFragment) {
            mCurrentFragment = (Fragment) object;
        }
    }

    public void scrollToPosition(int position) {
        if (mCurrentFragment == null) {
            return;
        }
        if (mCurrentFragment instanceof HomeFragment) {
            ((HomeFragment) mCurrentFragment).scrollToPosition(position);
        } else if (mCurrentFragment instanceof DiscoverFragment) {
            ((DiscoverFragment) mCurrentFragment).scrollToPosition(position);
        } else if (mCurrentFragment instanceof KnowledgeFragment) {

        }
    }
}
