package ServerN;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class Server {

    private ExecutorService threads;
    private Server.ServerConfig sc;
    private ConnectionManager cm;

    public Server(Server.ServerConfig sc) {
        this.sc = sc;
        this.threads = Executors.newCachedThreadPool();
    }

    public void start(int port) {
        ConnectionManager cm = new ConnectionManager();
        try {
            ConnectionEstablisher cs = new ConnectionEstablisher(3340, cm, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
