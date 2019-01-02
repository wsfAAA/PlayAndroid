package playandroid.cmcc.com.baselibrary.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import playandroid.cmcc.com.baselibrary.base.LazyBaseFragment;

/**
 * Created by wsf on 2019/1/2.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends LazyBaseFragment {

    private P mBasePresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("cesi---->", "BaseMvpFragment onViewCreated");
        mBasePresenter = creatPersenter();
        if (mBasePresenter != null) {
            mBasePresenter.addActivityInstanc((IBaseView) mContext);
        }
    }

    public abstract P creatPersenter();
}
