package playandroid.cmcc.com.baselibrary.base.mvp;

import android.content.Context;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import playandroid.cmcc.com.baselibrary.base.vu.BaseVu;


/**
 * Created by yutao on 2018/6/21.
 */

public  class BaseMvpXVu<P extends BasePresenterX> extends BaseVu implements IBaseDelegateX<P> {

    protected P mPresenter;

    public BaseMvpXVu() {
        mPresenter = bindPresenter();
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }


    @Override
    public void bindView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.onDestroy();
        }
        mPresenter=null;
    }

    @Override
    public P bindPresenter() {
        BasePresenterX basePresenterX=null;
        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                Class<BasePresenterX> vClass = (Class<BasePresenterX>) p[0];
                basePresenterX = vClass.newInstance();
                basePresenterX.attachView(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (P) basePresenterX;
    }
}
