package cmcc.com.playandroid.viewbyid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;

import androidx.appcompat.app.AppCompatActivity;
import cmcc.com.playandroid.R;

public class ViewByIdActivity extends AppCompatActivity {

    @ViewById(R.id.tv_text)
    TextView textView;
    @ViewById(R.id.tv_text_tow)
    TextView textViewTow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_by_id);
        inject(this);

        textView.setText("ViewById one");
        textViewTow.setText("ViewById Tow");
    }

    public static void inject(Activity activity) {
        // 1.获取所有的属性
        Field[] fields = activity.getClass().getDeclaredFields();
        // 2.过滤关于 ViewById 属性
        for (Field field : fields) {
            ViewById viewById = field.getAnnotation(ViewById.class);  // 获取注解
            if (viewById != null) {
                // 3.findViewById
                View view = activity.findViewById(viewById.value());
                try {
                    // 4.反射注入 , 把对应的值注入到对应属性中
                    field.setAccessible(true);  //设置权限
                    field.set(activity, view);  // activity 代表的是属性所在类，view 代表的是属性的值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
