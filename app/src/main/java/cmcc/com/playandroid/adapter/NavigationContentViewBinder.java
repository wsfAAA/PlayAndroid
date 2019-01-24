package cmcc.com.playandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cmcc.com.playandroid.R;
import cmcc.com.playandroid.bean.NavigationBean;
import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.baselibrary.util.WebViewRoute;
import cmcc.com.playandroid.webview.WebviewActivity;

/**
 * 导航右边 item 内容
 */
public class NavigationContentViewBinder extends ItemViewBinder<NavigationBean.DataBean.ArticlesBean, NavigationContentViewBinder.ViewHolder> {

    private final Context mContext;

    public NavigationContentViewBinder(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_navigation_content_layout, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NavigationBean.DataBean.ArticlesBean navigation) {
        holder.navigation = navigation;
        holder.context = mContext;
        if (!TextUtils.isEmpty(navigation.getTitle())) {
            holder.mTextView.setText(navigation.getTitle());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private Context context;
        private NavigationBean.DataBean.ArticlesBean navigation;

        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.m_tv_content);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebviewActivity.class);
                    intent.putExtra(WebViewRoute.WEBVIEW_URL, navigation.getLink());
                    context.startActivity(intent);
                }
            });
        }
    }


}
