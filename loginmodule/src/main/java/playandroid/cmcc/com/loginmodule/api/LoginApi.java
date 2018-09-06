package playandroid.cmcc.com.loginmodule.api;


import io.reactivex.Observable;
import playandroid.cmcc.com.loginmodule.bean.LoginRegisterBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by yutao on 2018/1/22.
 * 接口实现
 */

public interface LoginApi {

    public static final String baseUrl="http://www.wanandroid.com/";

    /**
     * 登录
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginRegisterBean> login(@Field("username") String username,
                                        @Field("password") String userpassword);

    /**
     * 注册
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<LoginRegisterBean> register(@Field("username") String username,
                                           @Field("password") String password,
                                           @Field("repassword") String repassword);

    /**
     * 退出登录
     * @return
     */
    @GET("user/logout/json")
    Observable<Object> logout();
}