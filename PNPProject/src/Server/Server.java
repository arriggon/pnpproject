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
 *
 * MAin Server class that directs all server-related tasks and invokes all other Server Services
 *
 */
public class Server extends Task<Void>{
    //Service
    /**
     * Datamodel for Users
     */
    private UserList userList;
    /**
     * Datamodel for All Chat Messages (Chat Units)
     */
    private ChatList chatList;
    /**
     * Buffer for ChatList
     */
    private DataInput dataInput;
    //Services
    /**
     * Cotainer for connection initialization thread
     */
    private Receptionist receptionist;
    /**
     * Container for Data managing thread
     */
    private DataManager dataManager;
    //Server only
    /**
     * Executors service responsible for managing all threads used by the server
     */
    private ExecutorService service;

    //Flags
    /**
     * Starting flag for the receptionist thread
     */
    private boolean recOnceStarted;
    /**
     * Starting flag for the data management thread
     */
    private boolean dataManagerStatedOnce;

    /**
     * Initializes the server and hands over all the neccessary data models
     * @param userList reference for the data model for users
     * @param chatList Reference for the data model for the chat messages
     * @throws IOException
     */
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

    /**
     * Starts the server
     */
    public void start() {
        service.submit(this);
    }

    @Override
    /**
     * Method executed when the main server thread is online
     */
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

    /**
     * Is invoked by the GUI to hand over the message typed by the server admin and forwards it to Data Managment
     * @param str String handed over by the GUI to send
     */
    public void send(String str) {
        ChatUnit u = new ChatUnit("Server", str);
        dataManager.send(u);
    }

    /**
     * Generates a List containig all Users logged in on the server in an Abstract form for further Use
     * @return A list that can be sent over the network containing all Users of the Server in an abstracted form
     */
    public List<User> getCompleteUserList() {
        ArrayList<User> users = new ArrayList<>();
        Iterator<User> i = userList.getList().iterator();
        while (i.hasNext()) {
            users.add(new User(i.next().getUsername()));
        }
        return users;
    }

    /**
     * Invoked when one of the user disconnects. Is used to free resources.
     * @param u User that disconnects
     */
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

    /**
     * Retrieves the Character from the Data Model and returns it
     * @param username Username of the user the character is requested for
     * @return Character from the requested user
     */
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

    /**
     * Called when the server disconnects a User
     * @param su Server user to be disconnected
     */
    public void disconnectUser(ServerUser su) {

        try {
            su.getOos().writeObject(new DisconnectNotification());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Called when the server shuts down, to disconnect all the currently active users on the server
     */
    public void disconnectAllUsers() {
        this.userList.getList().stream().filter(e -> e instanceof ServerUser).forEach(e -> {
            if (e instanceof ServerUser) {
                ServerUser su = (ServerUser)e;
                this.disconnectUser(su);
            }
        });
    }
}
