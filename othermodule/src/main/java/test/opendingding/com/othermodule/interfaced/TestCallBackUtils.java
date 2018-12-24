package test.opendingding.com.othermodule.interfaced;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wsf on 2018/10/23.
 */

public class TestCallBackUtils {

    //----------------------------------静态方法回调1、静态方法回调2----------------------------------
    public static void requestData(final Activity activity, final String urlStr, final TestCallBack listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(urlStr);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    final StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    if (listener != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFinish(sb.toString()); //回调onFinish方法
                            }
                        });
                    }
                } catch (final Exception e) {
                    if (listener != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onError(e);  //回调onError方法
                            }
                        });
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    //----------------------------类对象回调、类对象回调匿名内部类回调----------------------------
    private InterfacedCallBack threeCallBack;

    public void setThreeCallBack(InterfacedCallBack threeCallBack){
        this.threeCallBack=threeCallBack;
        HttpTestThree();
    }

    private void HttpTestThree(){
        //处理网络请求或其他任务

        //消息
        if (threeCallBack!=null){
            threeCallBack.onMessage("回调成功");
        }
    }

    //----------------------------静态匿名内部类回调1----------------------------

    public static InterfacedCallBack interfacedCallBack;

    public static void setCallBack(InterfacedCallBack callBack) {
        interfacedCallBack = callBack;
        TestDemo();
    }

    public static void TestDemo(){
        //任务处理
        //处理完成
        if (interfacedCallBack!=null){
            interfacedCallBack.onMessage("静态匿名内部类回调1");
        }
    }

    //----------------------------静态匿名内部类回调2,使用内部接口----------------------------
    public static InterfacedCallBack2 interfacedCallBack2;

    public static void setCallBack2(InterfacedCallBack2 callBack) {
        interfacedCallBack2 = callBack;
        TestDemo2();
    }

    public static void TestDemo2(){
        //任务处理
        //处理完成
        if (interfacedCallBack2!=null){
            interfacedCallBack2.onMessage("静态匿名内部类回调2");
        }
    }
    //内部接口
    public interface InterfacedCallBack2{
        void onMessage(String msg);
    }
}
