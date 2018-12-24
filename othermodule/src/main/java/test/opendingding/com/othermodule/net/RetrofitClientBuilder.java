package test.opendingding.com.othermodule.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import test.opendingding.com.othermodule.net.callback.IError;
import test.opendingding.com.othermodule.net.callback.IFailure;
import test.opendingding.com.othermodule.net.callback.IRequest;
import test.opendingding.com.othermodule.net.callback.ISuccess;
import test.opendingding.com.othermodule.net.ui.LoaderStyle;

/**
 * Created by wsf on 2018/11/27.
 * 使用构建者模式
 */
public final class RetrofitClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();//WeakHashMap 不在使用时会被gc
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;

    private String mDownloadDir = null;  //文件夹
    private String mExtension = null;   //文件后缀名
    private String mName = null;    //文件名

    public final RetrofitClient build() {
        return new RetrofitClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody, mContext, mLoaderStyle,mFile,mDownloadDir,mExtension,mName);
    }

    public RetrofitClientBuilder() {
    }

    public final RetrofitClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    /**
     * 源数据  json
     * @param raw json格式 或者加密后josn String
     * @return
     */
    public final RetrofitClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }
    /**
     * 添加参数
     *
     * @param params
     * @return
     */
    public final RetrofitClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RetrofitClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RetrofitClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RetrofitClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }


    //////////////////////////////////////相关错误成功接口回调/////////////////////////////////////
    public final RetrofitClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RetrofitClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RetrofitClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RetrofitClientBuilder onRequest(IRequest mIRequest) {
        this.mIRequest = mIRequest;
        return this;
    }

    ///////////////////////////////////////加载样式//////////////////////////////////////////////
    /**
     * 加载样式支持 LoaderStyle 枚举类型
     *
     * @param context
     * @param style
     * @return
     */
    public final RetrofitClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    /**
     * 默认样式 BallClipRotatePulseIndicator
     *
     * @param context
     * @return
     */
    public final RetrofitClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }


    ///////////////////////////////////下载相关////////////////////////////////////////////////////
    public final RetrofitClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RetrofitClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RetrofitClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }
}
