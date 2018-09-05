package playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp;

import android.content.Context;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import playandroid.cmcc.com.baselibrary.wuxiao109banben.vu.BaseVu;

/**
 * Created by wsf on 2018/9/5.
 */
public class BaseMvpXVu<P extends BasePresenterX> extends BaseVu implements IBaseDelegateX<P> {
    protected P mPresenter = this.bindPresenter();

    public BaseMvpXVu() {
    }

    public void init(Context context) {
        super.init(context);
    }

    public void bindView() {
    }

    public void onDestroy() {
        super.onDestroy();
        if(this.mPresenter != null) {
            this.mPresenter.onDestroy();
        }

        this.mPresenter = null;
    }

    public P bindPresenter() {
        BasePresenterX basePresenterX = null;

        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if(t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType)t).getActualTypeArguments();
                Class<BasePresenterX> vClass = (Class)p[0];
                basePresenterX = (BasePresenterX)vClass.newInstance();
                basePresenterX.attachView(this);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return (P) basePresenterX;
    }
}
