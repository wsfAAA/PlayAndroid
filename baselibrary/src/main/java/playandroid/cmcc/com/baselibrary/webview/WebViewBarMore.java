package playandroid.cmcc.com.baselibrary.webview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.R2;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by wsf on 2018/12/19.
 */

public class WebViewBarMore extends BasePopupWindow {

    @BindView(R2.id.m_tv_web_share)
    TextView mTvWebShare;
    @BindView(R2.id.m_tv_web_collect)
    TextView mTvWebCollect;
    @BindView(R2.id.m_tv_web_bs_open)
    TextView mTvWebBsOpen;
    private Context mContext;
    private Unbinder unbinder;

    public WebViewBarMore(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View onCreateContentView() {
        View view = createPopupById(R.layout.webview_bar_maor_layout);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void destory() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void showPopup() {
        setBackgroundColor(Color.TRANSPARENT);  //取消默认背景灰色
//        setAllowDismissWhenTouchOutside(false); //点击popup背景区域不消失
//        setBackPressEnable(false);              //点击返回键不消失
//        setAllowInterceptTouchEvent(false);     //设置是否允许BasePopup拦截事件，默认拦截,false不拦截。配合setAllowDismissWhenTouchOutside使用popup不消失，popup背景内容可点击
        showPopupWindow();
    }

    @OnClick({R2.id.m_tv_web_share, R2.id.m_tv_web_collect, R2.id.m_tv_web_bs_open})
    public void onViewClicked(View view) {
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
