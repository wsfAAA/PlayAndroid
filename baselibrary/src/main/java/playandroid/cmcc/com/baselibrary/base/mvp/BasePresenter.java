package playandroid.cmcc.com.baselibrary.base.mvp;

/**
 * Created by yutao on 2018/5/21.
 */

public abstract class  BasePresenter<V extends IBaseView,M extends BaseModel> implements IBasePresenter{

    protected final String TAG=getClass().getSimpleName();

    protected V baseView;

    protected M baseModel;

    protected abstract M bindModel();

    public BasePresenter(){}

    public BasePresenter(V v){
        attachView(v);
    }

    public void attachView(V view) {
        this.baseView=view;
        baseModel=bindModel();
    }

    public void detachView() {
        baseView=null;
    }

    public void onDestroy(){
        if(baseModel!=null){
            baseModel.onDestroy();
            baseModel=null;
        }
        detachView();
    }

    public V getV() {
        return baseView;
    }
}
