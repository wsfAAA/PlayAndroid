package cmcc.com.playandroid.activity;


import cmcc.com.playandroid.bean.CommonListBean;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/1/23.
 */

public class DetailsContentModel extends BaseModel<DetailsContentPresenter> {

    /**
     * 发现页 根据id 请求详情数据
     *
     * @param mId       发现页类型id
     * @param pageCount 页数
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
     * 搜索页 根据 key 搜索 详情数据
     *
     * @param mes       搜索关键字
     * @param pageCount 页数
     */
    public void searchRequest(String mes, int pageCount) {
        RxClient.builder()
                .addParams("k", mes)
                .build()
                .rxPost(BaseApiService.SEARCH + pageCount + "/json", new RxCallBack<CommonListBean>() {
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

    /**
     * 收藏列表请求
     *
     * @param pageCount 页数
     */
    public void requestCollect(int pageCount) {
        RxClient.builder()
                .cache(false)
                .build()
                .rxGet(BaseApiService.COLLECT_LIST + pageCount + "/json", new RxCallBack<CommonListBean>() {
                    @Override
                    public void rxOnNext(CommonListBean response) {
                        if (response != null && response.getData() != null) {
                            // TODO: 2019/2/12  收藏列表中没有这个字段 添加
                            if (response.getData().getDatas() != null) {
                                for (int i = 0; i < response.getData().getDatas().size(); i++) {
                                    response.getData().getDatas().get(i).setCollect(true);
                                }
                            }
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
