package TestServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Alexander on 28.04.2015.
 */
public class Chatter implements Runnable {

    private Socket s;
    private ObjectInputStream ios;
    private ObjectOutputStream oos;
    private ConnectionManagement com;

    public Chatter(Socket s, ObjectInputStream ios, ObjectOutputStream oos, ConnectionManagement com) {
        this.s = s;
        this.ios = ios;
        this.oos = oos;
        this.com = com;
    }

    @Override
    public void run() {
        Object  o = null;
        try {
        	while(true) {
        		o = ios.readObject();
                com.sendAll(o.toString(), this);
        	}
            
        } catch (IOException e) {
        	com.removeConnection(this);
        } catch (ClassNotFoundException e) {
        	com.removeConnection(this);
        }

    }

    public void send(String s) {
        try {
            this.oos.writeObject(s);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
