package kotlintest.com.kotlinmodule.mvp.persenter

import kotlintest.com.kotlinmodule.mvp.module.KotlinModle
import kotlintest.com.kotlinmodule.mvp.view.KotlinMainActivity
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter

/**
 * Created by wsf on 2019/3/29.
 */
class KotlinPersenter : BasePresenter<KotlinMainActivity, KotlinModle>() {

    override fun creatModel(): KotlinModle {
        return KotlinModle()
    }

}

