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

    public ConnectionEstablisher(int port) throws IOException {
        listener = new ServerSocket(port);
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




        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
