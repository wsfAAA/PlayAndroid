package playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import playandroid.cmcc.com.baselibrary.wuxiao109banben.vu.Vu;

/**
 * Created by wsf on 2018/9/5.
 */

public class BasePresenterX<V extends Vu, M extends BaseModel> implements IBasePresenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected V baseView;
    protected M baseModel;

    protected M bindModel() {
       BaseModel baseModel = null;

        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if(t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType)t).getActualTypeArguments();
                Class<BaseModel> vClass = (Class)p[1];
                baseModel = vClass.newInstance();
                baseModel.setmPresenter(this);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return (M) baseModel;
    }

    public BasePresenterX() {
    }

    public BasePresenterX(V v) {
        this.attachView(v);
    }

    public void attachView(V vu) {
        this.baseView = vu;
        this.baseModel = this.bindModel();
    }

    public void detachView() {
        this.baseView = null;
    }

    public void onDestroy() {
        if(this.baseModel != null) {
            this.baseModel.onDestroy();
            this.baseModel = null;
        }

        this.detachView();
    }

    public V getVu() {
        return this.baseView;
    }
}