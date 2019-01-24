package playandroid.cmcc.com.baselibrary.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import playandroid.cmcc.com.baselibrary.R;

/**
 * 咪咕直播 下拉刷新 加载样式
 */
public class MiguClassicHeader extends RelativeLayout implements RefreshHeader {

    private View rootView;
    private ImageView pull_icon;
    private ImageView refreshing_icon;
    private TextView state_tv;

    public MiguClassicHeader(Context context) {
        super(context);
        initView(context);
    }

    public MiguClassicHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MiguClassicHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.view_migu_refresh_header_4_short_video,null);
        pull_icon = (ImageView) rootView.findViewById(R.id.pull_icon);
        refreshing_icon = (ImageView) rootView.findViewById(R.id.refreshing_icon);
        state_tv = (TextView) rootView.findViewById(R.id.state_tv);
        addView(rootView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int headHeight, int extendHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {

        refreshing_icon.setImageResource(R.drawable.loading_list);
        AnimationDrawable drawable = (AnimationDrawable) refreshing_icon.getDrawable();
        drawable.stop();
        refreshing_icon.setVisibility(View.GONE);

        pull_icon.setVisibility(View.VISIBLE);
        if (success){
            state_tv.setText("刷新成功");
        } else {
            state_tv.setText("刷新失败");
        }
        return 300;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
                refreshing_icon.setVisibility(View.GONE);
                pull_icon.setVisibility(View.VISIBLE);
                state_tv.setText("下拉刷新");


            case PullDownToRefresh:
                refreshing_icon.setVisibility(View.GONE);
                pull_icon.setVisibility(View.VISIBLE);
                state_tv.setText("下拉刷新");

                break;
            case Refreshing:
                refreshing_icon.setVisibility(View.VISIBLE);
                pull_icon.setVisibility(View.GONE);
                state_tv.setText("努力刷新中...");

                refreshing_icon.setImageResource(R.drawable.loading_list);
                AnimationDrawable drawable = (AnimationDrawable) refreshing_icon.getDrawable();
                drawable.start();

                break;
            case ReleaseToRefresh:
                refreshing_icon.setVisibility(View.GONE);
                pull_icon.setVisibility(View.VISIBLE);
                state_tv.setText("释放立即刷新");
                break;

        }
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {
    }
}
