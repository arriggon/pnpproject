package Clinet.Service;

import Clinet.Client;
import Model.ChatList;
import Model.Request.UserListRequest;
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
    private Client c;

    private DataRetriever dataRetriever;

    public WhoOpensTheConnection(String username, String serverIp, int port, ChatList chatList, Client c) {
        this.username = username;
        this.serverIp = serverIp;
        this.port = port;
        this.chatList = chatList;
        this.c = c;

        this.setOnSucceeded(e -> {
            try {
                System.out.println("Activating dataRetriever");
                DataRetriever dataRetriever1 = WhoOpensTheConnection.this.getValue();
                if(dataRetriever1 != null ) {
                    System.out.println("Dataretriever nor null");
                    this.dataRetriever = dataRetriever1;
                    dataRetriever.start();

                } else {
                    this.c.cancel();
                    this.c.showConnectionError();
                }
            }finally {

            }

        });

    }

    public void send(String str) {
        dataRetriever.send(str);
    }

    public void stop() {
        this.setOnSucceeded(e -> {
            return;
        });

        if(dataRetriever != null) {
            dataRetriever.stop();
        }

    }




    @Override
    protected Task<DataRetriever> createTask() {
        final String serverIp = this.serverIp;
        final int port = this.port;
        final Client c = this.c;
        final ChatList chatList = this.chatList;
        return new Task<DataRetriever>() {
            @Override
            protected DataRetriever call() throws Exception {
                ObjectInputStream ios = null;
                ObjectOutputStream oos = null;
                Socket s = null;
                    s = new Socket();
                    s.setSoTimeout(10000);
                    s.connect(new InetSocketAddress(serverIp, port));

                    ios = new ObjectInputStream(s.getInputStream());
                    oos = new ObjectOutputStream(s.getOutputStream());

                    Object o = ios.readObject();
                    if(String.class.isInstance(o)) {
                        String so = (String) o;
                        if(so.equalsIgnoreCase("200")) {
                            oos.writeObject(username);
                            o = ios.readObject();
                            if(o instanceof String) {
                                so = (String) o;
                                if(so.equalsIgnoreCase("400")){
                                    return null;
                                }
                            }
                        } else {
                            throw new Exception("Connection failed");
                        }
                    }
                System.out.println("Sending Request");
                oos.writeObject(new UserListRequest());
                System.out.println("Sent reques");

                DataRetriever d = new DataRetriever(s, chatList, ios, oos, c);



                return  d;
            }
        };
    }
}
