package playandroid.cmcc.com.baselibrary.wuxiao109banben.bk;


import butterknife.ButterKnife;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.vu.BaseVu;

/**
 * Created by wsf on 2018/9/5.
 */

public class MgBaseVu<T> extends BaseVu<T> {
    public MgBaseVu() {
    }

    public void bindView() {
        ButterKnife.bind(this, this.view);
    }
}