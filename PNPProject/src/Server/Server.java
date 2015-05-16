package Server;

import Model.*;
import Server.Service.DataManager;
import Server.Service.Receptionist;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class Server extends Task<Void>{
    //Service
    private UserList userList;
    private ChatList chatList;
    private DataInput dataInput;
    //Services
    private Receptionist receptionist;
    private DataManager dataManager;
    //Server only
    private ExecutorService service;
    private boolean recOnceStarted;
    private boolean dataManagerStatedOnce;

    public Server(UserList userList, ChatList chatList) throws IOException {

        service = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });
        this.userList = userList;
        this.chatList = chatList;

        dataInput = new DataInput();
        receptionist = new Receptionist(this.userList, this.chatList,this.dataInput
                , service, this);
        dataManager = new DataManager(dataInput, this.service, this.chatList, this.userList);
        recOnceStarted = false;
        dataManagerStatedOnce = false;
    }

    public void start() {
        service.submit(this);
    }

    @Override
    protected Void call() throws Exception {
        while(!isCancelled()) {
            if(!recOnceStarted) {
                receptionist.start();
                recOnceStarted=true;
            }
            if(!dataManagerStatedOnce) {
                dataManager.start();
                dataManagerStatedOnce = true;
            }

        }

        receptionist.stop();
        dataManager.stop();

        return null;
    }

    public void send(String str) {
        ChatUnit u = new ChatUnit("Server", str);
        dataManager.send(u);
    }

    public List<User> getCompleteUserList() {
        ArrayList<User> users = new ArrayList<>();
        Iterator<User> i = userList.getList().iterator();
        while (i.hasNext()) {
            users.add(new User(i.next().getUsername()));
        }
        return users;
    }
}
