package playandroid.cmcc.com.baselibrary.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by wsf on 2018/11/14.
 * 需要使用mvp模式可以继承BaseMvpActivity，无需只需要继承BaseActivity
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mBasePresenter = TUtil.getT(this, 0);//通过反射 绑定Presenter
        mBasePresenter = creatPersenter();
        if (mBasePresenter != null) {
            mBasePresenter.addActivityInstanc(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
            mBasePresenter = null;
        }
    }


    public abstract P creatPersenter();
}
