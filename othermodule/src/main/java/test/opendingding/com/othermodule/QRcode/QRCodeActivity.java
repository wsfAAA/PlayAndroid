package test.opendingding.com.othermodule.QRcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;

import test.opendingding.com.othermodule.R;

public class QRCodeActivity extends AppCompatActivity {

    private ScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        scannerView = findViewById(R.id.scannerView);

        scannerView.setOnScannerCompletionListener(new OnScannerCompletionListener() {
            @Override
            public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
                ParsedResultType type = parsedResult.getType();
                ToastUtils.showShort(type+"    "+rawResult);
                switch (type) {
                    case ADDRESSBOOK:
                        break;
                    case URI:
                        break;
                    case TEXT:
                        break;
                }
                Log.i("wsf", "rawResult: " + rawResult + "  ,  rawResult:  " + rawResult + "     ,type:  " + type);
            }
        });

        Button button1 = findViewById(R.id.bent1);
        final Button button2 = findViewById(R.id.bent2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button2.getText().toString().equals("开灯")) {
                    scannerView.toggleLight(true);//开
                    button2.setText("关灯");
                } else {
                    button2.setText("开灯");
                    scannerView.toggleLight(false);//关
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.onPause();
    }
}
