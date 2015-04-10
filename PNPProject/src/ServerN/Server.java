package ServerN;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class Server {

    private ExecutorService threads;
    private Server.ServerConfig sc;

    public Server(Server.ServerConfig sc) {
        this.sc = sc;
        this.threads = Executors.newCachedThreadPool();
    }

    public void start(int port) {
        ConnectionEstablisher cs
    }

}
