package playandroid.cmcc.com.baselibrary.base.mvp;

/**
 * Created by yutao on 2018/5/21.
 */

public abstract class BaseModel<P extends IBasePresenter> {

    protected final String TAG=getClass().getSimpleName();

    protected P mPresenter;

    public P getmPresenter() {
        return mPresenter;
    }

    public void setmPresenter(P mPresenter) {
        this.mPresenter = mPresenter;
    }

    public BaseModel(){}

    public  BaseModel(P mPresenter){
        this.mPresenter=mPresenter;
    }

    public void onDestroy(){};



}
