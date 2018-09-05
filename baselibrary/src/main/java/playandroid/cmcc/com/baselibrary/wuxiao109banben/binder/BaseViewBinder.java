package playandroid.cmcc.com.baselibrary.wuxiao109banben.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.CallBack;
import playandroid.cmcc.com.baselibrary.wuxiao109banben.vu.Vu;
import android.support.annotation.NonNull;

/**
 * Created by wsf on 2018/9/5.
 */

public class BaseViewBinder<V extends Vu, T> extends ItemViewBinder<T, BaseViewBinder.ViewHolder> {
    protected CallBack<Object> itemCallBack;

    public BaseViewBinder() {
    }

    public BaseViewBinder(CallBack<Object> callBack) {
        this.itemCallBack = callBack;
    }

    @NonNull
    protected BaseViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        Vu itemVu = null;

        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if(t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType)t).getActualTypeArguments();
                Class<Vu> vClass = (Class)p[0];
                itemVu = (Vu)vClass.newInstance();
                itemVu.setCallBack(this.itemCallBack);
                itemVu.init(inflater, parent);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return new BaseViewBinder.ViewHolder(itemVu);
    }

    protected void onBindViewHolder(@NonNull BaseViewBinder.ViewHolder holder, @NonNull T componentsBean) {
        if(holder != null) {
            holder.bindData(componentsBean);
        }

    }

    protected void onViewAttachedToWindow(@NonNull BaseViewBinder.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.onViewAttachedToWindow();
    }

    protected void onViewDetachedFromWindow(@NonNull BaseViewBinder.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetachedFromWindow();
    }

    static class ViewHolder<T> extends android.support.v7.widget.RecyclerView.ViewHolder {
        Vu itemVu;

        ViewHolder(Vu itemVu) {
            super(itemVu.getView());
            this.itemVu = itemVu;
        }

        public void bindData(T componentsBean) {
            this.itemVu.setAdapterPos(this.getAdapterPosition());
            this.itemVu.bindData(componentsBean);
        }

        public void onViewDetachedFromWindow() {
            if(this.itemVu != null) {
                this.itemVu.onPause();
            }

        }

        public void onViewAttachedToWindow() {
            if(this.itemVu != null) {
                this.itemVu.onResume();
            }

        }
    }
}
