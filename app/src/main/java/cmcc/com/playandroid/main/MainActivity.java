package cmcc.com.playandroid.main;


import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.R;
import playandroid.cmcc.com.baselibrary.base.jadapter.basemvp.BaseActivity;
import playandroid.cmcc.com.baselibrary.base.jadapter.basemvp.BaseMvpActivity;

public class MainActivity extends BaseMvpActivity<MainPresenter> {

    public static final String AROUTER_LOGIN = "/account/login";
    public static final String AROUTER_SEARCH = "/home/search";

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
                ARouter.getInstance().build(AROUTER_LOGIN).navigation();
                break;
            case R.id.bt_search:
                ARouter.getInstance().build(AROUTER_SEARCH).navigation();
                break;
        }
    }

    @Override
    public MainPresenter creatPersenter() {
        return new MainPresenter();
    }
}
