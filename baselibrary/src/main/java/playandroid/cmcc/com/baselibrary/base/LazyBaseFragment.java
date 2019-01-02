package playandroid.cmcc.com.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import playandroid.cmcc.com.baselibrary.mvp.IBaseView;

/**
 * Created by wsf on 2019/1/2.
 */

public abstract class LazyBaseFragment extends Fragment implements IBaseView{

    /**
     * Fragment的View加载完毕的标记
     */
    private boolean isViewCreated;

    /**
     * Fragment对用户可见的标记
     */
    private boolean isUIVisible;

    private Unbinder mUnbinder;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        Log.i("cesi---->", "LazyBaseFragment onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResID(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Log.i("cesi---->", "LazyBaseFragment onCreateView");
        return view;
    }

    /**
     * onViewCreated 在onCreateView 后被触发的事件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("cesi---->", "LazyBaseFragment onViewCreated");
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    /**
     * 双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
     */
    private void lazyLoad() {
        if (isViewCreated && isUIVisible) {
            onFragmentVisible();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("cesi---->", "LazyBaseFragment onDestroy");
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    protected abstract int getLayoutResID();

    protected abstract void onFragmentVisible();
}
