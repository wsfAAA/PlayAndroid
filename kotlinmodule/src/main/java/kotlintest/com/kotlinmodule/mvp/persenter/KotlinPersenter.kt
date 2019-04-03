package kotlintest.com.kotlinmodule.mvp.persenter

import cmcc.com.playandroid.bean.CommonListBean
import com.blankj.utilcode.util.ToastUtils
import kotlintest.com.kotlinmodule.BannerBean
import kotlintest.com.kotlinmodule.adapter.KotlinIBannerBinder
import kotlintest.com.kotlinmodule.adapter.KotlinItemBinder
import kotlintest.com.kotlinmodule.mvp.module.KotlinModle
import kotlintest.com.kotlinmodule.mvp.view.KotlinMainActivity
import kotlinx.android.synthetic.main.activity_kotlin_main.*
import me.drakeet.multitype.MultiTypeAdapter
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter

/**
 * Created by wsf on 2019/3/29.
 */
class KotlinPersenter : BasePresenter<KotlinMainActivity, KotlinModle>() {
    var isRefresh = false
    var mCountPage = 0
    private lateinit var adapter: MultiTypeAdapter
    private lateinit var mItems: MutableList<Any>

    fun initAdapter(): MultiTypeAdapter {
        adapter = MultiTypeAdapter()
        mItems = ArrayList()
        adapter.items = mItems
//        adapter.register(CommonListBean.DataBean.DatasBean::class.java,  CommonListViewBinder(mContext)) //使用java的公共 binder类
        adapter.register(CommonListBean.DataBean.DatasBean::class.java, KotlinItemBinder(mContext))  //使用kotlin binder类
        adapter.register(BannerBean::class.java, KotlinIBannerBinder(mContext))
        return adapter
    }

    override fun creatModel(): KotlinModle {
        return KotlinModle()
    }

    fun requestBanner(countPage: Int, b: Boolean) {
        isRefresh = b
        mCountPage = countPage
        mBaseModel.requestBannner()
    }

    fun bannerSucceed(response: BannerBean) {
        if (isRefresh) {
            mItems.clear()
            if (response != null) {
                mItems.add(0, response)
            }
        }
        mBaseModel.requestListData(mCountPage)
    }

    fun bannerError() {
        ToastUtils.showShort("bannner请求失败")
    }

    fun homeListSucceed(response: CommonListBean?) {
        if (response == null) {
            return
        }
        if (isRefresh) {
            mBaseView.mSmartRefresh.finishRefresh()
        } else {
            if (response.data.isOver) {
                mBaseView.mSmartRefresh.finishLoadMoreWithNoMoreData()
            } else {
                mBaseView.mSmartRefresh.finishLoadMore()
            }
        }

        for (index in response.data.datas.indices) {
            mItems.add(response.data.datas[index])
        }
        adapter.notifyDataSetChanged()
        mBaseView.mBaseLoadView.showContent()
        ToastUtils.showShort("请求成功")
    }

    fun homeListError() {
        ToastUtils.showShort("请求失败")
        if (isRefresh) {
            mBaseView.mSmartRefresh.finishRefresh(1000, false)
        } else {
            mBaseView.mSmartRefresh.finishLoadMore(1000, false, false)
        }

        if (mItems != null && mItems.size > 0) {
            mBaseView.mBaseLoadView.showContent()
        } else {
            mBaseView.mBaseLoadView.showEmptyData()
        }
    }
}

