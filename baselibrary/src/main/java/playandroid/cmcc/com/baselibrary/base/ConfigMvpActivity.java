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
import playandroid.cmcc.com.baselibrary.basemvp.BaseMvpActivity;
import playandroid.cmcc.com.baselibrary.basemvp.BasePresenter;

/**
 * Created by wsf on 2018/12/26.
 * <p>
 * 可配置的activity 继承可扩展 单独页面的配置
 */
public abstract class ConfigMvpActivity<P extends BasePresenter> extends BaseMvpActivity<P> {

    private ImageView mConfigImgBack;//返回
    private ImageView mConfigImgMore;//更多
    private TextView mConfigTvTitle; //标题

    // TODO: 2018/12/27  注意 继承ConfigMvpActivity的页面为 SmartRefreshLayout赋值 用于刷新加载统一处理  
    // TODO: 2018/12/27  相关属性：https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_property.md
    protected SmartRefreshLayout mConfigSmartRefreshLayout;

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

    protected void setSmartRefreshLayout(SmartRefreshLayout mConfigSmartRefreshLayout) {
        this.mConfigSmartRefreshLayout = mConfigSmartRefreshLayout;
    }

    /**
     * 是否显示更多
     *
     * @param visible
     */
    protected void isShowMore(int visible) {
        if (mConfigImgMore != null) {
            if (visible == View.VISIBLE) {
                mConfigImgMore.setVisibility(View.VISIBLE);
            } else {
                mConfigImgMore.setVisibility(View.GONE);
            }
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
