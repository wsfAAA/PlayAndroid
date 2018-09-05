package playandroid.cmcc.com.baselibrary.wuxiao109banben.vu;

import java.io.Serializable;

/**
 * Created by wsf on 2018/9/5.
 */

public interface VuCallback<T> extends Serializable {
    void execute(T var1);
}
