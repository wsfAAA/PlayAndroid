package playandroid.cmcc.com.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpActivity;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2018/12/26.
 * <p>
 * 可配置的activity 继承可扩展 用于单独页面的配置 例如 标题等
 */
public abstract class ConfigMvpActivity<P extends BasePresenter> extends BaseMvpActivity<P> {

    // TODO: 2019/1/2 标题栏
    private ImageView mConfigImgBack;//返回
    private ImageView mConfigImgMore;//更多
    private TextView mConfigTvTitle; //标题

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isActionBar(true);
        mConfigImgBack = findViewById(R.id.m_base_img_back);
        mConfigImgMore = findViewById(R.id.m_base_img_more);
        mConfigTvTitle = findViewById(R.id.m_base_tv_title);

        Log.i("cesi---->", "ConfigMvpActivity onCreate");
        if (mConfigImgBack != null) {
            mConfigImgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * 是否显示更多  默认不显示
     *
     * @param visible
     */
    protected void isShowMore(int visible) {
        if (mConfigImgMore != null) {
            mConfigImgMore.setVisibility(visible);
        }
    }

    /**
     * 是否显示 返回键 默认显示
     *
     * @param visible
     */
    protected void isShowBack(int visible) {
        if (mConfigImgBack != null) {
            mConfigImgBack.setVisibility(View.VISIBLE);
        }
    }

    protected void setTitleText(String title) {
        if (mConfigTvTitle != null && !TextUtils.isEmpty(title)) {
            mConfigTvTitle.setText(title);
        }
    }

    protected void setTitleText(int resString) {
        if (mConfigTvTitle != null) {
            mConfigTvTitle.setText(getString(resString));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("cesi---->", "ConfigMvpActivity onDestroy");
    }
}
