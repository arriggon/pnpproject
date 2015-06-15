package Clinet;

import Clinet.Service.WhoOpensTheConnection;
import GUI.CharEdit.CharEdit;
import Model.Character.*;
import Model.Character.Character;
import Model.ChatList;
import Model.Request.GetIpCarrier;
import Model.Request.UserListCarrier;
import Model.Request.UserRemovalNotification;
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
 * This class represents a client and is the main thread for every client
 */
public class Client extends Task<Void> {

    /**
     * Is the Thread Pool for all client-related processes
     */
    private ExecutorService service;
    /**
     * DataModel for chat Messages
     */
    private ChatList chatList;
    /**
     * Data Model for users connected to the server
     */
    private UserList userList;
    /**
     * Credentials needed for logging onto the server
     */
    private String username, ipAddress;

    /**
     * Port to connect with the server
     */
    private int port;

    /**
     * Container for connection-establishing service
     */
    private WhoOpensTheConnection whoOpensTheConnection;

    /**
     * Starting flag for the connection-establishig service
     */
    private boolean whoIsRunningOnce;

    /**
     * Initializes the Client
     * @param cL Data model for Chat messages
     * @param uL Data Model for Users
     * @param username Username of the client
     * @param ipAddress ip address of the server
     * @param port Port the server listenes too
     */
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

    /**
     * Starts the client
     */
    public void start() {
        this.service.submit(this);
    }

    /**
     * Invoked to send a chat message to the server
     * @param string The message to be sent
     */
    public void send(String string) {
        whoOpensTheConnection.send(string);
    }

    /**
     * Is invoked to notify the server about the disconnection of the client.
     */
    public void requestDisconnect() {
        whoOpensTheConnection.requestDisconnect();
    }



    @Override
    /**
     * Is the main method of the client thread. Is used to start and to stop the services working for the client.
     */
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

    /**
     * Invoked if an error happened when the connection fails. (Default: Username is already taken)
     */
    public void showConnectionError() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Could not Connect: Username already exist");
        a.show();
    }


    /**
     * This method is invoked while a connection to the server is established to populate the User data model.
     * @param uca The data used to populate the User list
     */
     public void addAllUsers(UserListCarrier uca) {
        System.out.println("Writting users to GUI");
        Iterator<User> i = uca.listToAddOn.iterator();
        while (i.hasNext()) {
            User u = i.next();
            System.out.println(u.toString());
            userList.addUser(u);
        }
    }

    /**
     * Invoked to request the client IP from the server
     */
    public void requestIpFromServer() {
        System.out.print("Reuest ip from Client 2");
        whoOpensTheConnection.requestIpFromServer();
    }

    /**
     * Invoked when the answer for the IPRequest from the server has been received.
     * @param gipca the Answer from the server
     */
    public void showIpRequested(GetIpCarrier gipca) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("The IP of "+gipca.username+": "+gipca.ipAddress);
        a.show();
    }

    /**
     * Is Invoked when the Server notifies the Client that users have logged off and have to be removed from the Data Model
     * @param userRemovalNotification Container containing the Users that have to be removed
     */
    public void removeUsersFromUserList(UserRemovalNotification userRemovalNotification) {
        userRemovalNotification.usersToRemove.stream().forEach(e -> {
            userList.removeUser(e);
        });
    }

    /**
     * Invoked by the client to request the Character from a specific user
     * @param username user from who the character is requested
     */
    public void requestCharacter(String username) {
        whoOpensTheConnection.requestCharacter(username);
    }

    /**
     * Invoked when the answer from the server is recieved to display the requested character
     * @param c Character to be displayed
     */
    public void showCharacter(Model.Character.Character c) {

        CharEdit ce = new CharEdit();
        if(c != null) {
            ce.getEditControlls().fillCharEdit(c);
        }



    }

    /**
     * Is invoked when the character has been changed
     * @param c new Character
     */
    public void sendCharacterChangeNotification(Character c) {
        whoOpensTheConnection.sendCharacterChangeNotification(c);
    }
}
