package Model;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class ChatUnit {

    private User u;
    private String message;

    public ChatUnit(User u, String message) {
        this.u = u;
        this.message = message;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "["+u.getUsername()+"]: "+message;
    }
}
