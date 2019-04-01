package kotlintest.com.kotlinmodule.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cmcc.com.playandroid.bean.CommonListBean
import me.drakeet.multitype.ItemViewBinder
import kotlin.com.kotlinmodule.R

/**
 * Created by wsf on 2019/4/1.   MultiType版本的 kotlin 使用
 */
class KotlinItemBinder : ItemViewBinder<CommonListBean.DataBean.DatasBean,KotlinItemBinder.ViewHolder >(){

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_common_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: CommonListBean.DataBean.DatasBean) {

    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
//        val fooView: TextView = itemView.findViewById(R.id.foo)
    }
}