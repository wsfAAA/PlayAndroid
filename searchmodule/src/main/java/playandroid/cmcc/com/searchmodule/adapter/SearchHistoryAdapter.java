package playandroid.cmcc.com.searchmodule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;

/**
 * Created by wsf on 2018/9/28.
 */

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {
    private Context context;
    private ArrayList<String> mSearchHistoryLis;
    private View.OnClickListener mRootListener;
    private View.OnClickListener mCleanListener;

    public SearchHistoryAdapter(Context context, ArrayList<String> mSearchHistory,
                                View.OnClickListener mRootListener,View.OnClickListener mCleanListener) {
        this.context = context;
        this.mSearchHistoryLis = mSearchHistory;
        this.mRootListener=mRootListener;
        this.mCleanListener=mCleanListener;
    }

    public void setData(ArrayList<String> data){
        mSearchHistoryLis=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_history_item_layout, null);
        return new SearchHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryViewHolder holder, int position) {
        holder.tvSearchHistory.setText(mSearchHistoryLis.get(position));
        holder.rlSearchHistoryRoot.setTag(R.id.tv_search_history_id, mSearchHistoryLis.get(position));
        holder.tvSearchHistoryClean.setTag(R.id.tv_search_history_position_id,position);
        holder.rlSearchHistoryRoot.setOnClickListener(mRootListener);
        holder.tvSearchHistoryClean.setOnClickListener(mCleanListener);
    }

    @Override
    public int getItemCount() {
        int count = mSearchHistoryLis == null ? 0 : mSearchHistoryLis.size();
        return count;
    }

    public static class SearchHistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_search_history)
        TextView tvSearchHistory;
        @BindView(R2.id.tv_search_history_clean)
        ImageView tvSearchHistoryClean;
        @BindView(R2.id.rl_search_history_root)
        RelativeLayout rlSearchHistoryRoot;

        public SearchHistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
