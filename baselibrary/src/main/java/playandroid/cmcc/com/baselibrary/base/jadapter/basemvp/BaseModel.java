package playandroid.cmcc.com.baselibrary.base.jadapter.basemvp;


/**
 * Created by wsf on 2018/11/6.
 */

public class BaseModel<P extends BasePresenter> {

    protected final String TAG = getClass().getSimpleName();

    protected P mBasePresenter;

    public P getmPresenter() {
        return mBasePresenter;
    }

    /**
     * 绑定 Presenter
     *
     * @param mPresenter
     */
    public void setPresenter(P mPresenter) {
        this.mBasePresenter = mPresenter;
    }

    public BaseModel() {
    }

    public void onDestroy() {
        mBasePresenter = null;
    }
}
