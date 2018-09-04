package playandroid.cmcc.com.baselibrary.base.mvp;

/**
 * Created by yutao on 2018/5/21.
 */

public interface IBaseDelegate <V extends IBaseView,P extends IBasePresenter>{


    P bindPresenter();

    P getPresenter();
}
