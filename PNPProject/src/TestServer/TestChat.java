package TestServer;

import GUI.GameWindow;
import GUI.Launcher;
import TestServer.Chatter;
import TestServer.ConnectionManagement;
import TestServer.InputWatcher;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Alexander on 28.04.2015.
 */
public class TestChat implements Runnable {

    private GameWindow gm;

    public TestChat(GameWindow gm) {
        this.gm = gm;
    }

    public void main(int port) {
        ServerSocket s = null;
        try {
            ConnectionManagement cm = new ConnectionManagement(gm);
            InputWatcher im = new InputWatcher(cm, gm);
            s = new ServerSocket(0);
            s.setSoTimeout(1000);
            gm.appendChatText("Port: "+s.getLocalPort());





            while(true) {
                try {
                    Socket sa = s.accept();
                    ObjectInputStream ios = new ObjectInputStream(sa.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(sa.getOutputStream());
                    Chatter c = new Chatter(sa, ios, oos, cm);
                    cm.addConnection(c);

                }catch(InterruptedIOException e) {
                }
                if(Launcher.serverF.isCancelled()) {
                    cm.stopAll();
                }

            }




        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public GameWindow getGm() {return gm;}


    @Override
    public void run() {
        ServerSocket s = null;
        try {
            ConnectionManagement cm = new ConnectionManagement(gm);
            InputWatcher im = new InputWatcher(cm, gm);
            s = new ServerSocket(0);
            s.setSoTimeout(1000);
            gm.getObserver().addMessage("Port: "+s.getLocalPort());
            //gm.appendText("Port: "+s.getLocalPort()); //Hier gab es immer eine Illegal state exception
            gm.setIn(im);





            while(true) {
                try {
                    Socket sa = s.accept();
                    ObjectInputStream ios = new ObjectInputStream(sa.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(sa.getOutputStream());
                    Chatter c = new Chatter(sa, ios, oos, cm);      //Chatter
                    cm.addConnection(c);
                }catch(InterruptedIOException e) {
                }


            }




        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
