package playandroid.cmcc.com.baselibrary.base.mvp;

import android.content.Context;

import playandroid.cmcc.com.baselibrary.base.vu.BaseVu;


/**
 * Created by yutao on 2018/5/21.
 */

public abstract class ABaseMvpVu<V extends IBaseView,P extends IBasePresenter> extends BaseVu implements IBaseDelegate<V,P>{

    protected P mPresenter;

    public ABaseMvpVu(){
        mPresenter= bindPresenter();
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }


}
