package kotlintest.com.kotlinmodule.mvp.view

import cmcc.com.playandroid.common.CommonFinal
import com.alibaba.android.arouter.facade.annotation.Route
import kotlintest.com.kotlinmodule.mvp.persenter.KotlinPersenter
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpActivity
import kotlin.com.kotlinmodule.R

/**
 * Created by wsf on 2019/3/29.
 */
@Route(path = CommonFinal.AROUTER_KOTLIN)
class KotlinMainActivity : BaseMvpActivity<KotlinPersenter>() {

    override fun creatPersenter(): KotlinPersenter {
        return KotlinPersenter()
    }

    override fun getLayoutResID(): Int {
        return R.layout.activity_kotlin_main
    }

    override fun initView() {

    }
}
