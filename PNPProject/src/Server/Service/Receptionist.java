package Server.Service;

import Model.*;
import Model.Request.UserListCarrier;
import Model.Request.UserListRequest;
import Server.Server;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

/**
 * This Class represents the service that is used to initiate the connection for every user that logs onto the server.
 *
 * Created by RAIDER on 08.05.2015.
 */
public class Receptionist extends Service<User> {

    /**
     * Socket the service listenes too
     */
    private ServerSocket s;
    /**
     * Data Model for Users
     */
    private UserList userList;
    /**
     * Thread Pool the service runs in
     */
    private ExecutorService service;
    /**
     * Data Model for forwading data
     */
    private DataInput dataInput;
    /**
     * Server this service belongs too
     */
    private Server server;

    /**
     * Initiates the Receptionis
     * @param u Datamodel for users
     * @param chatList Datamodel for Chat Units
     * @param dataInput Data Model for Data
     * @param service Service the service will be running in
     * @param server Server the service belongs too
     * @throws IOException
     */
    public Receptionist(UserList u,ChatList chatList, DataInput dataInput, ExecutorService service, Server server) throws IOException {
        this.userList = u;
        this.service = service;
        this.dataInput = dataInput;
        this.server = server;
        this.setExecutor(
                service
        );
        this.s = new ServerSocket(0);
        this.s.setSoTimeout(1500);
        User serveru = new User("Server");
        chatList.addChatUnit(new ChatUnit(serveru.getUsername(), "Port: " + s.getLocalPort()));
        chatList.addChatUnit(new ChatUnit(serveru.getUsername(), "Server Address: "+InetAddress.getLocalHost().toString()));
        Receptionist th = this;

        this.setOnSucceeded(
                e ->  {
                    User user = th.getValue();
                    if (user == null) {
                        th.restart();
                    } else {
                        if(ServerUser.class.isInstance(user)) {
                            ServerUser serverUser = (ServerUser) user;
                            try {
                                serverUser.getS().setSoTimeout(100);
                            } catch (SocketException e1) {
                                e1.printStackTrace();
                            }
                            DataRetriever dataRetriever = new DataRetriever(serverUser.getIos(), serverUser, Receptionist.this.dataInput, Receptionist.this.service, Receptionist.this.server);
                            serverUser.setDataRetriever(dataRetriever);
                            dataRetriever.start();
                        }
                        ArrayList<User> usersToTransfer = new ArrayList<User>();
                        usersToTransfer.add(new User(user.getUsername()));
                        userList.getList().stream().filter(new Predicate<User>() {
                            @Override
                            public boolean test(User user1) {
                                return !(user1.getUsername() == user.getUsername());
                            }
                        }).forEach(e12 -> {
                            if(e12 instanceof ServerUser) {
                                ServerUser su = (ServerUser) e12;
                                try {
                                    su.getOos().writeObject(new UserListCarrier(usersToTransfer));
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                        u.addUser(th.getValue());
                        th.restart();
                    }


                }
        );
    }

    /**
     * Stops the receptionist
     */
    public void stop() {
        this.setOnSucceeded(e -> {
            return;
        });
    }

    @Override
    /**
     * Main method for the Service. THis method is invoked by the service and connects the clients that log onto the server.
     */
    protected Task<User> createTask() {
        final ServerSocket s = this.s;
        final UserList userList = this.userList;
        return new Task<User>() {
            @Override
            protected User call() throws Exception {
                String u = null;

                    try {
                        Socket sa = s.accept();
                        //Get User
                        ObjectOutputStream oos = new ObjectOutputStream(sa.getOutputStream());
                        ObjectInputStream ios = new ObjectInputStream(sa.getInputStream());

                        //User(Object)

                        oos.writeObject("200");
                        u = (String) ios.readObject();
                        final String uS = u;
                        boolean isThere = userList.getList().stream().anyMatch(new Predicate<User>() {
                            @Override
                            public boolean test(User user) {
                                return user.getUsername().equals(uS);
                            }
                        });
                        if(isThere) {
                            oos.writeObject("400");
                            return null;
                        } else {
                            oos.writeObject("401");
                        }

                        return new ServerUser(u, sa, ios, oos);
                    }catch (SocketTimeoutException e) {

                    }




                return null;
            }
        };
    }
}
