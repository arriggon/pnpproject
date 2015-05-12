package Clinet.Service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

import java.net.*;

/**
 * Created by Alexander on 12.05.2015.
 */
public class WhoOpensTheConnection extends Service<Socket> {

    private String username;
    private String serverIp;
    private int port;

    public WhoOpensTheConnection(String username, String serverIp, int port) {
        this.username = username;
        this.serverIp = serverIp;
        this.port = port;
    }



    @Override
    protected Task<Socket> createTask() {
        final String serverIp = this.serverIp;
        final int port = this.port;
        return new Task<Socket>() {
            @Override
            protected Socket call() throws Exception {
                Socket s = new Socket();
                s.setSoTimeout(100);
                s.connect(new InetSocketAddress(serverIp, port));
                return s;
            }
        };
    }
}
