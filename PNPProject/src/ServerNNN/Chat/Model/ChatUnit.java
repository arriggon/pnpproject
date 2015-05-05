package ServerNNN.Chat.Model;

import ServerNNN.Model.Client;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class ChatUnit {
    private Client user;
    private String message;

    public ChatUnit(Client u, String m) {
        user = u;
        message = m;
    }

    public String getMessage() {
        return message;
    }

    public Client getUser() {
        return user;
    }

}
