package Clinet.Service;

import Model.ChatList;
import Model.ChatUnit;
import Model.DataOverNetwork;
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

    public DataRetriever(Socket socket, ChatList chatList, ObjectInputStream ios, ObjectOutputStream oos) {
        this.socket = socket;
        list = chatList;
        this.ios = ios;
        this.oos = oos;
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
            socket.close();
            ios.close();
            oos.close();
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

                return null;
            }
        };
    }
}
