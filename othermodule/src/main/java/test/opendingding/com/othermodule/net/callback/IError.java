package test.opendingding.com.othermodule.net.callback;

public interface IError {

    /**
     * 请求错误
     * @param code 失败码
     * @param msg  失败信息
     */
    void onError(int code, String msg);
}
