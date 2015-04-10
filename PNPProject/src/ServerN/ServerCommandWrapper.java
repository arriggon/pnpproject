package ServerN;

import java.io.Serializable;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class ServerCommandWrapper implements Serializable{
    private ServerCommand command;

    public ServerCommandWrapper(ServerCommand command) {
        this.command = command;
    }
}
