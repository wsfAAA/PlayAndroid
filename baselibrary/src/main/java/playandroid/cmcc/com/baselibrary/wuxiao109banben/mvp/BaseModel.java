package playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp;


/**
 * Created by wsf on 2018/9/5.
 */

public abstract class BaseModel<P extends IBasePresenter> {
    protected final String TAG = this.getClass().getSimpleName();
    protected P mPresenter;

    public P getmPresenter() {
        return this.mPresenter;
    }

    public void setmPresenter(P mPresenter) {
        this.mPresenter = mPresenter;
    }

    public BaseModel() {
    }

    public BaseModel(P mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void onDestroy() {
    }
}
