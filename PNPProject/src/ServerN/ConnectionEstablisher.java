package ServerN;

import ServerN.Model.User;
import ServerN.Model.UserInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class ConnectionEstablisher implements Runnable {

    private ServerSocket listener;
    private ConnectionManager cm;
    private Server s;

    public ConnectionEstablisher(int port, ConnectionManager cm, Server s) throws IOException {
        listener = new ServerSocket(port);
        this.cm = cm;
    }

    @Override
    public void run() {
        try {

            Socket s = listener.accept();
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ios = new ObjectInputStream(s.getInputStream());

            oos.writeObject(new ServerCommandWrapper(ServerCommand.CLIENT_SEND_INFO));
            UserInfo usi = (UserInfo) ios.readObject();
            User u = new User(s, usi);

            cm.addUser(u);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
