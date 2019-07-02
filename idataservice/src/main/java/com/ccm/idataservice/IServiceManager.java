package com.ccm.idataservice;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ccm.idataservice.search.ISearchService;

public class IServiceManager {
    private static IServiceManager serviceManager;

    private IServiceManager() {

    }

    public static IServiceManager getInstance() {
        if (serviceManager == null) {
            synchronized (IServiceManager.class) {
                if (serviceManager == null) {
                    serviceManager = new IServiceManager();
                }
            }
        }
        return serviceManager;
    }


    private ISearchService iSearchService;

    public ISearchService getISearchService() {
        if(iSearchService==null){
            iSearchService = ARouter.getInstance().navigation(ISearchService.class);
        }
        return iSearchService;
    }

    private ILoginService iLoginService;

    public ILoginService getILoginService(){
        if(iLoginService==null){
            iLoginService = ARouter.getInstance().navigation(ILoginService.class);
        }
        return iLoginService;
    }
}
