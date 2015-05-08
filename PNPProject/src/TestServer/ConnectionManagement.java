package TestServer;

import GUI.GameWindow;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Alexander on 28.04.2015.
 */
public class ConnectionManagement {

    private ArrayList<Chatter> ooss;
    private GameWindow gm;
    private ExecutorService threads;
    private HashMap<Chatter, Future> futures;

    public ConnectionManagement(GameWindow gm) {
        ooss = new ArrayList<Chatter>();
        this.gm = gm;
        threads = Executors.newCachedThreadPool();
    }

    public void addConnection(Chatter c) {
        ooss.add(c);            //Chatter wird hinzugefügt
        threads.submit(c);      //Aber anscheinend werden sie nicht abgearbeitet ???
    }
    
    public void removeConnection(Chatter c) {
    	ooss.remove(c);
        futures.get(c).cancel(true);
    }

    public void sendAll(String s, Chatter c) {//Funktioniert, da InputWatcher allen Clients eine Nachricht schicken kann
        Iterator<Chatter> i = ooss.iterator();
        gm.getObserver().addMessage(s);
        while(i.hasNext()) {
        	Chatter temp = i.next();
        	if(!temp.equals(c)) {
        		temp.send(s);
        	}
        }
    }

    public void stopAll() {
        Iterator<Chatter> i = ooss.iterator();
        while (i.hasNext()) {
            i.next().stop();
        }
    }

}
