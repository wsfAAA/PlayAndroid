package playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp;

import android.content.Context;

import playandroid.cmcc.com.baselibrary.wuxiao109banben.vu.BaseVu;

/**
 * Created by wsf on 2018/9/5.
 */

public abstract class ABaseMvpVu<V extends IBaseView, P extends IBasePresenter> extends BaseVu implements IBaseDelegate<V, P> {
    protected P mPresenter = this.bindPresenter();

    public ABaseMvpVu() {
    }

    public void init(Context context) {
        super.init(context);
    }

    public P getPresenter() {
        return this.mPresenter;
    }
}
