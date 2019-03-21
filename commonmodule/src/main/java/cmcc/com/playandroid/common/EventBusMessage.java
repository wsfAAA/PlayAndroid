package cmcc.com.playandroid.common;

/**
 * Created by wsf on 2019/3/21.
 */

public class EventBusMessage {

    public final static String EVENT_LOGIN_SUCCEED="EVENT_LOGIN_SUCCEED";
    public final static String EVENT_LOGIN_FIALUER="EVENT_LOGIN_FIALUER";

    private String type;
    private String message;

    public EventBusMessage(String type) {
        this.type = type;
    }

    public EventBusMessage(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
