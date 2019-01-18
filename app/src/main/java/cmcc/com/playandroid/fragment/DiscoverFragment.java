package cmcc.com.playandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmcc.com.playandroid.R;

/**
 * 发现
 */
public class DiscoverFragment extends Fragment {


    public DiscoverFragment() {
    }

    public void scrollToPosition(int position) {
//        if (mRecyclerview != null) {
//            mRecyclerview.scrollToPosition(position);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

}
