package com.playandroid.newbase.mvp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * BasePresenter 基类
 * 1、通过反射创建model
 * 2、对 view 的绑定和解除绑定 防止   Activity -> Presenter ,Presenter 持有了 Activity 内存泄漏
 */
public class BasePresenter<V extends BaseView, M extends BaseModel> {
    private V mView;
    private M mModel;

    public void attach(V view) {
        this.mView = view;

        // 通过反射创建model
        Type[] params = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        try {
            mModel = (M) ((Class) params[1]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void detach() {
        this.mView = null;
    }

    public M getModel() {
        return mModel;
    }

    public V getView() {
        return mView;
    }
}
