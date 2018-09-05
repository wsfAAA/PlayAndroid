package playandroid.cmcc.com.baselibrary.wuxiao109banben.vu;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;
import java.util.Stack;

import playandroid.cmcc.com.baselibrary.wuxiao109banben.util.MgUtil;

/**
 * Created by wsf on 2018/9/5.
 */

public class VuManager {
    private final String TAG = "VuManager";
    private Stack<Vu> vuStack = new Stack();
    private static VuManager instance;
    public ViewGroup containerViewContainer;
    public View mainBody;
    public final int AnimTime = 300;
    public int sWidth = 0;
    public int sHeight = 0;
    private Context context;
    private boolean exitFlag = false;
    private final long intervalTime = 2000L;
    private Handler mhandler = new Handler();
    private View maskView;

    public static VuManager getInstance() {
        if(instance == null) {
            instance = new VuManager();
        }

        return instance;
    }

    private VuManager() {
    }

    public void initContainerView(ViewGroup containerViewContainer) {
        this.containerViewContainer = containerViewContainer;
        this.context = containerViewContainer.getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.sWidth = wm.getDefaultDisplay().getWidth();
        this.sHeight = wm.getDefaultDisplay().getHeight();
    }

    public View getMainBody() {
        return this.mainBody;
    }

    public void setMainBody(View mainBody) {
        this.mainBody = mainBody;
    }

    public boolean backClick() {
        if(MgUtil.filter()) {
            if(this.vuStack.size() > 0) {
                Vu topVu = this.vuStack.peek();
                if(topVu.callBackStatus()) {
                    this.popVu();
                } else if(this.exitFlag) {
                    ((Activity)this.context).finish();
                    System.exit(0);
                } else {
                    this.exitFlag = true;
                    Toast.makeText(this.context, "再点击一次返回键，退出程序",  Toast.LENGTH_LONG).show();
                    this.mhandler.postDelayed(new Runnable() {
                        public void run() {
                           VuManager.this.exitFlag = false;
                        }
                    }, 2000L);
                }

                return true;
            }

            if(this.exitFlag) {
                ((Activity)this.context).finish();
                System.exit(0);
            } else {
                this.exitFlag = true;
                Toast.makeText(this.context, "再点击一次返回键，退出程序",  Toast.LENGTH_LONG).show();
                this.mhandler.postDelayed(new Runnable() {
                    public void run() {
                        VuManager.this.exitFlag = false;
                    }
                }, 2000L);
            }
        }

        return false;
    }

