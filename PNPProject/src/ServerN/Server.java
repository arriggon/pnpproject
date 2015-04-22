package ServerN;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import Server.ServerConfig;
import ServerN.Connection.ChatReader;
import ServerN.Connection.ChatReaderManager;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class Server {

    private ExecutorService threads;
    private ServerConfig sc;
    private ConnectionManager cm;
    private ConnectionEstablisher ce;
    private Future fCm;
    private ChatReaderManager chatReaderManager;

    public Server(ServerConfig sc) {
        this.sc = sc;
        this.threads = Executors.newCachedThreadPool();
    }

    public void start(int port) {
        ConnectionManager cm = new ConnectionManager();
        this.chatReaderManager = new ChatReaderManager(threads);
        try {
            ConnectionEstablisher cs = new ConnectionEstablisher(3340, cm,this.chatReaderManager);
            this.fCm = threads.submit(cs);
            this.ce = cs;
            this.cm = cm;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void stop() {
        fCm.cancel(false);



        //Load saves into file once model is finished

    }

}
