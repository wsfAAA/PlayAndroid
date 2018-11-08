package playandroid.cmcc.com.baselibrary.base.jadapter.basemvp;

import android.support.v4.app.FragmentActivity;
import android.util.Log;


public class BasePresenter<V extends FragmentActivity, M extends BaseModel> implements BindViewModel<M> {

    protected final String TAG = getClass().getSimpleName();

    protected M mBaseModel;
    protected V mBaseView;

    public BasePresenter() {
        mBaseModel = bindModel();
        mBaseModel.setmPresenter(this);
        Log.i("wsf", TAG + " BasePresenter");
    }

    /**
     * 绑定 view
     *
     * @param activity
     */
    public void addActivityInstanc(V activity) {
        this.mBaseView = activity;
        Log.i("wsf", TAG + " addActivityInstanc");
    }

    public void onDestroy() {
        Log.i("wsf", TAG + " onDestroy");
        if (mBaseModel != null) {
            mBaseModel.onDestroy();
            mBaseModel = null;
        }
            mBaseView = null;
    }

    /**
     * 绑定 model
     *
     * @return
     */
    @Override
    public M bindModel() {
        return TUtil.getT(this, 1);
    }
}
