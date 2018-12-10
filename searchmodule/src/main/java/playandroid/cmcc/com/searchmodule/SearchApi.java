package playandroid.cmcc.com.searchmodule;

import io.reactivex.Observable;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.bean.SearchHotKey;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by wsf on 2018/9/17.
 */

public interface SearchApi {


    /**
     * 搜索  0未页数 0代表第一页
     * @return
     */
    @FormUrlEncoded
    @POST("article/query/0/json")
    Observable<SearchBean> searchContent(@Field("k") String k);

    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    Observable<SearchHotKey> searchHotKey();

}
