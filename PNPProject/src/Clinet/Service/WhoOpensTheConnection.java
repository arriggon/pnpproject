package Clinet.Service;

import Clinet.Client;
import Model.Character.*;
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
 * This service creates the connection
 * Created by Alexander on 12.05.2015.
 */
public class WhoOpensTheConnection extends Service<DataRetriever> {

    /**
     * Username of the client
     */
    private String username;
    /**
     * Ip of the server
     */
    private String serverIp;
    /**
     * Port of the server that is used for connection
     */
    private int port;
    /**
     * The datamodel behind chat messages
     */
    private ChatList chatList;
    /**
     * Client the service belongs too.
     */
    private Client c;

    /**
     * Service for retriving data
     */
    private DataRetriever dataRetriever;

    /**
     * Initializes the service
     * @param username Username of the client
     * @param serverIp Ip of the server
     * @param port Port of the server
     * @param chatList Data model for chat messages
     * @param c Client the service belongs too
     */
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

    /**
     * Send a string to the server
     * @param str
     */
    public void send(String str) {
        dataRetriever.send(str);
    }

    /**
     * Stop the service
     */
    public void stop() {
        this.setOnSucceeded(e -> {
            return;
        });

        if(dataRetriever != null) {
            dataRetriever.stop();
        }

    }

    /**
     * Request the client ip from the server
     */
    public void requestIpFromServer() {
        System.out.print("Request IP 3");
        if(dataRetriever != null) {
            dataRetriever.requestIpFromServer();
        }
    }

    /**
     * Notify the Server from the upcoming disconnect
     */
    public void requestDisconnect() {
        if(dataRetriever != null) {
            dataRetriever.requestDisconnect();
        }
    }




    @Override
    /**
     * Main logic behind the service that connects the client with the server
     */
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

    /**
     * Request the character from a specific user from the server
     * @param username user the character is requested from
     */
    public void requestCharacter(String username) {
        if(dataRetriever != null) {
            dataRetriever.requestCharacter(username);
        }
    }

    /**
     * Is sent if the character of client has been changed
     * @param c new Character of the client
     */
    public void sendCharacterChangeNotification(Model.Character.Character c) {
        if(dataRetriever != null) {
            dataRetriever.sendCharacterChangeNotification(c);
        }
    }
}
