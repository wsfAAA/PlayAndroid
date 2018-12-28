package test.opendingding.com.othermodule.popupclient;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import playandroid.cmcc.com.baselibrary.R2;
import test.opendingding.com.othermodule.R;

/**
 * Created by wsf on 2018/12/19.
 */

public class WebViewBarMore {

    private final Unbinder unbinder;
    @BindView(R2.id.m_tv_web_share)
    TextView mTvWebShare;
    @BindView(R2.id.m_tv_web_collect)
    TextView mTvWebCollect;
    @BindView(R2.id.m_tv_web_bs_open)
    TextView mTvWebBsOpen;
    private final PopupClient build;

    public WebViewBarMore() {
        build = PopupClient.builder()
                .view(R.layout.webview_bar_maor_layout)
//                .animStyle(R.style.DialogBaseAnimation)
                .width(ConvertUtils.dp2px(200))
                .height(ConvertUtils.dp2px(120))
                .clickType(PopupClickType.SCREEN_YES_AND_RETURN_KEY_YSE)
                .build();
        unbinder = ButterKnife.bind(this, build.getView());
    }

    public void showPopup(View mView, int x, int y) {
        if (build != null && !build.isShowing()) {
            build.showPopupWindowPosition(mView, x, y);
        }
    }

    public void showPopupButton(View mView) {
        if (build != null && !build.isShowing()) {
            build.showPopupWindowBottom(mView);
        }
    }


    public void destory() {
        if (build != null) {
            if (build.isShowing()) {
                build.dismiss();
            }
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @OnClick({R2.id.m_tv_web_share, R2.id.m_tv_web_collect, R2.id.m_tv_web_bs_open})
    public void onViewClicked(View view) {
        if (build != null && build.isShowing()) {
            build.dismiss();
        }
        int i = view.getId();
        if (i == R.id.m_tv_web_share) {
            ToastUtils.showShort("分享");
        } else if (i == R.id.m_tv_web_collect) {
            ToastUtils.showShort("收藏");
        } else if (i == R.id.m_tv_web_bs_open) {
            ToastUtils.showShort("浏览器打开");
        }
    }
}
