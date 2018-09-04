package playandroid.cmcc.com.baselibrary.base.bk;


import butterknife.ButterKnife;
import playandroid.cmcc.com.baselibrary.base.vu.BaseVu;

/**
 * Created by yutao on 2018/6/21.
 */

public class MgBaseVu<T> extends BaseVu<T> {

    @Override
    public void bindView() {
        ButterKnife.bind(this, view);
    }
}
