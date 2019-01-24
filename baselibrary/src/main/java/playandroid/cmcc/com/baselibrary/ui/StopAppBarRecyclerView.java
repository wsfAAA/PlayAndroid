package playandroid.cmcc.com.baselibrary.ui;

/**
 * Created by wsf on 2019/1/24.
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * AppBarLayout 禁止滑动的 RecyclerView
 * 参考地址：https://blog.csdn.net/EthanCo/article/details/78752352
 */
public class StopAppBarRecyclerView extends RecyclerView {

    boolean isStopAppBarScroll = true;

    public StopAppBarRecyclerView(Context context) {
        super(context);
    }

    public StopAppBarRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StopAppBarRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isStopAppBarScroll() {
        return isStopAppBarScroll;
    }

    /**
     * 是否停止 recyclerview 和appbar之间的关联滚动 ,默认停止
     *
     * @return
     */
    public void setAppBarScroll(boolean nestedEnable) {
        isStopAppBarScroll = nestedEnable;
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        if (isStopAppBarScroll) {
            return false;
        } else {
            return super.startNestedScroll(axes, type);
        }
    }

    @Override
    public void stopNestedScroll(int type) {
        if (isStopAppBarScroll) {

        } else {
            super.stopNestedScroll(type);
        }
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        if (isStopAppBarScroll) {
            return false;
        } else {
            return super.hasNestedScrollingParent(type);
        }
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        if (isStopAppBarScroll) {
            return false;
        } else {
            return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
        }
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        if (isStopAppBarScroll) {
            return false;
        } else {
            return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
        }
    }
}
