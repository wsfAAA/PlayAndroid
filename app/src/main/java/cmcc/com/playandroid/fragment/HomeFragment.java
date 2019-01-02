package cmcc.com.playandroid.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cmcc.com.playandroid.R;
import cmcc.com.playandroid.test.TestAdapter;
import cmcc.com.playandroid.test.TestAdapterViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private ArrayList<TestAdapter> data = new ArrayList<>();

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView(View view) {
        for (int i = 0; i < 100; i++) {
            data.add(new TestAdapter(i+"数据"));
        }
        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
        multiTypeAdapter.setItems(data);
        multiTypeAdapter.register(TestAdapter.class,new TestAdapterViewBinder());
        recyclerView = view.findViewById(R.id.m_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(multiTypeAdapter);
    }

}
