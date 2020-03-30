package com.live.base.mvp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * BasePresenter 基类
 * 1、通过反射创建model
 * 2、对 view 的绑定和解除绑定 防止   Activity -> Presenter ,Presenter 持有了 Activity 内存泄漏
 */
public class BasePresenter<V extends BaseView> implements IBasePresenter {
    private V mView;
    private List<BaseModel> mModels = new ArrayList<>();
    public void attach(V view) {
        this.mView = view;
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectModel injectModel = field.getAnnotation(InjectModel.class);  //获取 InjectPresenter 注解
            if (injectModel != null) {
                try {
                    Class<? extends BaseModel> baseMoedel = null;   // 创建注入
                    try {
                        baseMoedel = (Class<? extends BaseModel>) field.getType();
                    } catch (Exception e) {
                        throw new RuntimeException("No support inject BaseModel type " + field.getType().getName()); // 其它注解
                    }

                    String simpleName = baseMoedel.getSuperclass().getSimpleName();
                    if (!"BaseModel".equals(simpleName)) {  // 获取继承的父类，如果不是 继承 BasePresenter 抛异常
                        throw new RuntimeException("InjectModel 必须只能给继承自 BaseModel 的Model使用 ！" + simpleName);
                    }

                    BaseModel baseModel = baseMoedel.newInstance(); // 创建 Presenter 对象
                    //给对应的 InjectModel 注解设置对象
                    field.setAccessible(true);
                    field.set(this, baseModel);
                    mModels.add(baseModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void detach() {
        this.mView = null;
        for (int i = 0; i < mModels.size(); i++) {
            mModels.get(i).onDestroy();
        }
    }
    public V getView() {
        return mView;
    }
}
