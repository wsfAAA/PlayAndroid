package playandroid.cmcc.com.baselibrary.net.callback;

import com.google.gson.Gson;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.ResponseCallback;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * Created by wsf on 2018/12/6.
 *  自定义RxCallBack 扩展
 */
public abstract class RxCallBack<T> extends ResponseCallback<T, ResponseBody> {

    @Override
    public T onHandleResponse(ResponseBody response) throws Exception {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) new String(response.bytes());
        }
        String jstring = new String(response.bytes());
        return transform(jstring, entityClass);
    }

    /**
     * 请求开始
     *
     * @param tag
     */
    @Override
    public void onStart(Object tag) {
        super.onStart(tag);
    }

    /**
     * 处理请求成功回调
     *
     * @param tag
     * @param call
     * @param response
     */
    @Override
    public void onNext(Object tag, Call call, T response) {
        onRxSuccess(tag, response);
    }

    /**
     * 请求取消时回调
     *
     * @param tag
     * @param e
     */
    @Override
    public void onCancel(Object tag, Throwable e) {

    }

    /**
     * 请求错误回调
     *
     * @param tag
     * @param e
     */
    @Override
    public void onError(Object tag, Throwable e) {
        onRxError(tag, e);
    }

    /**
     * 请求失败回调
     *
     * @param e
     */
    @Override
    public void onFailure(Call call, IOException e) {
        super.onFailure(call, e);

    }

    /**
     * 请求完成
     *
     * @param tag
     */
    @Override
    public void onCompleted(Object tag) {
        super.onCompleted(tag);
    }

    /**
     * 请求进度
     *
     * @param tag
     * @param progress
     * @param transfered
     * @param total
     */
    @Override
    public void onProgress(Object tag, float progress, long transfered, long total) {
        super.onProgress(tag, progress, transfered, total);
    }

    @Override
    public void onProgress(Object tag, int progress, long speed, long transfered, long total) {
        super.onProgress(tag, progress, speed, transfered, total);
    }

    /**
     * 释放数据回调
     */
    @Override
    public void onRelease() {
        super.onRelease();
    }

    public abstract void onRxSuccess(Object tag, T response);

    public abstract void onRxError(Object tag, Throwable e);

    public T transform(String response, final Class classOfT) throws ClassCastException {
        return (T) new Gson().fromJson(response, classOfT);
    }
}
