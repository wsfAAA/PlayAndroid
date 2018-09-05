package playandroid.cmcc.com.baselibrary.wuxiao109banben.vu;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.view.Window;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wsf on 2018/9/5.
 */

public class BasePresenterActivity<V extends Vu> extends FragmentActivity {
    protected V vu;

    public BasePresenterActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFormat(-3);

        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if(t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType)t).getActualTypeArguments();
                Class<V> vClass = (Class)p[0];
                this.vu = vClass.newInstance();
                this.vu.init(this.getLayoutInflater(), (ViewGroup)null);
                this.setContentView(this.vu.getView());
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    protected void hideNavBar() {
        if(Build.VERSION.SDK_INT >= 23) {
            Window window = this.getWindow();
            window.clearFlags(67108864);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(-2147483648);
            window.setStatusBarColor(0);
            this.getWindow().getDecorView().setSystemUiVisibility(9216);
        }

    }

    public void beforeCreate() {
    }

    protected void onPause() {
        super.onPause();
        if(this.vu != null) {
            this.vu.onPause();
        }

    }

    protected void onResume() {
        super.onResume();
        if(this.vu != null) {
            this.vu.onResume();
        }

    }

    protected final void onDestroy() {
        super.onDestroy();
        if(this.vu != null) {
            this.vu.onDestroy();
            this.vu = null;
        }

    }

    protected Class<V> getVuClass() {
        Class<V> vClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return vClass;
    }
}
