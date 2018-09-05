package playandroid.cmcc.com.baselibrary.wuxiao109banben.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wsf on 2018/9/5.
 */
public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    public Fragment getItem(int arg0) {
        return (Fragment)this.mFragments.get(arg0);
    }

    public int getCount() {
        return this.mFragments.size();
    }
}

