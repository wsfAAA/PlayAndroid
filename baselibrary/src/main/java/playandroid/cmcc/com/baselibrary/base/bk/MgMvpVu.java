package playandroid.cmcc.com.baselibrary.base.bk;

import android.content.Context;


import butterknife.ButterKnife;
import playandroid.cmcc.com.baselibrary.base.mvp.IBaseDelegate;
import playandroid.cmcc.com.baselibrary.base.mvp.IBasePresenter;
import playandroid.cmcc.com.baselibrary.base.mvp.IBaseView;
import playandroid.cmcc.com.baselibrary.base.vu.BaseVu;

/**
 * Created by yutao on 2018/6/21.
 */

public abstract class MgMvpVu<V extends IBaseView, P extends IBasePresenter> extends BaseVu implements IBaseDelegate<V, P> {

    protected P mPresenter;

    public MgMvpVu() {
        mPresenter = bindPresenter();
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this, view);
    }
}
