package cmcc.com.playandroid.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.bean.NavigationBean;
import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.base.BaseApplication;

/**
 * Created by wsf on 2019/1/21.  左边 tab adapter
 */
public class LeftNavigationViewBinder extends ItemViewBinder<NavigationBean.DataBean, LeftNavigationViewBinder.ViewHolder> {

    private ArrayList<NavigationBean.DataBean> data = new ArrayList<>();

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_navigation_left_layout, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final NavigationBean.DataBean navigation) {
        if (navigation == null || TextUtils.isEmpty(navigation.getName())) {
            return;
        }

        holder.mTvTabName.setText(navigation.getName());
        if (navigation.isSelect()) {
            holder.mTvTabName.setBackgroundColor(Color.WHITE);
            holder.mTvTabName.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.color_FF9949));
        } else {
            holder.mTvTabName.setBackgroundColor(BaseApplication.getApplication().getResources().getColor(R.color.color_F0F4F7));
            holder.mTvTabName.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.color_5B5B5B));
        }
        if (!data.contains(navigation)) {
            data.add(navigation);
        }
        holder.mTvTabName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setSelect(false);
                }
                navigation.setSelect(true);
                if (listener != null) {
                    listener.onClick(v, holder.getPosition());
                }
            }
        });
    }

    private OnItemClickListener listener;

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.listener = onClickListener;
    }

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTabName;

        ViewHolder(View view) {
            super(view);
            mTvTabName = view.findViewById(R.id.m_tv_tab_name);
        }
    }


}
