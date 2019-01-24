package cmcc.com.playandroid.mvp.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.adapter.LeftNavigationViewBinder;
import cmcc.com.playandroid.adapter.RightNavigationViewBinder;
import cmcc.com.playandroid.bean.NavigationBean;
import cmcc.com.playandroid.mvp.presenter.KnowledgePresenter;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpFragment;
import playandroid.cmcc.com.baselibrary.ui.BaseLoadingView;
import playandroid.cmcc.com.baselibrary.ui.StopAppBarRecyclerView;

/**
 * 导航
 */
public class KnowledgeFragment extends BaseMvpFragment<KnowledgePresenter> {

    @BindView(R.id.m_left_recycler_view)
    StopAppBarRecyclerView mLeftRecyclerView;
    @BindView(R.id.m_right_recycler_view)
    RecyclerView mRightRecyclerView;
    @BindView(R.id.m_loading_view)
    public BaseLoadingView mLoadingView;

    private MultiTypeAdapter mLeftMultiTypeAdapter;
    private MultiTypeAdapter mRightMultiTypeAdapter;
    private boolean isLeftClick = false;

    public KnowledgeFragment() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void onFragmentVisible() {
        /**
         * 左边recyclerview 处理
         */
        mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLeftMultiTypeAdapter = new MultiTypeAdapter();
        LeftNavigationViewBinder leftNavigationViewBinder = new LeftNavigationViewBinder();
        mLeftMultiTypeAdapter.register(NavigationBean.DataBean.class, leftNavigationViewBinder);
        mLeftMultiTypeAdapter.setItems(mBasePresenter.getTabData());
        mLeftRecyclerView.setAdapter(mLeftMultiTypeAdapter);

        leftNavigationViewBinder.setOnClickListener(new LeftNavigationViewBinder.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                mLeftMultiTypeAdapter.notifyDataSetChanged();
                isLeftClick = true;
                //改变右边item内容 位置
                mRightRecyclerView.scrollToPosition(position);
            }
        });

        /**
         * 右边 recyclerview 处理
         */
        final LinearLayoutManager mRightLinearLayoutManager = new LinearLayoutManager(mContext);
        mRightRecyclerView.setLayoutManager(mRightLinearLayoutManager);
        mRightMultiTypeAdapter = new MultiTypeAdapter();
        mRightMultiTypeAdapter.register(NavigationBean.DataBean.class, new RightNavigationViewBinder(mContext));
        mRightMultiTypeAdapter.setItems(mBasePresenter.getTabData());
        mRightRecyclerView.setAdapter(mRightMultiTypeAdapter);
        mRightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLeftClick) {
                    isLeftClick = false;
                } else {
                    int firstVisibleItemPosition = mRightLinearLayoutManager.findFirstVisibleItemPosition();
                    ArrayList<NavigationBean.DataBean> tabData = mBasePresenter.getTabData();
                    for (int i = 0; i < tabData.size(); i++) {
                        tabData.get(i).setSelect(false);
                    }
                    tabData.get(firstVisibleItemPosition).setSelect(true);

                    //改变左边 tab 选中位置
                    mLeftRecyclerView.scrollToPosition(firstVisibleItemPosition);
                    mLeftMultiTypeAdapter.notifyDataSetChanged();
                }
            }
        });

        mLoadingView.showLoading();
        mBasePresenter.requestData();

        mLoadingView.setAnewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBasePresenter.requestData();
            }
        });
    }

    public MultiTypeAdapter getLeftMultiTypeAdapter() {
        return mLeftMultiTypeAdapter;
    }

    public MultiTypeAdapter getRightMultiTypeAdapter() {
        return mRightMultiTypeAdapter;
    }


    @Override
    public KnowledgePresenter creatPersenter() {
        return new KnowledgePresenter();
    }
}
