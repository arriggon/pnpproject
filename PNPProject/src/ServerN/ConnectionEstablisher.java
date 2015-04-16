package ServerN;

import ServerN.Model.User;
import ServerN.Model.UserInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class ConnectionEstablisher implements Runnable {

    private ServerSocket listener;
    private ConnectionManager cm;
    private AtomicBoolean ready;
    private Server s;

    public ConnectionEstablisher(int port, ConnectionManager cm, Server s) throws IOException {
        listener = new ServerSocket(port);
        this.cm = cm;
        this.ready = new AtomicBoolean();
    }

    public boolean isReady() {
        return ready.get();
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.ready.set(true);
                Socket s = listener.accept();
                this.ready.set(false);
                establishConnection(s);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void establishConnection(Socket s) throws Exception {
        ObjectOutputStream oos = null;
        ObjectInputStream ios = null;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            ios = new ObjectInputStream(s.getInputStream());

            oos.writeObject(new ServerCommandWrapper(ServerCommand.CLIENT_SEND_INFO));
            UserInfo usi = (UserInfo) ios.readObject();
            User u = new User(s, usi);


            cm.addUser(u);

        } finally {
            if(oos != null) {
                oos.close();
            }

            if(ios != null) {
                ios.close();
            }

        }

    }

}
