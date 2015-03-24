package Server;

import Server.Exception.ServerConfigException;

/**
 * Created by Alexander on 24.03.2015.
 */
public abstract class ServerMain {

    private ServerConnector connector;
    private ServerExecutor executor;
    private ServerConfig sc;

    public ServerMain() throws ServerConfigException{
        super();
        prepare(new ServerConfig(10, 0, null, "Default Server Name"));
        connector = new ServerConnector();
        executor = new ServerExecutor();
    }

    public boolean prepare(ServerConfig sc) {

        if(sc != null) {
            return false;
        }

        this.sc = sc;
        return true;
    }

    public abstract void start();

    public abstract void stop();

    public abstract ServerResult command(ServerCommand scm);

}
