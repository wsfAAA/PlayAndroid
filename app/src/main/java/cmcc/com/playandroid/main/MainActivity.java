package cmcc.com.playandroid.main;


import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.R;
import playandroid.cmcc.com.baselibrary.basemvp.BaseMvpActivity;
import playandroid.cmcc.com.baselibrary.common.CommonFinal;

public class MainActivity extends BaseMvpActivity<MainPresenter> {

    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_search)
    Button btSearch;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.bt_login, R.id.bt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                ARouter.getInstance().build(CommonFinal.AROUTER_LOGIN).navigation();
                break;
            case R.id.bt_search:
                ARouter.getInstance().build(CommonFinal.AROUTER_SEARCH).navigation();
                break;
        }
    }

    @Override
    public MainPresenter creatPersenter() {
        return new MainPresenter();
    }
}
