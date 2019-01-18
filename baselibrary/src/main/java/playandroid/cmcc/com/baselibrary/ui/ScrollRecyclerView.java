package playandroid.cmcc.com.baselibrary.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zuoxian on 2016/3/14.
 */
public class ScrollRecyclerView extends RecyclerView {

    private static RecyclerCallBack mRecyclerCallBack;

    public ScrollRecyclerView(Context context) {
        super(context);
    }

    public ScrollRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (mRecyclerCallBack == null) {
            return;
        }
        if (!canScrollVertically(-1)) {  // 是否在顶部
            mRecyclerCallBack.onScrolledToTop();
        } else if (!canScrollVertically(1)) { // 是否在底部
            mRecyclerCallBack.onScrolledToBottom();
        } else if (dy < 0) {   // 向上滑动
            mRecyclerCallBack.onScrolledUp();
        } else if (dy > 0) {   // 向下滑动
            mRecyclerCallBack.onScrolledDown();
        }
    }

    public static void setRecyclerCallBack(RecyclerCallBack callBack) {
        mRecyclerCallBack = callBack;
    }

    public interface RecyclerCallBack {
        void onScrolledUp();

        void onScrolledDown();

        void onScrolledToTop();

        void onScrolledToBottom();
    }
}
