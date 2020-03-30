package com.live.base.net.callback;

public interface DownloadListener {
    void onStart(int progress);
    void onProgress(int progress);
    void onCompleted(String path);
    void onError(String msg);
}