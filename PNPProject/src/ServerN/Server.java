package ServerN;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class Server {

    private ExecutorService threads;
    private Server.ServerConfig sc;
    private ConnectionManager cm;
    private ConnectionEstablisher ce;
    private Future fCm;

    public Server(Server.ServerConfig sc) {
        this.sc = sc;
        this.threads = Executors.newCachedThreadPool();
    }

    public void start(int port) {
        ConnectionManager cm = new ConnectionManager();
        try {
            ConnectionEstablisher cs = new ConnectionEstablisher(3340, cm, this);
            this.fCm = threads.submit(cs);
            this.ce = cs;
            this.cm = cm;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void stop() {
        while (true) {
            if(this.ce.isReady()) {
                fCm.cancel(false);
            }
        }

        //Load saves into file once model is finished

    }

}
