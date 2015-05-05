package Server;


import GUI.GameWindow;
import Server.Chat.ChatObserver;
import Server.Lobby.Lobby;
import Server.Model.Client;
import Server.Model.Connection;
import Server.Rights.Laws;

import java.security.acl.Owner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Alexander on 26.04.2015.
 */
public class Server {
    private ExecutorService serverThreads;
    private String password;
    private String name;
    private ConcurrentHashMap<Client, Connection> connections;
    private Future connectionEstablished;
    private ChatObserver chatObserver;
    private Owner owner;

    public Server(String password, String name, GameWindow gwin) throws Exception {
        setName(name);
        setPassword(password);
        serverThreads = Executors.newCachedThreadPool();
        connections = new ConcurrentHashMap<Client, Connection>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(name == null) {
            throw new Exception("Server: name: Null Reference given");
        }
        this.name = name;
    }

    public void start(Client c) {
        ConnectionEstablished cs = new ConnectionEstablished(this);
        Lobby l = new Lobby(this);
        Laws laws = new Laws();
        connectionEstablished = serverThreads.submit(cs);
        chatObserver = new ChatObserver(this);


    }

    public boolean addConnection(Client c, Connection co) {
        Object o = connections.put(c, co);
        chatObserver.addChatter(c, co, this);
        if(o == null) {
            return false;
        } else {
            return true;
        }
    }

    public ExecutorService getServerThreads() {
        return serverThreads;
    }

}