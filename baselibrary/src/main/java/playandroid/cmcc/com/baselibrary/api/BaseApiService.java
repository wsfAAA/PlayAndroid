package playandroid.cmcc.com.baselibrary.api;

/**
 * Created by wsf on 2018/12/14.
 */

public class BaseApiService {
    public static final String BASE_URL = "http://wanandroid.com/";

    ////////////////////////////////////搜索相关/////////////////////////////////////////
    /**
     * 热词
     */
    public static final String SEACH_HOTKEY = "hotkey/json";

    /**
     * 搜索
     * 页码：拼接在链接上，从0开始。  article/query/0/json
     * k ： 搜索关键词
     */
    public static final String SEARCH = "article/query/";

    ////////////////////////////////////登录相关/////////////////////////////////////////
    /**
     * 注册
     */
    public static final String REGISTER = "user/register";
    /**
     * 登录
     */
    public static final String LOGIN = "user/login";
}
