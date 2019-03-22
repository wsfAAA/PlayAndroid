package com.ccm.idataservice.search;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by wsf on 2019/3/22.
 */

public interface ISearchService extends IProvider {
    /**
     * 用于初始化 相关module sdk
     *
     * @param context
     */
    void initSDK(Context context);

    void goToSearch(Context context,String message);
}
