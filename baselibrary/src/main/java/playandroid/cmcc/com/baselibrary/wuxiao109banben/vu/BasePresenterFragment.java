package playandroid.cmcc.com.baselibrary.wuxiao109banben.vu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wsf on 2018/9/5.
 */

public class BasePresenterFragment<V extends Vu> extends Fragment {
    protected V vu;

    public BasePresenterFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if(t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType)t).getActualTypeArguments();
                Class<V> vClass = (Class)p[0];
                this.vu = (V)vClass.newInstance();
                this.beforeInit();
                this.vu.init(inflater, container);
                view = this.vu.getView();
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return view;
    }

    public final void onDestroyView() {
        if(this.vu != null) {
            this.vu.onDestroy();
            this.vu = null;
        }

        super.onDestroyView();
    }

    public final void onPause() {
        super.onPause();
        if(this.vu != null) {
            this.vu.onPause();
        }

    }

    public final void onResume() {
        super.onResume();
        this.beforeResume();
        if(this.vu != null) {
            this.vu.onResume();
        }

    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(this.vu != null) {
            this.vu.setUserVisibleHint(isVisibleToUser);
        }

    }

    public void beforeInit() {
    }

    public void beforeResume() {
    }
}
