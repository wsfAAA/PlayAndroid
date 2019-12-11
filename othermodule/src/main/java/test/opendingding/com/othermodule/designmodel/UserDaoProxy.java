package test.opendingding.com.othermodule.designmodel;

import android.util.Log;

/**
 * 代理对象
 */
public class UserDaoProxy implements IUserDao {

    private UserDao target;

    public UserDaoProxy(UserDao target) {
        this.target = target;
    }

    @Override
    public void addRecord(String mes) {
        Log.i("wsf", "开始执行任务。。。。");
        if (target != null) {
            target.addRecord(mes);
        }
        Log.i("wsf", "提交执行任务。。。。");
    }
}
