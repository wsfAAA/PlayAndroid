package playandroid.cmcc.com.baselibrary.base.vu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * 
* @Description: 
* @author yutao  
* @date 2015年6月25日 下午3:09:23 
* @version V1.0
 */
public  class BasePresenterFragment<V extends Vu> extends Fragment {

    protected V vu;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                Class<V> vClass = (Class<V>) p[0];
                vu = vClass.newInstance();
                beforeInit();
                vu.init(inflater, container);
                view = vu.getView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public final void onDestroyView() {
        if(vu!=null){
            vu.onDestroy();
            vu = null;
        }
        super.onDestroyView();

    }


    @Override
    public final void onPause() {
        super.onPause();
        if(vu!=null){
            vu.onPause();
        }
    }

    @Override
    public final void onResume() {
        super.onResume();
        beforeResume();
        if(vu!=null){
            vu.onResume();
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(vu!=null){
            vu.setUserVisibleHint(isVisibleToUser);
        }
    }

    public void beforeInit(){

    }

    public void beforeResume(){

    }

}