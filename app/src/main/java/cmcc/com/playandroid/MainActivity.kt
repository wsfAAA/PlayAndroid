package cmcc.com.playandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setText("这是测试数据。。。。。。。。。。。。。。。。。")
        image.setImageResource(R.mipmap.ic_launcher)
    }
}
