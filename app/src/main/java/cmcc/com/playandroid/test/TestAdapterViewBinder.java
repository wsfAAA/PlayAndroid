package cmcc.com.playandroid.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cmcc.com.playandroid.R;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by wsf on 2018/12/29.
 */
public class TestAdapterViewBinder extends ItemViewBinder<TestAdapter, TestAdapterViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_test_adapter, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TestAdapter testAdapter) {
         holder.mTextView.setText(testAdapter.getTest());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_test);
        }
    }
}
