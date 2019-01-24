package playandroid.cmcc.com.baselibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import playandroid.cmcc.com.baselibrary.R;

/**
 * 咪咕直播 小视频 上拉 底部加载样式
 */
public class MiguShortVideoFooter extends RelativeLayout implements RefreshFooter {


    private TextView mTvContent;

    public MiguShortVideoFooter(Context context) {
        super(context);
        initView(context);
    }

    public MiguShortVideoFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MiguShortVideoFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context ctx) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.view_custom_load_more, null);
        mTvContent = view.findViewById(R.id.m_tv_content);
        addView(view);
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState1) {

    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... ints) {

    }

    @Override
    public void onInitialized(RefreshKernel refreshKernel, int i, int i1) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float v, int i, int i1) {

    }

    @Override
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i1) {

    }

    @Override
    public int onFinish(RefreshLayout refreshLayout, boolean success) {
        if (success) {
            mTvContent.setText("刷新完成");
        } else {
            mTvContent.setText("刷新失败");
        }
        return 0;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }


    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }
}
