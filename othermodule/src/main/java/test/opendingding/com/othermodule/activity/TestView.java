package test.opendingding.com.othermodule.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import test.opendingding.com.othermodule.R;

public class TestView extends RelativeLayout {
    private Context context;

    public TestView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View inflate = View.inflate(context, R.layout.test_view_layout, this);
        this.setVisibility(VISIBLE);
    }
}
