package playandroid.cmcc.com.baselibrary.wuxiao109banben.vu;

import java.io.Serializable;

/**
 * Created by wsf on 2018/9/5.
 */

public enum AnimSwitchEnum implements Serializable {
    None(0),
    RightToLift(1),
    LeftToRight(2),
    BottomToUp(3),
    TopToDown(4),
    Custom(5);

    public int value = 0;

    private AnimSwitchEnum(int value) {
        this.value = value;
    }
}
