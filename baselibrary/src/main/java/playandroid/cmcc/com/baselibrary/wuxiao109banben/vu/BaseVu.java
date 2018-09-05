package playandroid.cmcc.com.baselibrary.wuxiao109banben.vu;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import playandroid.cmcc.com.baselibrary.wuxiao109banben.CallBack;


/**
 * Created by wsf on 2018/9/5.
 */

public class BaseVu<T> implements Vu<T>, View.OnTouchListener {
    protected Context context;
    protected View view;
    protected AnimSwitchEnum animTypeIn;
    protected AnimSwitchEnum animTypeOut;
    protected int depth;
    protected int delayInitTime;
    protected boolean isNeedMask;
    protected int adapterPosition;
    protected String TAG;
    protected boolean isCache;
    protected CallBack<Object> callBack;

    public BaseVu() {
        this.animTypeIn = AnimSwitchEnum.RightToLift;
        this.animTypeOut =AnimSwitchEnum.LeftToRight;
        this.depth = 0;
        this.delayInitTime = 300;
        this.isNeedMask = true;
        this.adapterPosition = 0;
        this.TAG = this.getClass().getSimpleName();
        this.isCache = true;
    }

    public BaseVu(Context context) {
        this.animTypeIn = AnimSwitchEnum.RightToLift;
        this.animTypeOut = AnimSwitchEnum.LeftToRight;
        this.depth = 0;
        this.delayInitTime = 300;
        this.isNeedMask = true;
        this.adapterPosition = 0;
        this.TAG = this.getClass().getSimpleName();
        this.isCache = true;
        this.init(LayoutInflater.from(context), (ViewGroup)null);
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
               BaseVu.this.delayInitData();
            }
        }, (long)this.delayInitTime);
    }

    public BaseVu(Context context, ViewGroup container) {
        this.animTypeIn =AnimSwitchEnum.RightToLift;
        this.animTypeOut = AnimSwitchEnum.LeftToRight;
        this.depth = 0;
        this.delayInitTime = 300;
        this.isNeedMask = true;
        this.adapterPosition = 0;
        this.TAG = this.getClass().getSimpleName();
        this.isCache = true;
        this.init(LayoutInflater.from(context), container);
    }

    public boolean isNeedMask() {
        return this.isNeedMask;
    }

    public void setNeedMask(boolean needMask) {
        this.isNeedMask = needMask;
    }

    public void init(Context context) {
        this.init(LayoutInflater.from(context), (ViewGroup)null);
    }

    public void delayInitData() {
    }

    public AnimSwitchEnum getAnimTypeIn() {
        return this.animTypeIn;
    }

    public void setAnimTypeIn(AnimSwitchEnum animTypeIn) {
        this.animTypeIn = animTypeIn;
    }

    public AnimSwitchEnum getAnimTypeOut() {
        return this.animTypeOut;
    }

    public void setAnimTypeOut(AnimSwitchEnum animTypeOut) {
        this.animTypeOut = animTypeOut;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDelayInitTime() {
        return this.delayInitTime;
    }

    public void setDelayInitTime(int delayInitTime) {
        this.delayInitTime = delayInitTime;
    }

    public int depth() {
        return this.depth;
    }

    public void seDepth(int depth) {
        this.depth = depth;
    }

    public boolean callBackStatus() {
        return true;
    }

    public int getLayoutId() {
        return 0;
    }

    public void init(LayoutInflater inflater, ViewGroup container) {
        if(this.getLayoutId() != 0) {
            this.view = inflater.inflate(this.getLayoutId(), container, false);
            this.context = this.view.getContext();
            this.bindView();
        }

    }

    public View getView() {
        return this.view;
    }

    public void onDestroy() {
        this.context = null;
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void animStart() {
    }

    public void animEnd() {
    }

    public void customAnim(View currentView, View lastView) {
    }

    public boolean isCache() {
        return this.isCache;
    }

    public boolean isAllowMany() {
        return false;
    }

    public void setCache(boolean cache) {
        this.isCache = cache;
    }

    public void setAnimSwitchTypeIn(AnimSwitchEnum animType) {
        if(animType != null) {
            this.animTypeIn = animType;
            this.setAnimSwitchTypeOut(animType);
        }

    }

    public AnimSwitchEnum getAnimSwitchTypeIn() {
        return this.animTypeIn;
    }

    public void setAnimSwitchTypeOut(AnimSwitchEnum animType) {
        if(animType != null) {
            this.animTypeOut = animType;
        }

    }

    public AnimSwitchEnum getAnimSwitchTypeOut() {
        return this.animTypeOut;
    }

    public void bindData(T data) {
    }

    public void bindView() {
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
    }

    public void setAdapterPos(int pos) {
        this.adapterPosition = pos;
    }

    public int getAdapterPos() {
        return this.adapterPosition;
    }

    public void setCallBack(CallBack<Object> callBack) {
        this.callBack = callBack;
    }

    public void show() {
        if(this.view != null) {
            this.view.setVisibility(View.VISIBLE);
        }

    }

    public void hide() {
        if(this.view != null) {
            View var10001 = this.view;
            this.view.setVisibility(View.GONE);
        }

    }

    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}

