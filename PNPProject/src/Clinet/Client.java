package Clinet;

import Model.ChatList;
import Model.UserList;
import javafx.concurrent.Task;
import org.omg.CORBA.portable.ValueOutputStream;

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



    public Client(ChatList cL, UserList uL, String username, String ipAddress) {

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




    }





    @Override
    protected Void call() throws Exception {


        return null;
    }
}
