package cmcc.com.playandroid.common;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/2/3.
 */

public class CommonRequest {


    /**
     * 收藏站外文章
     *
     * @param title
     * @param author
     * @param link
     */
    public static void requsetCollect(String title, String author, String link) {
        RxClient.builder()
                .cache(false)
                .addParams("title", title)
                .addParams("author", author)
                .addParams("link", link)
                .build()
                .rxPost(BaseApiService.ESSAY_COLLECT, new RxCallBack<String>() {
                    @Override
                    public void rxOnNext(String response) {
                        Log.i("wsf", "resL:   " + response);
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        Log.i("wsf", "e:   " + e);
                    }
                });
    }

    /**
     * 收藏站内文章
     *
     * @param id
     */
    public static void requsetCollect(int id) {
        RxClient.builder()
                .cache(false)
                .build()
                .rxPost(BaseApiService.ESSAY_COLLECT + id + "/json", new RxCallBack<String>() {
                    @Override
                    public void rxOnNext(String response) {
                        Log.i("wsf", "resL:   " + response);
                        if (TextUtils.isEmpty(response)) {
                            return;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int errorCode = jsonObject.optInt("errorCode");
                            if (errorCode == 0) {
                                if (mCollectCallblak != null) {
                                    mCollectCallblak.succeed(true);
                                }
                            } else {
                                ToastUtils.showShort("收藏失败： " + errorCode);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        ToastUtils.showShort("收藏失败： " + e);
                    }
                });
    }

    /**
     * 取消收藏
     *
     * @param id
     */
    public static void requsetUnCollect(int id) {
        RxClient.builder()
                .cache(false)
                .build()
                .rxPost(BaseApiService.UN_ESSAY_COLLECT + id + "/json", new RxCallBack<String>() {
                    @Override
                    public void rxOnNext(String response) {
                        if (TextUtils.isEmpty(response)) {
                            return;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int errorCode = jsonObject.optInt("errorCode");
                            if (errorCode == 0) {
                                if (mCollectCallblak != null) {
                                    mCollectCallblak.succeed(false);
                                }
                            } else {
                                ToastUtils.showShort("取消收藏失败： " + errorCode);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        ToastUtils.showShort("取消收藏失败： " + e);
                    }
                });
    }

    private static CollectCallblak mCollectCallblak;

    public static void setCollectCallblak(CollectCallblak collectCallblak) {
        mCollectCallblak = collectCallblak;
    }

    public interface CollectCallblak {
        void succeed(boolean collect);
    }
}
