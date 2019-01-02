package cmcc.com.playandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmcc.com.playandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends Fragment {


    public KnowledgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("wsf","KnowledgeFragment  "+this);
        return inflater.inflate(R.layout.fragment_knowledge, container, false);
    }

}
