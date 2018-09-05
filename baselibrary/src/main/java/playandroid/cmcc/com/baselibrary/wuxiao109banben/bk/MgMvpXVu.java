package playandroid.cmcc.com.baselibrary.wuxiao109banben.bk;


import butterknife.ButterKnife;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp.BaseMvpXVu;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.mvp.BasePresenterX;

/**
 * Created by wsf on 2018/9/5.
 */

public class MgMvpXVu<P extends BasePresenterX> extends BaseMvpXVu<P> {
    public MgMvpXVu() {
    }

    public void bindView() {
        ButterKnife.bind(this, this.view);
    }
}