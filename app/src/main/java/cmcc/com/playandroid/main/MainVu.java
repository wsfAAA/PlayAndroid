package cmcc.com.playandroid.main;

import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.R;
import playandroid.cmcc.com.baselibrary.base.bk.MgMvpXVu;

/**
 * Created by wsf on 2018/9/5.
 */

public class MainVu extends MgMvpXVu<MainPresenter> {

    @BindView(R.id.bt_login)
    Button btLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
        super.bindView();
    }

    /**
     * 调整登录页
     */
    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        ARouter.getInstance().build("/account/login").navigation();
    }
}
