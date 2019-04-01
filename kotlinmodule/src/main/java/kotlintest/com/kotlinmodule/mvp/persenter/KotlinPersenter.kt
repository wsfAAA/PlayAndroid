package kotlintest.com.kotlinmodule.mvp.persenter

import cmcc.com.playandroid.adapter.CommonListViewBinder
import cmcc.com.playandroid.bean.CommonListBean
import com.blankj.utilcode.util.ToastUtils
import kotlintest.com.kotlinmodule.BannerBean
import kotlintest.com.kotlinmodule.adapter.KotlinItemBinder
import kotlintest.com.kotlinmodule.mvp.module.KotlinModle
import kotlintest.com.kotlinmodule.mvp.view.KotlinMainActivity
import me.drakeet.multitype.MultiTypeAdapter
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter

/**
 * Created by wsf on 2019/3/29.
 */
class KotlinPersenter : BasePresenter<KotlinMainActivity, KotlinModle>() {
    var isRefresh = false
    private lateinit var adapter: MultiTypeAdapter
    private lateinit var mItems: MutableList<Any>

    fun initAdapter(): MultiTypeAdapter {
        adapter = MultiTypeAdapter()
        mItems = ArrayList()
        adapter.items = mItems
//        adapter.register(CommonListBean.DataBean.DatasBean::class.java,  CommonListViewBinder(mContext)) //使用java的公共 binder类
        adapter.register(CommonListBean.DataBean.DatasBean::class.java,KotlinItemBinder())
        return adapter
    }

    override fun creatModel(): KotlinModle {
        return KotlinModle()
    }

    fun requestListData(mCountPage: Int, b: Boolean) {
        isRefresh = b
        mBaseModel.requestListData(mCountPage)
    }

    fun requestBanner() {
        mBaseModel.requestBannner()

    }

    fun bannerSucceed(response: BannerBean) {

    }

    fun bannerError() {


    }

    fun homeListSucceed(response: CommonListBean?) {
        if (response == null) {
            return
        }

        if (isRefresh) {
            mItems.clear()
        }

        for (index in response.data.datas.indices) {
            var datasBean = response.data.datas[index]
            mItems.add(datasBean)
        }
        adapter.notifyDataSetChanged()
        ToastUtils.showShort("请求成功")
    }

    fun homeListError() {
        ToastUtils.showShort("请求失败")
    }

}