    /**
     * 视图出栈
     */
    public void popVu(){
        if (!vuStack.isEmpty()) {
            final Vu vu = vuStack.pop();
            synchronized (this) {
                if (vu == null)
                    return;
                Animator.AnimatorListener listener = new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        vu.animStart();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (!vuStack.isEmpty()) {     //如果视图不为空，peek Vu onResume
                            vuStack.peek().onResume();
                        }
                        vu.animEnd();
                        containerViewContainer.removeView(vu.getView());
                        vu.onDestroy();               //视图出栈，调用onDestroy释放资源
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                };
                if(mainBody!=null)
                    mainBody.setVisibility(View.VISIBLE);
                if (vu.isNeedMask()) {
                    alpha(false, containerViewContainer);
                }
                if (vu.getAnimSwitchTypeOut() == AnimSwitchEnum.BottomToUp ) {        //从下到上消失
                    if (vuStack.size() >= 1) {
                        Vu lastVu = vuStack.get(vuStack.size() - 1);
                        lastVu.getView().animate().setDuration(AnimTime).translationX(0).translationY(0).setInterpolator(new LinearOutSlowInInterpolator());
                    } else {
                        if(mainBody!=null){
                            mainBody.animate().setDuration(AnimTime - 100).translationX(0).translationY(0).setInterpolator(new LinearOutSlowInInterpolator());
                        }
                    }
                    if (vu.getView() != null) {
                        vu.getView().animate().setDuration(AnimTime).translationY(sHeight).
                                setInterpolator(new LinearOutSlowInInterpolator()).
                                setListener(listener);
                    }
                } else if (vu.getAnimSwitchTypeOut() == AnimSwitchEnum.TopToDown) {  //从上到下
                    if (vuStack.size() >= 1) {
                        Vu lastVu = vuStack.get(vuStack.size() - 1);
                        lastVu.getView().animate().setDuration(AnimTime).translationX(0).translationY(0).setInterpolator(new LinearOutSlowInInterpolator());
                    } else {
                        if(mainBody!=null){
                            mainBody.animate().setDuration(AnimTime - 100).translationX(0).translationY(0).setInterpolator(new LinearOutSlowInInterpolator());
                        }
                    }
                    vu.getView().animate().setDuration(AnimTime).setInterpolator(new LinearOutSlowInInterpolator())
                            .translationY(-sHeight).setListener(listener);
                } else if (vu.getAnimSwitchTypeOut() == AnimSwitchEnum.RightToLift) { //右到左
                    if (vuStack.size() >= 1) {
                        Vu lastVu = vuStack.get(vuStack.size() - 1);
                        lastVu.getView().animate().setDuration(AnimTime).translationX(0).translationY(0).setInterpolator(new LinearOutSlowInInterpolator());
                    } else {
                        if(mainBody!=null){
                            mainBody.setVisibility(View.VISIBLE);
                            mainBody.setX(sWidth);
                            mainBody.animate().setDuration(AnimTime).translationX(0).translationY(0).setInterpolator(new LinearOutSlowInInterpolator());
                        }
                    }
                    vu.getView().animate().setDuration(AnimTime).translationX(-sWidth).
                            setInterpolator(new LinearOutSlowInInterpolator()).
                            setListener(listener);
                }  else if (vu.getAnimSwitchTypeOut() == AnimSwitchEnum.LeftToRight) {  //左到右
                    if (vuStack.size() >= 1) {
                        Vu lastVu = vuStack.get(vuStack.size() - 1);
                        lastVu.getView().setX(-sWidth);
                        lastVu.getView().animate().setDuration(AnimTime).translationX(0).setInterpolator(new LinearOutSlowInInterpolator());
                    } else {
                        if(mainBody!=null){
                            mainBody.setVisibility(View.VISIBLE);
                            mainBody.setX(-sWidth);
                            mainBody.animate().setDuration(AnimTime ).translationX(0).setInterpolator(new LinearOutSlowInInterpolator());
                        }
                    }
                    vu.getView().animate().setDuration(AnimTime).translationX(sWidth).
                            setInterpolator(new LinearOutSlowInInterpolator()).
                            setListener(listener);
                }else if (vu.getAnimSwitchTypeOut() == AnimSwitchEnum.None) {
                    if (vuStack.size() >= 1) {
                        Vu lastVu = vuStack.get(vuStack.size() - 1);
                        lastVu.getView().animate().setDuration(AnimTime).translationX(0).setInterpolator(new LinearOutSlowInInterpolator());
                    } else {
                        if(mainBody!=null){
                            mainBody.animate().setDuration(AnimTime - 100).translationX(0).setInterpolator(new LinearOutSlowInInterpolator());
                        }
                    }
                    vu.getView().animate().setDuration(0).alpha(0).
                            setInterpolator(new LinearOutSlowInInterpolator()).
                            setListener(listener);
                }else if (vu.getAnimSwitchTypeIn() == AnimSwitchEnum.Custom) {     //自定义动画
                    View lastView=null;
                    if (vuStack.size() >= 1) {
                        Vu lastVu = vuStack.get(vuStack.size() - 1);
                        lastView=lastVu.getView();
                    } else {
                        lastView=mainBody;
                    }
                    vu.customAnim(vu.getView(),lastView);
                }
            }
        }
    }

    /**
     * 视图入栈
     * @param vu
     */
    public void pushVu(Vu vu){
        synchronized (this) {
            if (vu == null)     //Vu为空不处理
                return;
            if (!vuStack.isEmpty()) {
                Vu peekVu=vuStack.peek();
                if(peekVu.getClass() == vu.getClass()){
                    if (!vu.isAllowMany()) {            //同一个视图不允许加载两次
                        vu.onDestroy();
                        return;
                    }
                }
            }
            synchronized (vu) {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                final View view = vu.getView();
                Animator.AnimatorListener listener = new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        try{
                            if(mainBody!=null){
                                mainBody.setVisibility(View.GONE);
                            }
                            int stackSize=vuStack.size();
                            if(stackSize>=2){
                                Vu vu= vuStack.get(stackSize-2);
                                if(vu!=null&&!vu.isCache()){ //视图入栈时，栈顶是否缓存，不缓存，移除栈顶视图
                                    if (vu.isNeedMask()) {   //是否有遮罩，如果有去掉遮罩
                                        {
                                            alpha(false, containerViewContainer);
                                        }
                                    }
                                    containerViewContainer.removeView(vu.getView());
                                    vu.onDestroy();
                                    vuStack.remove(vu);
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                };

                if (vu.isNeedMask()) {   //是否添加遮罩
                    alpha(true, containerViewContainer);
                }
                containerViewContainer.addView(view, layoutParams);   //添加视图到容器
                vuStack.add(vu);         //把Vu入栈

                //Vu动画实现
                if (vu.getAnimSwitchTypeIn() == AnimSwitchEnum.LeftToRight) {   //左到右
                    view.setX(-sWidth);                                         //初始化view x坐标位置
                    view.animate().setListener(listener);
                    view.animate().setDuration(AnimTime).translationX(0).setInterpolator(new LinearOutSlowInInterpolator());
                    if (vuStack.size() >= 2) {              //判断是否有上一个视图
                        Vu lastVu= vuStack.get(vuStack.size() - 2);
                        if (lastVu.getView() != null){      //上一个视图做动画
                            lastVu.getView().animate().setDuration(AnimTime).translationX(sWidth).setInterpolator(new LinearOutSlowInInterpolator());
                        }
                    } else {      //如果栈内只有一个Vu,则mainBody做移动动画
                        if(mainBody!=null)
                            mainBody.animate().setDuration(AnimTime ).translationX(sWidth).setInterpolator(new LinearOutSlowInInterpolator());
                    }

                }else if (vu.getAnimSwitchTypeIn() == AnimSwitchEnum.RightToLift) {   //右到左
                    view.setX(sWidth);                                                //初始化view x坐标位置
                    view.animate().setListener(listener);
                    view.animate().setDuration(AnimTime).translationX(0).setInterpolator(new LinearOutSlowInInterpolator());
                    if (vuStack.size() >= 2) {
                        Vu lastVu = vuStack.get(vuStack.size() - 2);
                        if (lastVu.getView() != null)
                            lastVu.getView().animate().setDuration(AnimTime).translationX(-sWidth).setInterpolator(new LinearOutSlowInInterpolator());
                    } else {
                        if(mainBody!=null)
                            mainBody.animate().setDuration(AnimTime).translationX(-sWidth).setInterpolator(new LinearOutSlowInInterpolator());
                    }

                } else if (vu.getAnimSwitchTypeIn() == AnimSwitchEnum.TopToDown) {  //从上到下
                    if (vu.isNeedMask()) {
                        alpha(true, containerViewContainer);
                    }
                    view.setY(-sHeight);                                             //初始化view y坐标位置
                    view.animate().setDuration(AnimTime).translationY(0).setInterpolator(new LinearOutSlowInInterpolator()).setListener(listener);
                }  else if (vu.getAnimSwitchTypeIn() == AnimSwitchEnum.BottomToUp) {
                    if (vu.isNeedMask()) {
                        alpha(true, containerViewContainer);
                    }
                    view.setY(sHeight);
                    view.animate().setDuration(AnimTime).setInterpolator(new AccelerateDecelerateInterpolator())
                            .translationY(vu.getView().getMeasuredHeight()).setListener(listener);
                }else if (vu.getAnimSwitchTypeIn() == AnimSwitchEnum.Custom) {     //自定义动画
                    View lastView=null;
                    if (vuStack.size() >= 2) {
                        Vu lastVu = vuStack.get(vuStack.size() - 2);
                        lastView=lastVu.getView();
                    } else {
                        lastView=mainBody;
                    }
                    vu.customAnim(view,lastView);
                }
                vu.onResume();
            }
        }
    }

    public void alpha(boolean flag, ViewGroup vContainer) {
        if(flag) {
            if(this.maskView == null) {
                this.maskView = new View(this.context);
                this.maskView.setBackgroundColor(Color.parseColor("#99000000"));
                this.maskView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -1));
            }

            if(this.maskView.getParent() == null) {
                vContainer.addView(this.maskView);
            }
        } else if(this.maskView != null) {
            vContainer.removeView(this.maskView);
        }

    }
}

