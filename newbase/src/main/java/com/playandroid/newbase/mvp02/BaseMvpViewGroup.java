package com.playandroid.newbase.mvp02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseMvpViewGroup extends RelativeLayout implements BaseView {
    protected Context mContext;
    private List<BasePresenter> mPresenters = new ArrayList<>();

    public BaseMvpViewGroup(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BaseMvpViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseMvpViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View inflate = inflate(mContext, getLayoutResID(), this);
        mPresenters = MvpUtil.initPresenter(this);
        initView(inflate);
    }

    protected abstract void initView(View view);

    protected abstract int getLayoutResID();

    protected void onDestroy() {
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();
        }
//        removeAllViews();
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
