package Clinet.Service;

import Model.ChatList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 * Created by Alexander on 12.05.2015.
 */
public class WhoOpensTheConnection extends Service<Socket> {

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
                dataRetriever = new DataRetriever(WhoOpensTheConnection.this.getValue(), chatList, new ObjectInputStream(WhoOpensTheConnection.this.getValue().getInputStream()), new ObjectOutputStream(WhoOpensTheConnection.this.getValue().getOutputStream()));
                dataRetriever.start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

    }



    @Override
    protected Task<Socket> createTask() {
        final String serverIp = this.serverIp;
        final int port = this.port;
        return new Task<Socket>() {
            @Override
            protected Socket call() throws Exception {
                ObjectInputStream ios = null;
                ObjectOutputStream oos = null;
                Socket s = null;
                try {
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
                }finally {
                    if(ios != null) {
                        ios.close();
                    }
                    if(oos != null) {
                        oos.close();
                    }
                }


                return s;
            }
        };
    }
}
