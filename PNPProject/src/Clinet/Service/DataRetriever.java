package Clinet.Service;

import Clinet.Client;
import Model.Character.*;
import Model.Character.Character;
import Model.ChatList;
import Model.ChatUnit;
import Model.DataOverNetwork;
import Model.Request.*;
import Model.UserList;
import Server.Server;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;

/**
 * This service monitors the connection to the server and handles incoming data
 * Created by Alexander on 12.05.2015.
 */
public class DataRetriever extends Service<DataOverNetwork> {

    /**
     * Connection to the server
     */
    private Socket socket;
    /**
     * Data Model for chat messages
     */
    private ChatList list;
    /**
     * Input Stream to be monitored
     */
    private ObjectInputStream ios;
    /**
     * Output Stream used to send data
     */
    private ObjectOutputStream oos;
    /**
     * Client this service belong to
     */
    private Client c;

    /**
     * Initilaized the Service
     * @param socket Connection to the service
     * @param chatList Data Model behind chat messages
     * @param ios Input stream to be monitored
     * @param oos Output Stream used for sending data
     * @param c Client the serive belongs too
     */
    public DataRetriever(Socket socket, ChatList chatList, ObjectInputStream ios, ObjectOutputStream oos, Client c) {
        this.socket = socket;
        list = chatList;
        this.ios = ios;
        this.oos = oos;
        this.c = c;
        try {
            socket.setSoTimeout(100);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.setOnSucceeded(e -> {
            Object o = DataRetriever.this.getValue();
            if(ChatUnit.class.isInstance(o)) {
                ChatUnit u = (ChatUnit) o;
                list.addChatUnit(u);
            } else if(o instanceof UserListCarrier) {
                UserListCarrier uca = (UserListCarrier) o;
                c.addAllUsers(uca);

            } else if(o instanceof GetIpCarrier) {
                GetIpCarrier gipca = (GetIpCarrier) o;
                this.c.showIpRequested(gipca);
            } else if(o instanceof UserRemovalNotification) {
                UserRemovalNotification urnf = (UserRemovalNotification) o;
                this.c.removeUsersFromUserList(urnf);
            } else if(o instanceof CharacterDisplayCarrier) {
                CharacterDisplayCarrier cid = (CharacterDisplayCarrier) o;
                this.c.showCharacter(cid.character);
            } else if(o instanceof DisconnectNotification) {
                this.c.requestDisconnect();
                this.c.cancel();
                Platform.exit();
                System.exit(0);
            }
            DataRetriever.this.restart();
        });

        this.setOnFailed(e -> {
            Throwable t = DataRetriever.this.getException();
            if(SocketTimeoutException.class.isInstance(t)) {
                DataRetriever.this.restart();
            }
        });
    }

    /**
     * Send data over the Output stream
     * @param str String to be sent
     */
    public void send(String str) {
        try {
            oos.writeObject(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Stop the service
     */
    public void stop() {
        this.setOnSucceeded(e -> {
            return;
        });

        this.setOnFailed( e -> {
            return;
        });

        try {
            System.out.println("Disconnecting");
            oos.writeObject(new DisconnectNotification());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
            ios.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Invoked to notify the server about the upcoming disconnection
     */
    public void requestDisconnect() {
        try {
            System.out.println("Disconnecting");
            oos.writeObject(new DisconnectNotification());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Main Logic behind the server. Is used to retrieve the data and handle it
     */
    protected Task<DataOverNetwork> createTask() {

        final ObjectInputStream ios = this.ios;

        return new Task<DataOverNetwork>() {
            @Override
            protected DataOverNetwork call() throws Exception {
                Object o = ios.readObject();
                if(ChatUnit.class.isInstance(o)) {
                    return (ChatUnit)o;
                }

                if(o instanceof UserListCarrier) {
                    return (UserListCarrier) o;
                }

                if(o instanceof GetIpCarrier) {
                    return (GetIpCarrier)o;
                }

                if(o instanceof UserRemovalNotification)
                    return  (UserRemovalNotification)o;

                if(o instanceof CharacterDisplayCarrier) {
                    return (CharacterDisplayCarrier)o;
                }

                if(o instanceof DisconnectNotification) {
                    return (DisconnectNotification)o;
                }
                return null;
            }
        };
    }

    /**
     * Invoked to request the ip of the client from the server
     */
    public void requestIpFromServer() {
        System.out.print("Request final");
        try {
            oos.writeObject(new GetIpRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Is used to request the Character from a specific user from the server
     * @param username user the character is requested from
     */
    public void requestCharacter(String username) {
        try {
            oos.writeObject(new CharacterRequest(username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoked when the Character of the client is changed to notify the server
     * @param c new Character
     */
    public void sendCharacterChangeNotification(Character c) {
        try {
            oos.writeObject(new CharacterChangeNotification(c));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
