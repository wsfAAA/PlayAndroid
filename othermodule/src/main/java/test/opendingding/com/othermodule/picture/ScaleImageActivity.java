package test.opendingding.com.othermodule.picture;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.view.EnjoyCropLayout;
import test.opendingding.com.othermodule.view.core.BaseLayerView;
import test.opendingding.com.othermodule.view.core.clippath.ClipPathLayerView;
import test.opendingding.com.othermodule.view.core.clippath.ClipPathSquare;
import test.opendingding.com.othermodule.view.core.mask.ColorMask;

public class ScaleImageActivity extends AppCompatActivity {

    EnjoyCropLayout enjoyCropLayout;
    private Button back;
    private ImageView img_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_image);


        back = findViewById(R.id.back);
        img_pic = findViewById(R.id.img_pic);
        enjoyCropLayout = (EnjoyCropLayout) findViewById(R.id.ll);
        enjoyCropLayout.setImageResource(R.drawable.cover1);//设置裁剪原图片
        //这里提供了默认的半透明圆形裁剪参数，你也自定义整个裁剪过程的参数，具体参见下面defineCropParams方法。
//        enjoyCropLayout.setDefaultCircleCrop(300);
        defineCropParams();
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //裁剪图片，注意这里得到的图片并未进行任何压缩，是裁剪出来的原图大小
                Bitmap bitmap = enjoyCropLayout.crop();
                if (bitmap!=null){
                    img_pic.setVisibility(View.VISIBLE);
                    img_pic.setImageBitmap(bitmap);
                }else {
                    img_pic.setVisibility(View.VISIBLE);
                    Toast.makeText(ScaleImageActivity.this,"图片空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_pic.setVisibility(View.GONE);
            }
        });
    }

    private void defineCropParams() {
        //设置裁剪集成视图，这里通过一定的方式集成了遮罩层与预览框
        BaseLayerView layerView = new ClipPathLayerView(this);
        layerView.setMask(ColorMask.getTranslucentMask()); //设置遮罩层,这里使用半透明的遮罩层
        layerView.setShape(new ClipPathSquare(300)); //设置预览框形状
        enjoyCropLayout.setLayerView(layerView); //设置裁剪集成视图
        enjoyCropLayout.setRestrict(true); //设置边界限制，如果设置了该参数，预览框则不会超出图片
    }
}
