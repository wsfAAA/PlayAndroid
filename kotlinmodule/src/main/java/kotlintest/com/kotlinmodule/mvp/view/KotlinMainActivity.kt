package kotlintest.com.kotlinmodule.mvp.view

import android.support.v7.widget.LinearLayoutManager
import cmcc.com.playandroid.common.CommonFinal
import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlintest.com.kotlinmodule.mvp.persenter.KotlinPersenter
import kotlinx.android.synthetic.main.activity_kotlin_main.*
import kotlinx.android.synthetic.main.activity_kotlin_main.view.*
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpActivity
import kotlin.com.kotlinmodule.R


/**
 * Created by wsf on 2019/3/29.
 */
@Route(path = CommonFinal.AROUTER_KOTLIN)
class KotlinMainActivity : BaseMvpActivity<KotlinPersenter>() {

    var mCountPage = 0

    override fun creatPersenter(): KotlinPersenter {
        return KotlinPersenter()
    }

    override fun getLayoutResID(): Int {
        return R.layout.activity_kotlin_main
    }

    override fun initView() {
//        mBaseLoadView.showLoading()
        mBasePresenter.requestListData(mCountPage,true)
        mBasePresenter.requestBanner()

        val layout= LinearLayoutManager(mContext)
        mRecyclerview.layoutManager=layout

        mRecyclerview.adapter=mBasePresenter.initAdapter()

        mSmartRefresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(p0: RefreshLayout) {

            }

            override fun onLoadMore(p0: RefreshLayout) {

            }
        })

    }
}
