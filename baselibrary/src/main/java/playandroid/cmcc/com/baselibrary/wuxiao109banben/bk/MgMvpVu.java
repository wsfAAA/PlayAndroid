package playandroid.cmcc.com.baselibrary.wuxiao109banben.bk;

import android.content.Context;


import butterknife.ButterKnife;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp.IBaseDelegate;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp.IBasePresenter;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp.IBaseView;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.vu.BaseVu;

/**
 * Created by wsf on 2018/9/5.
 */
public abstract class MgMvpVu<V extends IBaseView, P extends IBasePresenter> extends BaseVu implements IBaseDelegate<V, P> {
    protected P mPresenter = this.bindPresenter();

    public MgMvpVu() {
    }

    public void init(Context context) {
        super.init(context);
    }

    public P getPresenter() {
        return this.mPresenter;
    }

    public void bindView() {
        ButterKnife.bind(this, this.view);
    }
}
