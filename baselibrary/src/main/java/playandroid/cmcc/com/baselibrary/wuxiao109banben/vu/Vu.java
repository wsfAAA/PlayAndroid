package playandroid.cmcc.com.baselibrary.wuxiao109banben.vu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.io.Serializable;

import playandroid.cmcc.com.baselibrary.wuxiao109banben.CallBack;

/**
 * Created by wsf on 2018/9/5.
 */

public interface Vu<T> extends Serializable {
    int getLayoutId();

    void init(LayoutInflater var1, ViewGroup var2);

    void init(Context var1);

    void delayInitData();

    int depth();

    void seDepth(int var1);

    boolean callBackStatus();

    boolean isCache();

    boolean isAllowMany();

    View getView();

    void onDestroy();

    void onPause();

    void onResume();

    void animStart();

    void animEnd();

    void customAnim(View var1, View var2);

    void setAnimSwitchTypeIn(AnimSwitchEnum var1);

   AnimSwitchEnum getAnimSwitchTypeIn();

    void setAnimSwitchTypeOut(AnimSwitchEnum var1);

    AnimSwitchEnum getAnimSwitchTypeOut();

    boolean isNeedMask();

    void bindData(T var1);

    void bindView();

    void setUserVisibleHint(boolean var1);

    void setAdapterPos(int var1);

    int getAdapterPos();

    void setCallBack(CallBack<Object> var1);
}
