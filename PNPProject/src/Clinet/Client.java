package Clinet;

import Clinet.Service.WhoOpensTheConnection;
import Model.ChatList;
import Model.Request.UserListCarrier;
import Model.User;
import Model.UserList;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import org.omg.CORBA.portable.ValueOutputStream;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Alexander on 12.05.2015.
 */
public class Client extends Task<Void> {


    private ExecutorService service;
    private ChatList chatList;
    private UserList userList;
    private String username, ipAddress;
    private int port;

    private WhoOpensTheConnection whoOpensTheConnection;


    private boolean whoIsRunningOnce;


    public Client(ChatList cL, UserList uL, String username, String ipAddress, int port) {

        service = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });

        chatList = cL;
        userList = uL;

        this.username = username;
        this.ipAddress = ipAddress;

        this.port = port;

        this.whoIsRunningOnce = false;
        this.whoOpensTheConnection = null;


    }

    public void start() {
        this.service.submit(this);
    }

    public void send(String string) {
        whoOpensTheConnection.send(string);
    }





    @Override
    protected Void call() throws Exception {
        if(!isCancelled()) {

            if(!whoIsRunningOnce) {
                whoOpensTheConnection = new WhoOpensTheConnection(username,ipAddress, port, chatList, this);
                whoOpensTheConnection.start();
            }

        }

        whoOpensTheConnection.stop();

        return null;
    }

    public void showConnectionError() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Could not Connect: Username already exist");
        a.show();
    }

    public void addAllUsers(UserListCarrier uca) {
        System.out.println("Writting users to GUI");
        Iterator<User> i = uca.listToAddOn.iterator();
        while (i.hasNext()) {
            User u = i.next();
            System.out.println(u.toString());
            userList.addUser(u);
        }
    }
}
