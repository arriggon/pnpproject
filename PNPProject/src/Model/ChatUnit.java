package Model;

import java.io.Serializable;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class ChatUnit implements Serializable {

    private String username;
    private String message;

    public ChatUnit(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getU() {
        return username;
    }

    public void setU(String u) {
        this.username = u;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "["+username+"]: "+message;
    }
}
