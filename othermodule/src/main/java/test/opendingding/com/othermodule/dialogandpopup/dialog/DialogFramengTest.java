package test.opendingding.com.othermodule.dialogandpopup.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import test.opendingding.com.othermodule.R;


/**
 * Created by wsf on 2018/10/31.
 */
public class DialogFramengTest extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setCancelable(true);


        View view = inflater.inflate(R.layout.dialog_confirm, container);
        Button cancel_btn = view.findViewById(R.id.cancel_btn);
        Button confirm_btn = view.findViewById(R.id.confirm_btn);
        cancel_btn.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);
//        //获取当前Activity所在的窗体
//        Window dialogWindow = dialog.getWindow();
//        //设置软键盘弹出模式
//        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        //获得窗体的属性
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.gravity = Gravity.BOTTOM;
//        //设置Dialog宽度匹配屏幕宽度
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        //设置Dialog高度自适应
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        //将属性设置给窗体
//        dialogWindow.setAttributes(lp);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_btn:
                if (dialogFragmentCallBack!=null){
                    dialogFragmentCallBack.cancle();
                }
                break;
            case R.id.confirm_btn:
                if (dialogFragmentCallBack!=null){
                    dialogFragmentCallBack.yesBtn();
                }
                break;
        }
    }

    private DialogFragmentCallBack dialogFragmentCallBack;

    public void setCallBack(DialogFragmentCallBack dialogFragmentCallBack) {
        this.dialogFragmentCallBack = dialogFragmentCallBack;
    }

    public interface DialogFragmentCallBack {
        void cancle();

        void yesBtn();
    }
}
