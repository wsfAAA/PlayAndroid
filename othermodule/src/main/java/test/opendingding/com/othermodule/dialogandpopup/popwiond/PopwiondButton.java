package test.opendingding.com.othermodule.dialogandpopup.popwiond;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import test.opendingding.com.othermodule.R;


/**
 * Created by wsf on 2018/10/28.
 */

public class PopwiondButton extends PopupWindow {

    private Context context;

    public PopwiondButton(Context mainActivity) {
        context=mainActivity;
        initView();
    }

    private void initView() {
        setTouchable(true);
//        setAnimationStyle();//动画
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        View view=View.inflate(context, R.layout.popwion_button_layout,null);
        setContentView(view);

        //相关view操作
        view.findViewById(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
