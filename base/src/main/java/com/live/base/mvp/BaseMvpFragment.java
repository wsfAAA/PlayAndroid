package com.live.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMvpFragment extends Fragment implements BaseView {

    protected Context mContext;
    private List<BasePresenter> mPresenters = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenters = MvpUtil.initPresenter(this);
        initView();
        return View.inflate(mContext, getLayoutRes(), null);
    }

    protected abstract void initView();

    protected abstract int getLayoutRes();


    @Override
    public void onDestroy() {
        super.onDestroy();
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();  // 解绑
        }
    }

}
