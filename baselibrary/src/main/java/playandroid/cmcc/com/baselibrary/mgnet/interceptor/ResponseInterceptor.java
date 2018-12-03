package playandroid.cmcc.com.baselibrary.mgnet.interceptor;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class ResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Response response = chain.proceed(oldRequest);
        String content = handleResponse(response);
        //response string只能读一次，为了让日志拦截器正常使用，copy一份response传递
        MediaType contentType = response.body().contentType();
        if (contentType == null) {
            contentType = MediaType.parse("text/json");
        }
        return response.newBuilder().body(ResponseBody.create(contentType, content)).build();
    }


    private String handleResponse(Response response) {
        String content = "";
        try {
            content = response.body().string();
            JSONObject jsonObject = new JSONObject(content);
            if (jsonObject != null && jsonObject.has("code")) {
                if ("309".equals(jsonObject.get("code"))) {
                } else if ("310".equals(jsonObject.get("code"))) {
                } else if ("50002".equals(jsonObject.get("code"))) {
                } else {
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content;
    }

}
