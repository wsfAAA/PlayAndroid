package playandroid.cmcc.com.baselibrary.mvp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


public abstract class BasePresenter<V extends IBaseView, M extends BaseModel> implements BindViewModel<M> {

    protected final String TAG = getClass().getSimpleName();

    protected M mBaseModel;
    protected V mBaseView;
    protected Context mContext;

    public BasePresenter() {
        mBaseModel = creatModel();
        mBaseModel.setPresenter(this);
    }

    /**
     * 绑定 view
     *
     * @param activity
     */
    public void addActivityInstanc(V activity) {
        this.mBaseView = activity;
        if (activity instanceof FragmentActivity || activity instanceof Fragment) {
            this.mContext = (Context) activity;
        }
    }

    public void onDestroy() {
        if (mBaseModel != null) {
            mBaseModel.onDestroy();
            mBaseModel = null;
        }
        mBaseView = null;
    }

//    /**
//     * 绑定 model, 通过反射
//     *
//     * @return
//     */
//    @Override
//    public M bindModel() {
//        return TUtil.getT(this, 1);
//    }

    public abstract M creatModel();
}
