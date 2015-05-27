package Server;

import Model.*;
import Model.Character.*;
import Model.Character.Character;
import Model.Request.DisconnectNotification;
import Model.Request.UserRemovalNotification;
import Server.Service.DataManager;
import Server.Service.Receptionist;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.*;
import java.util.stream.Collector;
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

    public void userDisconnected(User u) {
        if(u instanceof ServerUser) {
            ((ServerUser) u).getDataRetriever().stop();
            try {
                ((ServerUser) u).getOos().close();
                ((ServerUser) u).getS().close();
                ((ServerUser) u).getIos().close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<User> users = new ArrayList<>();
            users.add(new User(u.getUsername()));
            userList.getList().stream().filter(new Predicate<User>() {
                @Override
                public boolean test(User user) {
                    return !(user.getUsername() == u.getUsername());
                }
            }).forEach(e -> {
                if(e instanceof ServerUser) {
                    ServerUser su = (ServerUser) e;
                    try {
                        su.getOos().writeObject(new UserRemovalNotification(users));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
             });

            this.userList.removeUser(u);
        }
    }

    public Model.Character.Character getCharacterFromUser(String username) {
        Iterator<User> i = userList.getList().iterator();
        while (i.hasNext()) {
            Object o = i.next();
            if(o instanceof ServerUser) {
                ServerUser su = (ServerUser)o;
                if(su.getUsername().equals(username)) {
                    return su.getCharacter();
                }
            }
        }
        return null;
    }

    public void disconnectUser(ServerUser su) {

        try {
            su.getOos().writeObject(new DisconnectNotification());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnectAllUsers() {
        this.userList.getList().stream().filter(e -> e instanceof ServerUser).forEach(e -> {
            if (e instanceof ServerUser) {
                ServerUser su = (ServerUser)e;
                this.disconnectUser(su);
            }
        });
    }
}
