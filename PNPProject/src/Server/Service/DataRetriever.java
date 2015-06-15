package Server.Service;

import Model.*;
import Model.Character.*;
import Model.Request.*;
import Server.Server;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the Object that is used to monitor the client and to process data from the Client
 * Created by RAIDER on 09.05.2015.
 */
public class DataRetriever extends Service<DataOverNetwork> {

    /**
     * Stream used for Monitoring incoming data
     */
    private ObjectInputStream ios;
    /**
     * User the DataRetriever belongs to
     */
    private User u;
    /**
     * Data Model used for forwarding chat messages
     */
    private DataInput dataInput;
    /**
     * Service the service is running in
     */
    private ExecutorService service;
    /**
     * Reference to the server this service belongs to
     */
    private Server server;

    /**
     * Initializes the DataRetriever
     * @param ios Stream to be monitored
     * @param u User the DataRetriever belongs too
     * @param dataInput DataModel for forwading chat messages
     * @param service  Service the service is running in
     * @param server Server the dataRetriever belongs too
     */
    public DataRetriever(ObjectInputStream ios, User u, DataInput dataInput, ExecutorService service, Server server) {
        this.ios = ios;
        this.dataInput = dataInput;
        this.u = u;
        this.service = service;
        this.server = server;
        this.setExecutor(this.service);

        this.setOnSucceeded(e -> {
            Object unit = DataRetriever.this.getValue();
            if(unit instanceof ChatUnit) {
                System.out.print("Adding DATA\n");
                ChatUnit u1 = (ChatUnit) unit;
                this.dataInput.add(u1);
            }else if(unit instanceof UserListRequest) {
                UserListRequest ulr = (UserListRequest) unit;
                System.out.println("Recieved UserListRequest from" +ulr.getUsername());
                List<User> users1 = server.getCompleteUserList();
                UserListCarrier users = new UserListCarrier(users1);
                if(u instanceof ServerUser) {
                    try {
                        System.out.println("Sending UserListCarrier");
                        ((ServerUser) u).getOos().writeObject(users);
                        ((ServerUser) u).getOos().flush();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if(unit instanceof GetIpRequest) {
                if(u instanceof ServerUser) {
                    GetIpCarrier gipc = new GetIpCarrier(((ServerUser) u).getS().getInetAddress().getHostAddress(), u.getUsername());
                    try {
                        ((ServerUser) u).getOos().writeObject(gipc);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (unit instanceof CharacterRequest) {

                Model.Character.Character c = this.server.getCharacterFromUser(((CharacterRequest)unit).username);
                CharacterDisplayCarrier cid = new CharacterDisplayCarrier(((CharacterRequest)unit).username, c);
                if(u instanceof ServerUser) {
                    try {
                        ((ServerUser) u).getOos().writeObject(cid);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


            }else if(unit instanceof CharacterChangeNotification) {
                if(u instanceof ServerUser) {
                    ServerUser su = (ServerUser) u;
                    su.setCharacter(((CharacterChangeNotification)unit).characterToTake);
                }
            }else if(unit instanceof DisconnectNotification) {

                this.server.userDisconnected(this.u);

            }


            DataRetriever.this.restart();
        });

        this.setOnFailed(e -> {
            Exception exception = (Exception) DataRetriever.this.getException();
            if(SocketTimeoutException.class.isInstance(exception)) {
                DataRetriever.this.restart();
            }
        });
    }

    /**
     * Stops the DataRetriever
     */
    public void stop()  {
        this.setOnFailed(e -> {
            return;
        });

        this.setOnSucceeded( e -> {
            return;
        });
    }

    @Override
    /**
     * Main Logic behind the DataRetriever. This method is always invoked to see if anything has been sent and if yes, then it is determined
     * if the data can be used or not and then it is forwarded for further processing.
     */
    protected Task<DataOverNetwork> createTask() {
        final ObjectInputStream ios = this.ios;
        final User u = this.u;
        return new Task<DataOverNetwork>(){
            @Override
            protected DataOverNetwork call() throws Exception {
                if(true) {
                    Object o = ios.readObject();
                    if(String.class.isInstance(o)){
                        String s = (String) o;
                        ChatUnit chatUnit = new ChatUnit(u.getUsername(), s);
                        return chatUnit;
                    } else if (o instanceof UserListRequest) {
                        UserListRequest ulr = (UserListRequest) o;
                        ulr.setUsername(u.getUsername());
                        return ulr;
                    } else if(o instanceof GetIpRequest) {
                        return (GetIpRequest) o;
                    } else if(o instanceof DisconnectNotification) {
                        return (DisconnectNotification) o;
                    } else if (o instanceof CharacterRequest) {
                        return (CharacterRequest)o;
                    } else if (o instanceof CharacterChangeNotification) {
                        return (CharacterChangeNotification)o;
                    }

                }

                return  null;
            }
        };
    }
}
