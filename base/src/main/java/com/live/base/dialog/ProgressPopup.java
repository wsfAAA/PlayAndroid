package com.live.base.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.live.base.R;
import com.live.base.view.CirclePercentView;

import razerdp.basepopup.BasePopupWindow;

public class ProgressPopup extends BasePopupWindow {

    private final Context context;
    private CirclePercentView circlePercentView;
    private TextView textView;

    public ProgressPopup(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateContentView() {
        View view = createPopupById(R.layout.progress_popup_loading);
        circlePercentView = view.findViewById(R.id.progress_circular);
        textView = view.findViewById(R.id.tv_content);
        return view;
    }

    public void setProgress(int progress, String content) {
        if (circlePercentView != null) {
            circlePercentView.setPercentage(progress);
            textView.setText(content + "");
        }
    }
}
