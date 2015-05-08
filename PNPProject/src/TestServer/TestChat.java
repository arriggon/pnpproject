package TestServer;

import GUI.GameWindow;
import TestServer.Chatter;
import TestServer.ConnectionManagement;
import TestServer.InputWatcher;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alexander on 28.04.2015.
 */
public class TestChat implements Runnable {

    private GameWindow gm;

    public TestChat(GameWindow gm) {
        this.gm = gm;
    }

    public void main(int port) {
        ExecutorService str = Executors.newCachedThreadPool();
        ServerSocket s = null;
        try {
            ConnectionManagement cm = new ConnectionManagement();
            InputWatcher im = new InputWatcher(cm, gm);
            s = new ServerSocket(0);
            gm.appendChatText("Port: "+s.getLocalPort());





            while(true) {
                Socket sa = s.accept();
                ObjectInputStream ios = new ObjectInputStream(sa.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(sa.getOutputStream());
                Chatter c = new Chatter(sa, ios, oos, cm);
                cm.addConnection(c);
                str.submit(c);

            }




        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public GameWindow getGm() {return gm;}

    @Override
    public void run() {
        ExecutorService str = Executors.newCachedThreadPool();
        ServerSocket s = null;
        try {
            ConnectionManagement cm = new ConnectionManagement();
            InputWatcher im = new InputWatcher(cm, gm);
            s = new ServerSocket(0);
            gm.appendChatText("Port: "+s.getLocalPort());





            while(true) {
                Socket sa = s.accept();
                ObjectInputStream ios = new ObjectInputStream(sa.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(sa.getOutputStream());
                Chatter c = new Chatter(sa, ios, oos, cm);
                cm.addConnection(c);
                str.submit(c);

            }




        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
