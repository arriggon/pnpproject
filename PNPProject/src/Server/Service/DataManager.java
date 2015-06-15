package Server.Service;

import Model.*;
import Server.Server;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Is a service of the server and is responsible for handling all incoming and outgoing data.
 */
public class DataManager extends Service<Void> {

    /**
     * Data Buffer
     */
    private DataInput dataInput;
    /**
     * Thread pool used to execute service
     */
    private ExecutorService service;
    /**
     * Datamodel behind Chat Messages
     */
    private ChatList chatList;
    /**
     * Datamodel behind Users
     */
    private UserList userList;

    /**
     * Initiates the Data Management service and gives all the required references to the service
     * @param dataInput Data Buffer
     * @param service Thread Pool
     * @param chatList  Data Model for Chat Units
     * @param userList Data Model for Users
     */
    public DataManager(DataInput dataInput, ExecutorService service, ChatList chatList, UserList userList) {
        this.dataInput = dataInput;
        this.service = service;
        this.chatList = chatList;
        this.userList = userList;
        this.setExecutor(this.service);
        this.setOnSucceeded(e -> {
            DataManager.this.restart();
        });
    }

    /**
     * Stops the thread and stops all the active Data Retriever too.
     */
    public void stop() {
        userList.getList().stream().forEach(e -> {
            if(ServerUser.class.isInstance(e)) {
                ServerUser u = (ServerUser) e;
                DataRetriever d = u.getDataRetriever();
                d.cancel();
            }
        });

        this.setOnSucceeded( e -> {
            return;
        });


    }

    /**
     * Forwards the ChatUnit into the Buffen from which it is going to work with it further
     * @param u ChatUnit to be sent
     */
    public void send(ChatUnit u) {
        dataInput.add(u);
    }

    @Override
    /**
     * Main Method of the DataManagement thread which containts the actual logic behind the thread
     */
    protected Task<Void> createTask() {

        final DataInput dataInput = this.dataInput;
        final ChatList chatList = this.chatList;
        final UserList userList = this.userList;
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if(dataInput.isUpdated()) {
                    System.out.print("Data updating");
                    List<ChatUnit> additions = dataInput.getAdditions();
                    Iterator<ChatUnit> i = additions.iterator();
                    while (i.hasNext()) {
                        ChatUnit chatUnit = i.next();
                        chatList.addChatUnit(chatUnit);
                        userList.getList().parallelStream().forEach(e -> {
                            if (ServerUser.class.isInstance(e)) {
                                ServerUser user = (ServerUser) e;
                                try {
                                    user.getOos().writeObject(chatUnit);
                                    user.getOos().flush();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    }
                }
                return null;
            }
        };
    }
}
