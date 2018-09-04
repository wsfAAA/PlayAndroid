package playandroid.cmcc.com.baselibrary.base.vu;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author yutao
 * @version V1.0
 * @Description:
 * @date 2015年6月25日 下午3:11:34
 */
public  class BasePresenterActivity<V extends Vu> extends FragmentActivity {

    protected V vu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                Class<V> vClass = (Class<V>) p[0];
                vu = vClass.newInstance();
                vu.init(getLayoutInflater(), null);
                setContentView(vu.getView());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void hideNavBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public void beforeCreate() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(vu!=null){
            vu.onPause();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(vu!=null){
            vu.onResume();
        }


    }

    @Override
    protected final void onDestroy() {
        super.onDestroy();
        if(vu!=null){
            vu.onDestroy();
            vu = null;
        }
    }


    protected  Class<V> getVuClass(){
        Class<V> vClass = (Class<V>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return vClass;
    }


}