package com.live.base.net.api;

/**
 * Created by wsf on 2018/12/14.
 */

public class ApiService {

    //私钥
    public static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ1HVMHZnIFd/RYwdy/ccJz74ZTGdDOswbFU/9iGSK6y50W08pFkMNDo+0qlp8uQ8YNuPhqHN+lbGOfZ4qDWY0eZ8GRXaJ5IX6/rWwhmLBQ8v9BPDbvnC78PfeccFz6IIPrLnPXA4W4irtwgx9zlOgtzTf97nebPI45hAY5/GxtRAgMBAAECgYB6Kz1oMrwCNVrxUIZUdBw5pOJOKXV76laug8MUkwKESU2idPLzAut0a3U/P7w+QQGJQSoWezBwIKYQ+suyebW1XtptezOROrs/0cUBqQj2i8G75a0IOHHkKAoY/QLOpi4w4zZ2qyuquSe6n0oZmeBE+1K0KVwL6R0UXSNOFuzFUQJBAOaaFemrjPS4G5nDetaWihRxGl/Ab4NP3UUjqQJT9r/va+irIzEGPDRm88Tv//0HUa9sU80VzVkxPv4z7P/G7S0CQQCumd3Gne6umP+IPE8Ei5wllJjr9AC+iwOrby6pZ+Yu/NYKq5IyBxEO/aYYLrIERkNeojmiQBS6U/EBN2j5BqU1AkAcngCcJ/rtH6/lLBohaTsngEW6nkH4dL/L5boCnPLhLNAiZqKOYey0wBX+WZ5WA0OC1pmruMnsCK19lGoT79bdAkBUnG3TxU8YPz59t7QXIMyvjX45wcnmjJlfp4Z2Q8pRdOcIzrIPGkuS9ypZR6+u0JnvLzA8x08EKI9/JmnxW94NAkAPm4I7W8a29ygYOjpQq9z2Mg5YbBshySrHdcqiZZ0t86Rsz4sdEW4VzMBk2iHtEFQAvzVoUCSEdNoikK5YsF2A";

    public static final String BASE_URL = "http://47.112.190.197/";

    //获取token 令牌
    public static final String TOKEN = "/token";

    //加入直播间接口
    public static final String JOIN_ROOM = "/customer/joinRoom";
    //退出直播间
    public static final String QUIT_ROOM = "/customer/quitRoom";

    //创建直播间
    public static final String CREATE_ROOM = "/management/room/create-room";
    //创建直播间
    public static final String UPDATE_ROOM = "/management/room/update-room";
    //开启直播间
    public static final String OPEN_LIVE_ROOM = "/management/room/open-room";
    //结束直播间
    public static final String FINISH_ROOM = "/management/room/finish-room";
    //查询直播间
    public static final String SEARCH_ROOM = "/management/room/search-room";

    //发表评论
    public static final String SEND_MESSAGE = "/management/comment";

    //点赞
    public static final String LIKE = "/like";

    //查询房间 sku get
    public static final String Inquire_SKU = "/management/sku";
    //添加sku post
    public static final String ADD_SKU = "/management/sku";
    //更改购物车
    public static final String UPDATA_CARTS = "/cart/update";
}
