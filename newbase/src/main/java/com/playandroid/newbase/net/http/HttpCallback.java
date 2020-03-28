package com.playandroid.newbase.net.http;

public interface HttpCallback<T> {

    void onSucceed(T data);

    void onError(String message);

}
