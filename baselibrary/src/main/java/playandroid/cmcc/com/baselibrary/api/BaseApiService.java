package playandroid.cmcc.com.baselibrary.api;

/**
 * Created by wsf on 2018/12/14.
 */

public class BaseApiService {
//    public static final String BASE_URL = "http://wanandroid.com/";
    public static final String BASE_URL = "https://www.wanandroid.com";

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

    ///////////////////////////////////////首页/////////////////////////////////////////
    /**
     * 首页banner
     */
    public static final String BANNER = "banner/json";

    /**
     * 首页列表
     * article/list/1/json
     */
    public static final String HOME_LIST = "article/list/";

    ///////////////////////////////////////导航/////////////////////////////////////////
    /**
     * 导航数据
     */
    public static final String NAVIGATION = "navi/json";

    ///////////////////////////////////////发现/////////////////////////////////////////
    /**
     * 发现数据
     */
    public static final String DISCOVER = "tree/json";

    /**
     * 详情页   http://www.wanandroid.com/article/list/0/json?cid=60
     */
    public static final String DETAILS_CONTENT = "article/list/";


    ///////////////////////////////////////收藏/////////////////////////////////////////
    /**
     * 收藏 站外文章
     * http://www.wanandroid.com/lg/collect/add/json
     * 方法：POST
     * 参数：
     * title，author，link
     */
    public static final String ESSAY_COLLECT2 = "lg/collect/add/json";

    /**
     * 收藏 站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     * 方法：POST
     * 参数： 文章id，拼接在链接中。
     */
    public static final String ESSAY_COLLECT = "lg/collect/";

    /**
     * 取消收藏
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     * 方法：POST
     * 参数：
     * id:拼接在链接上
     */
    public static final String UN_ESSAY_COLLECT = "lg/uncollect_originId/";

    /**
     * 收藏列表
     * http://www.wanandroid.com/lg/collect/list/0/json
     * 方法：GET
     * 参数： 页码：拼接在链接中，从0开始。
     */
    public static final String COLLECT_LIST = "lg/collect/list/";
}
