package Clinet.Service;

import Model.ChatList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 * Created by Alexander on 12.05.2015.
 */
public class WhoOpensTheConnection extends Service<DataRetriever> {

    private String username;
    private String serverIp;
    private int port;
    private ChatList chatList;

    private DataRetriever dataRetriever;

    public WhoOpensTheConnection(String username, String serverIp, int port, ChatList chatList) {
        this.username = username;
        this.serverIp = serverIp;
        this.port = port;
        this.chatList = chatList;

        this.setOnSucceeded(e -> {
            try {
                DataRetriever dataRetriever1 = WhoOpensTheConnection.this.getValue();
                if(dataRetriever1 != null ) {
                    this.dataRetriever = dataRetriever1;
                    dataRetriever.start();
                }
            }finally {

            }

        });

    }



    @Override
    protected Task<DataRetriever> createTask() {
        final String serverIp = this.serverIp;
        final int port = this.port;
        return new Task<DataRetriever>() {
            @Override
            protected DataRetriever call() throws Exception {
                ObjectInputStream ios = null;
                ObjectOutputStream oos = null;
                Socket s = null;
                    s = new Socket();
                    s.setSoTimeout(100);
                    s.connect(new InetSocketAddress(serverIp, port));

                    ios = new ObjectInputStream(s.getInputStream());
                    oos = new ObjectOutputStream(s.getOutputStream());

                    Object o = ios.readObject();
                    if(String.class.isInstance(o)) {
                        String so = (String) o;
                        if(so.equalsIgnoreCase("200")) {
                            oos.writeObject(username);
                        } else {
                            throw new Exception("Connection failed");
                        }
                    }

                DataRetriever d = new DataRetriever(s, chatList, ios, oos);



                return  d;
            }
        };
    }
}
