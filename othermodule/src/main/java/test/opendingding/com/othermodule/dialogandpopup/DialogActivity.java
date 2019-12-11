package test.opendingding.com.othermodule.dialogandpopup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.dialogandpopup.dialog.DialogFramengTest;
import test.opendingding.com.othermodule.dialogandpopup.dialog.DialogBuilderTest;
import test.opendingding.com.othermodule.dialogandpopup.dialog.DialogTow;
import test.opendingding.com.othermodule.dialogandpopup.popwiond.PopwiondButton;

public class DialogActivity extends AppCompatActivity {

    private LinearLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        llRoot = findViewById(R.id.ll_root);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popOne();
            }
        });

        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAlpha(1f);
                showDialog();
            }
        });

        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog2();
            }
        });

        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogFragment();
            }
        });
    }

    private void showDialogFragment() {
        final DialogFramengTest dialogFramengTest=new DialogFramengTest();
        dialogFramengTest.setCallBack(new DialogFramengTest.DialogFragmentCallBack() {
            @Override
            public void cancle() {
                dialogFramengTest.dismiss();
            }

            @Override
            public void yesBtn() {
                dialogFramengTest.dismiss();
            }
        });
        dialogFramengTest.show(getFragmentManager(),"DialogFramengTest");
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    private void showDialog2() {
        DialogTow dialogTow = new DialogTow(this, R.style.custom_dialog);
        dialogTow.setClickListener(new DialogTow.ClickListener() {
            @Override
            public void clickYes() {
                Toast.makeText(DialogActivity.this, "clickYes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clickCancel() {
                Toast.makeText(DialogActivity.this, "clickCancel", Toast.LENGTH_SHORT).show();
            }
        });
        dialogTow.show();
    }

    private void showDialog() {
        DialogBuilderTest.Builder builder = new DialogBuilderTest.Builder(this);
        builder.setClickListener(new DialogBuilderTest.Builder.ClickListener() {
            @Override
            public void clickYes() {
                Toast.makeText(DialogActivity.this, "clickYes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clickCancel() {
                Toast.makeText(DialogActivity.this, "clickCancel", Toast.LENGTH_SHORT).show();
            }
        });
        DialogBuilderTest dialogTest = builder.create();
        dialogTest.show();
    }

    /**
     * popwindow底部弹出
     */
    private void popOne() {
        PopwiondButton popwiondButton = new PopwiondButton(this);
        if (!popwiondButton.isShowing()) {
            /**
             *  showAtLocation:在一个弹出窗口中的指定位置显示内容视图。
             *  parent:View父视图
             *  gravity:弹出窗口位置的重力值
             *  x: 弹出窗口的x坐标偏移
             *  y:弹出窗口的y坐标偏移
             */
            popwiondButton.showAtLocation(llRoot, Gravity.BOTTOM, 0, 0);

            /**
             *在所附着的视图的左下角的一个弹出窗口中显示内容视图。
             */
//            popwiondButton.showAsDropDown(llRoot);
        }
    }
}
