package ServerNNN;

import ServerNNN.Model.Client;
import ServerNNN.Model.Connection;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Alexander on 27.04.2015.
 */
public class ConnectionEstablished implements Runnable {

    private Server s;

    public ConnectionEstablished(Server s) {
        this.s = s;


    }

    @Override
    public void run() {
        try {
            ServerSocket s = new ServerSocket(80);
            while(true) {
                Socket sa = s.accept();
                ObjectInputStream ios = new ObjectInputStream(sa.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(sa.getOutputStream());

                oos.writeObject(Commands.SendPassword);
                oos.flush();
                String str = (String) ios.readObject();

                if(!str.equals(this.s.getPassword())) {
                    oos.writeObject(Commands.ConnectionDenied);
                    oos.flush();
                    sa.close();
                }

                oos.writeObject(Commands.SendClient);
                oos.flush();

                Client c = (Client) ios.readObject();
                Connection connection = new Connection(ios, oos);
                boolean suc = this.s.addConnection(c, connection);
                if(!suc) {
                    oos.writeObject(Commands.ConnectionDeniedBy("Client Dupplication Error"));
                }



            }
        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Exception occured");
            a.setHeaderText("Opps, an Exception occured");
            a.setContentText(e.getMessage());
            a.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Exception occured");
            a.setHeaderText("Opps, an Exception occured");
            a.setContentText(e.getMessage());
            a.showAndWait();
        }

    }
}

