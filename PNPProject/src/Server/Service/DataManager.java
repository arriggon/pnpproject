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
 * Created by RAIDER on 10.05.2015.
 */
public class DataManager extends Service<Void> {

    private DataInput dataInput;
    private ExecutorService service;
    private ChatList chatList;
    private UserList userList;

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

    @Override
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
                        userList.getList().parallelStream().filter(t -> !t.equals(chatUnit.getU())).forEach(e -> {
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
