package cmcc.com.playandroid.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cmcc.com.playandroid.R

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        var bbb = null;
        //类型后面加?表示可为空
        var age: String? = bbb
        //不做处理返回 null
        val ages1 = age?.toInt()
        //age为空返回-1
        val ages2 = age?.toInt() ?: -1

        Log.e("wsf", "age:  " + age + "    ages1: " + ages1 + "    ages2: " + ages2);

        var aaa = ages1.toString()

        Log.e("wsf", "age:  " + age + "    ages1: " + ages1 + "    ages2: " + ages2+"   aaa:  "+aaa);

        //抛出空指针异常 , 程序闪退
        val ages = age!!.toInt()
    }
}
