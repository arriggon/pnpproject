package Server.Service;

import Model.*;
import Model.Request.UserListCarrier;
import Model.Request.UserListRequest;
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
 * Created by RAIDER on 09.05.2015.
 */
public class DataRetriever extends Service<DataOverNetwork> {

    private ObjectInputStream ios;
    private User u;
    private DataInput dataInput;
    private ExecutorService service;
    private Server server;

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

    public void stop()  {
        this.setOnFailed(e -> {
            return;
        });

        this.setOnSucceeded( e -> {
            return;
        });
    }

    @Override
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
                    }

                }

                return  null;
            }
        };
    }
}
