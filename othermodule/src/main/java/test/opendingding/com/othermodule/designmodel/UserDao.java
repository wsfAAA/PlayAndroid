package test.opendingding.com.othermodule.designmodel;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;

/**
 * 目标对象
 */
public class UserDao implements IUserDao {

    @Override
    public void addRecord(String mes) {
        Log.i("wsf", "" + mes);
        if (!TextUtils.isEmpty(mes)) {
            ToastUtils.showShort(mes);
        }
    }
}
