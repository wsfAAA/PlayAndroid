package playandroid.cmcc.com.searchmodule.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.bean.SearchHotKey;

/**
 * Created by wsf on 2018/9/27.
 */

public class SearchItemBinder extends ItemViewBinder<SearchHotKey, SearchItemBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.search_hotkey_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull SearchHotKey item) {
        int position = holder.getPosition();
        SearchHotKey.DataBean dataBean = item.getData().get(position);
        if (dataBean!=null){
            holder.tvSetchkhotkey.setText(dataBean.getName());
            holder.tvSetchkhotkey.setTag(R.id.tv_search_hotkey,dataBean);//通过tag传值
            holder.tvSetchkhotkey.setOnClickListener(mListener);
        }
    }

    private View.OnClickListener mListener;

    public void setOnItemClickListener(View.OnClickListener mListener){
            this.mListener=mListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_setchkhotkey)
        TextView tvSetchkhotkey;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
