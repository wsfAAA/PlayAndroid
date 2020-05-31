package cmcc.com.playandroid

import com.live.base.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity() {
    override fun initView() {
        textView.setText("这是测试数据。。。。。。。。。。。。。。。。。")
        image.setImageResource(R.mipmap.ic_launcher)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

}
