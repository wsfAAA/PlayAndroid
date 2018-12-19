package playandroid.cmcc.com.baselibrary.basemvp;


import playandroid.cmcc.com.baselibrary.base.BaseActivity;

/**
 * Created by wsf on 2018/11/14.
 * 需要使用mvp模式可以继承BaseMvpActivity，无需只需要继承BaseActivity
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mBasePresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
            mBasePresenter = null;
        }
    }


    public abstract P creatPersenter();

    @Override
    protected void initView() {
        //mBasePresenter = TUtil.getT(this, 0);//通过反射 绑定Presenter
        mBasePresenter = creatPersenter();
        if (mBasePresenter != null) {
            mBasePresenter.addActivityInstanc(this);
        }
        initMvpView();
    }

    protected abstract void initMvpView();

}
