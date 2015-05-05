package ServerNNN.Chat;

import GUI.Controller;
import ServerNNN.Chat.Model.ChatUnit;
import ServerNNN.Model.Client;
import ServerNNN.Model.Connection;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class ChatObserver {

    private ConcurrentLinkedQueue<ChatUnit> history;
    private Controller gui;
    private AtomicLong lastEntered;
    private ConcurrentHashMap<Client, Future> chatters;
    private ServerNNN.Server s;

    public ChatObserver(ServerNNN.Server s){
        history = new ConcurrentLinkedQueue<ChatUnit>();
        lastEntered = new AtomicLong(0);
        chatters = new ConcurrentHashMap<Client, Future>();
        this.s = s;
    }

    public void addUnit(ChatUnit cu) {
        history.add(cu);
        s.getC().handOverMessage(cu.getUser().getUsername(), cu.getMessage());
        lastEntered.incrementAndGet();
    }

    public Iterator<ChatUnit> getHistoryIterator() {
        Iterator<ChatUnit> i = history.iterator();
        return i;

    }

    public void addChatter(Client c, Connection co, ServerNNN.Server s) {
        Chatter ci = new Chatter(c, co, this);
        Future f = s.getServerThreads().submit(ci);
        chatters.put(c, f);
    }




}
