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



    public Client(ChatList cL, UserList uL) {

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





    }





    @Override
    protected Void call() throws Exception {

    }
}
