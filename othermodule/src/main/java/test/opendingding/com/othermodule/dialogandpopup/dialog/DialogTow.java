package test.opendingding.com.othermodule.dialogandpopup.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;

import test.opendingding.com.othermodule.R;


/**
 * Created by wsf on 2018/10/30.
 */

public class DialogTow extends AppCompatDialog {//建议extends AppCompatDialog 而非Dialog,兼容样式

    private Context context;
    private ClickListener mClickListener;

    public void setClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface ClickListener {
        void clickYes();

        void clickCancel();
    }

    public DialogTow(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        rootView.findViewById(R.id.bnt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null) {
                    mClickListener.clickYes();
                }
            }
        });
        rootView.findViewById(R.id.bnt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null) {
                    mClickListener.clickCancel();
                }
            }
        });
        setContentView(rootView);//Dialog使22用setContentView
        setCanceledOnTouchOutside(true);//设置在窗口边界外触摸时是否取消此对话框。如果设置为true，则对话框设置为可取消
        setCancelable(true);//设置此对话框是否可以使用BACK键取消,true取消，false不取消
    }
}
