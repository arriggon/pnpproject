package Server;

import Server.Exception.ServerConfigException;

/**
 * Created by Alexander on 24.03.2015.
 */
public class ServerConfig {
    public final int playerMax;
    public final int bwLimit;
    public final String pwString;
    public final String name;

    public ServerConfig(int playerMax, int bwLimit, String pwString, String name) throws ServerConfigException{
        if(playerMax <= 0) {
            throw new ServerConfigException("playerMax: Value must be greater than 0");
        }
        if (bwLimit <= 0) {
            throw new ServerConfigException("bwLimit: Value must be greater than 0");
        }
        if(name == null) {
            throw new ServerConfigException("name: Null Reference given");
        }
        if(name.length() < 3) {
            throw new ServerConfigException("name: Name too short: 3 characters required");
        }
        this.playerMax = playerMax;
        this.bwLimit = bwLimit;
        this.pwString = pwString;
        this.name = name;
    }
}
