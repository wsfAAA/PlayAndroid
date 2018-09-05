package playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp;


/**
 * Created by wsf on 2018/9/5.
 */

public interface IBaseDelegate<V extends IBaseView, P extends IBasePresenter> {
    P bindPresenter();

    P getPresenter();
}
