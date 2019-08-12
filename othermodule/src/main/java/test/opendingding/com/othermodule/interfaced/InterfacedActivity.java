package test.opendingding.com.othermodule.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.interfaced.InterfacedCallBack;
import test.opendingding.com.othermodule.interfaced.TestCallBack;
import test.opendingding.com.othermodule.interfaced.TestCallBackUtils;

public class InterfacedActivity extends AppCompatActivity implements TestCallBack, InterfacedCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaced);

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestOne();
            }
        });
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestTow();
            }
        });
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestThree();
            }
        });

        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestFour();
            }
        });
        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestFifty();
            }
        });
        findViewById(R.id.btn_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestSix();
            }
        });
        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestSave();
            }
        });
    }

    //--------------------------静态方法回调1----------------------------------

    /**
     * 静态方法回调1: 调用静态方法 new 接口形式、适用于 调用某方法处理完任务后根据传递接口进行回调，比如：网络请求
     */
    private void TestOne() {
        TestCallBackUtils.requestData(this, "网络请求地址", new TestCallBack() {
            @Override
            public void onFinish(String respose) {
                Toast.makeText(InterfacedActivity.this, "静态方法回调1: onFinish ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(InterfacedActivity.this, "静态方法回调1:  onError", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //-----------------------------------静态方法回调2---------------------------------

    /**
     * 静态方法回调2：通过调用静态方法，MainActivity类实现TestOneCallBack（定义接口）,进行注册实现。
     * 适用场景和第一种方式相似
     */
    private void TestTow() {
        TestCallBackUtils.requestData(this, "网络地址", this);
    }

    @Override
    public void onFinish(String respose) {
        Toast.makeText(InterfacedActivity.this, "静态方法回调2: onFinish", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(InterfacedActivity.this, "静态方法回调2: onError ", Toast.LENGTH_SHORT).show();
    }

    //-------------------------------类对象回调----------------------------------------------

    /**
     * 类对象回调：根据类对象，进行注册监听，MainActivity类实现TestThreeCallBack。
     */
    private void TestThree() {
        TestCallBackUtils testCallBackUtils = new TestCallBackUtils();
        testCallBackUtils.setThreeCallBack(this);
    }

    @Override
    public void onMessage(String respose) {
        Toast.makeText(InterfacedActivity.this, "类对象回调: " + respose, Toast.LENGTH_SHORT).show();
    }

    //----------------------------------类对象回调匿名内部类回调-----------------------------------

    /**
     * 类对象回调匿名内部类回调: 第三种方式拓展,不需要MainActivity类实现TestThreeCallBack，以匿名内部类方式实现
     */
    private void TestFour() {
        TestCallBackUtils testCallBackUtils = new TestCallBackUtils();
        testCallBackUtils.setThreeCallBack(new InterfacedCallBack() {
            @Override
            public void onMessage(String respose) {
                Toast.makeText(InterfacedActivity.this, "类对象回调匿名内部类: " + respose, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //----------------------------------静态匿名内部类回调1----------------------------------

    /**
     * 静态匿名内部类回调1
     */
    private void TestFifty() {
        TestCallBackUtils.setCallBack(new InterfacedCallBack() {
            @Override
            public void onMessage(String respose) {
                Toast.makeText(InterfacedActivity.this, respose, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //----------------------------------静态匿名内部类回调2,使用内部接口----------------------------------
    /**
     *静态匿名内部类回调2,使用内部接口
     */
    private void TestSix() {
        TestCallBackUtils.setCallBack2(new TestCallBackUtils.InterfacedCallBack2() {
            @Override
            public void onMessage(String msg) {
                Toast.makeText(InterfacedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //----------------------------------静态回调使用内部接口----------------------------------
    /**
     * 静态回调使用内部接口
     */
    private void TestSave() {
        //模拟任务TestDemo2方法自动被回调
        TestCallBackUtils.TestDemo2();
        TestCallBackUtils.interfacedCallBack2=new TestCallBackUtils.InterfacedCallBack2() {
            @Override
            public void onMessage(String msg) {
                Toast.makeText(InterfacedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
