package playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp;


/**
 * Created by wsf on 2018/9/5.
 */

public abstract class BasePresenter<V extends IBaseView, M extends BaseModel> implements IBasePresenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected V baseView;
    protected M baseModel;

    protected abstract M bindModel();

    public BasePresenter() {
    }

    public BasePresenter(V v) {
        this.attachView(v);
    }

    public void attachView(V view) {
        this.baseView = view;
        this.baseModel = this.bindModel();
    }

    public void detachView() {
        this.baseView = null;
    }

    public void onDestroy() {
        if(this.baseModel != null) {
            this.baseModel.onDestroy();
            this.baseModel = null;
        }

        this.detachView();
    }

    public V getV() {
        return this.baseView;
    }
}