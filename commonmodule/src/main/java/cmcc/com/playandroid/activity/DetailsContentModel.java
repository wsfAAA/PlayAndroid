package cmcc.com.playandroid.activity;


import cmcc.com.playandroid.bean.CommonListBean;
import cmcc.com.playandroid.activity.DetailsContentPresenter;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/1/23.
 */

public class DetailsContentModel extends BaseModel<DetailsContentPresenter> {

    /**
     *  根据id 请求详情数据
     * @param mId
     * @param pageCount
     */
    public void requestData(int mId, int pageCount) {
        RxClient.builder().cache(false).build().rxGet(BaseApiService.DETAILS_CONTENT + pageCount + "/json?cid=" + mId, new RxCallBack<CommonListBean>() {
            @Override
            public void rxOnNext(CommonListBean response) {
                if (response != null && response.getData() != null && response.getData().getDatas() != null) {
                    mBasePresenter.succeed(response);
                } else {
                    mBasePresenter.error();
                }
            }

            @Override
            public void rxOnError(Throwable e) {
                mBasePresenter.error();
            }
        });
    }

    /**
     *  根据 key 搜索 详情数据
     * @param mes
     * @param pageCount
     */
    public void searchRequest(String mes,int pageCount) {
        RxClient.builder()
                .addParams("k", mes)
                .build()
                .rxPost(BaseApiService.SEARCH+pageCount+"/json", new RxCallBack<CommonListBean>() {
                    @Override
                    public void rxOnNext(CommonListBean response) {
                        if (response != null && response.getData() != null && response.getData().getDatas().size() > 0) {
                            mBasePresenter.succeed(response);
                        } else {
                            mBasePresenter.error();
                        }
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        mBasePresenter.error();
                    }
                });
    }
}
