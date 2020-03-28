package com.playandroid.newbase.mvp02;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MvpUtil {

    /**
     * 通过反射 获取 Presenter
     */
    public static List<BasePresenter> initPresenter(BaseView baseView) {
        List<BasePresenter> mPresenters = new ArrayList<>();
        Field[] fields = baseView.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);  //获取 InjectPresenter 注解
            if (injectPresenter != null) {
                try {
                    Class<? extends BasePresenter> presenterClazz = null;   // 创建注入
                    try {
                        presenterClazz = (Class<? extends BasePresenter>) field.getType();
                    } catch (Exception e) {
                        throw new RuntimeException("No support inject presenter type " + field.getType().getName()); // 其它注解
                    }

                    String simpleName = presenterClazz.getSuperclass().getSimpleName();
                    if (!"BasePresenter".equals(simpleName)) {  // 获取继承的父类，如果不是 继承 BasePresenter 抛异常
                        throw new RuntimeException("InjectPresenter 必须只能给继承自 BasePresenter 的Presenter使用 ！" + simpleName);
                    }

                    BasePresenter basePresenter = presenterClazz.newInstance(); // 创建 Presenter 对象
                    basePresenter.attach(baseView);
                    field.setAccessible(true);
                    field.set(baseView, basePresenter);
                    mPresenters.add(basePresenter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return mPresenters;
    }
}
