package com.live.base.mvp;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseMvpActivity<T extends ViewBinding> extends FragmentActivity implements BaseView {
    private List<BasePresenter> mPresenters = new ArrayList<>();
    protected T viewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBefore();
        viewBinding = getViewBinding();
        setContentView(viewBinding.getRoot());
        mPresenters = MvpUtil.initPresenter(this);
        initView();
    }

    protected abstract void initView();

    protected abstract T getViewBinding();

    /**
     * 初始化view 之前
     */
    protected void initViewBefore() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();  // 解绑
        }
    }

//    /**
//     * 通过反射 获取 Presenter
//     */
//    private void initPresenter() {
//        Field[] fields = this.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);  //获取 InjectPresenter 注解
//            if (injectPresenter != null) {
//                try {
//                    Class<? extends BasePresenter> presenterClazz = null;   // 创建注入
//                    try {
//                        presenterClazz = (Class<? extends BasePresenter>) field.getType();
//                    } catch (Exception e) {
//                        throw new RuntimeException("No support inject presenter type " + field.getType().getName()); // 其它注解
//                    }
//
//                    String simpleName = presenterClazz.getSuperclass().getSimpleName();
//                    if (!"BasePresenter".equals(simpleName)) {  // 获取继承的父类，如果不是 继承 BasePresenter 抛异常
//                        throw new RuntimeException("InjectPresenter 必须只能给继承自 BasePresenter 的Presenter使用 ！" + simpleName);
//                    }
//
//                    BasePresenter basePresenter = presenterClazz.newInstance(); // 创建 Presenter 对象
//                    basePresenter.attach(this);
//                    field.setAccessible(true);
//                    field.set(this, basePresenter);
//                    mPresenters.add(basePresenter);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
