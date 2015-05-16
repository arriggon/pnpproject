package Clinet.Service;

import Clinet.Client;
import Model.ChatList;
import Model.ChatUnit;
import Model.DataOverNetwork;
import Model.Request.DisconnectNotification;
import Model.Request.GetIpCarrier;
import Model.Request.GetIpRequest;
import Model.Request.UserListCarrier;
import Model.UserList;
import Server.Server;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;

/**
 * Created by Alexander on 12.05.2015.
 */
public class DataRetriever extends Service<DataOverNetwork> {

    private Socket socket;
    private ChatList list;
    private ObjectInputStream ios;
    private ObjectOutputStream oos;
    private Client c;

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

    public void send(String str) {
        try {
            oos.writeObject(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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

    public void requestDisconnect() {
        try {
            System.out.println("Disconnecting");
            oos.writeObject(new DisconnectNotification());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
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

                return null;
            }
        };
    }

    public void requestIpFromServer() {
        System.out.print("Request final");
        try {
            oos.writeObject(new GetIpRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
