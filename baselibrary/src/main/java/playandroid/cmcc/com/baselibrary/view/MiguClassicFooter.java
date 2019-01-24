package playandroid.cmcc.com.baselibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.blankj.utilcode.util.SizeUtils;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import playandroid.cmcc.com.baselibrary.R;

/**
 * 咪咕 使用 默认上拉加载样式
 */
public class MiguClassicFooter extends ClassicsFooter {

    public MiguClassicFooter(Context context) {
        super(context);
        initMiguView();
    }

    public MiguClassicFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMiguView();
    }

    public MiguClassicFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMiguView();

    }

    public void initMiguView() {
        mTitleText.setTextColor(mTitleText.getResources().getColor(R.color.color_606060));
        mTitleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        mTitleText.setPadding(mTitleText.getPaddingLeft(), SizeUtils.dp2px(10), mTitleText.getPaddingRight(), SizeUtils.dp2px(10));
        setBackgroundColor(getResources().getColor(R.color.color_F7F7F7));
    }

}
