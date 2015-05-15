package Server.Service;

import Model.*;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class Receptionist extends Service<User> {

    private ServerSocket s;
    private UserList userList;
    private ExecutorService service;
    private DataInput dataInput;

    public Receptionist(UserList u,ChatList chatList, DataInput dataInput, ExecutorService service) throws IOException {
        this.userList = u;
        this.service = service;
        this.dataInput = dataInput;
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
                            DataRetriever dataRetriever = new DataRetriever(serverUser.getIos(), serverUser, Receptionist.this.dataInput, Receptionist.this.service);
                            serverUser.setDataRetriever(dataRetriever);
                            dataRetriever.start();
                        }
                        u.addUser(th.getValue());
                        th.restart();
                    }


                }
        );
    }

    public void stop() {
        this.setOnSucceeded(e -> {
            return;
        });
    }

    @Override
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
