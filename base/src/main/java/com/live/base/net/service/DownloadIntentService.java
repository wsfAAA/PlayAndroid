package com.live.base.net.service;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;

import com.live.base.net.RxClient;
import com.live.base.net.RxClientBuilder;
import com.live.base.net.callback.DownloadListener;

import androidx.annotation.Nullable;

public class DownloadIntentService extends IntentService {

    private String download_file_name;
    private String download_path;
    private String downloadUrl;
    private String download_base_url;

    public DownloadIntentService() {
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        downloadUrl = intent.getExtras().getString("download_url");
        download_file_name = intent.getExtras().getString("download_file_name");
        download_path = intent.getExtras().getString("download_path");
        download_base_url = intent.getExtras().getString("download_base_url");

        RxClientBuilder builder = RxClient.builder();
        if (!TextUtils.isEmpty(download_base_url)) {
            builder.baseUrl(download_base_url);
        }
        builder.build().rxDownload(downloadUrl, download_path, download_file_name, new DownloadListener() {
            @Override
            public void onStart(int progress) {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onCompleted(String path) {

            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
