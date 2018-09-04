package playandroid.cmcc.com.baselibrary.base.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.baselibrary.base.vu.Vu;

public class BaseViewBinder<V extends Vu,T> extends ItemViewBinder<T, BaseViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        Vu itemVu=null;
        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                Class<Vu> vClass = (Class<Vu>) p[0];
                itemVu = vClass.newInstance();
                itemVu.init(inflater, parent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ViewHolder(itemVu);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull T componentsBean) {
        if(holder!=null){
            holder.bindData(componentsBean);
        }
    }


    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.onViewAttachedToWindow();
    }

    @Override
    protected void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetachedFromWindow();
    }

    static class ViewHolder<T> extends RecyclerView.ViewHolder {
        Vu itemVu;

        ViewHolder(Vu itemVu) {
            super(itemVu.getView());
            this.itemVu=itemVu;
        }

        public void bindData(T componentsBean){
            itemVu.bindData(componentsBean);
        }

        public void onViewDetachedFromWindow(){
            if(itemVu!=null){
                itemVu.onPause();
            }
        }

        public void onViewAttachedToWindow(){
            if(itemVu!=null){
                itemVu.onResume();
            }
        }
    }
}
