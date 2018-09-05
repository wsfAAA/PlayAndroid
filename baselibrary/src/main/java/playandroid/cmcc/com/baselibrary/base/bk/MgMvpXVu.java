package playandroid.cmcc.com.baselibrary.base.bk;

import butterknife.ButterKnife;
import playandroid.cmcc.com.baselibrary.base.mvp.BaseMvpXVu;
import playandroid.cmcc.com.baselibrary.base.mvp.BasePresenterX;

/**
 * Created by yutao on 2018/6/28.
 */

public class MgMvpXVu<P extends BasePresenterX> extends BaseMvpXVu<P> {

    @Override
    public void bindView() {
        ButterKnife.bind(this, view);
    }
}
