package playandroid.cmcc.com.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.basemvp.BaseMvpActivity;
import playandroid.cmcc.com.baselibrary.basemvp.BasePresenter;

/**
 * Created by wsf on 2018/12/26.
 * <p>
 * 标题栏 basemvpactivity
 */
public abstract class ActionBarMvpActivity<P extends BasePresenter> extends BaseMvpActivity<P> {

    private ImageView mBaseImgBack;//返回
    private ImageView mBaseImgMore;//更多
    private TextView mBaseTvTitle; //标题

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseImgBack = findViewById(R.id.m_base_img_back);
        mBaseImgMore = findViewById(R.id.m_base_img_more);
        mBaseTvTitle = findViewById(R.id.m_base_tv_title);

        if (mBaseImgBack != null) {
            mBaseImgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * 是否显示更多
     *
     * @param visible
     */
    protected void isShowMore(int visible) {
        if (mBaseImgMore != null) {
            if (visible == View.VISIBLE) {
                mBaseImgMore.setVisibility(View.VISIBLE);
            } else {
                mBaseImgMore.setVisibility(View.GONE);
            }
        }
    }

    protected void setTitleText(String title) {
        if (mBaseTvTitle != null && !TextUtils.isEmpty(title)) {
            mBaseTvTitle.setText(title);
        }
    }

    protected void setTitleText(int resString) {
        if (mBaseTvTitle != null) {
            mBaseTvTitle.setText(getString(resString));
        }
    }

    @Override
    protected boolean isActionBar() {
        return true;
    }
}
