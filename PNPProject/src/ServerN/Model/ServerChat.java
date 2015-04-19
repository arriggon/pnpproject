package ServerN.Model;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * Created by RAIDER on 19.04.2015.
 */
public class ServerChat {
    private final String message;

    public ServerChat(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
