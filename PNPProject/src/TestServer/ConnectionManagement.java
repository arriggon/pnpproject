package TestServer;

import GUI.GameWindow;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Alexander on 28.04.2015.
 */
public class ConnectionManagement {

    private ArrayList<Chatter> ooss;
    private GameWindow gm;

    public ConnectionManagement() {
        ooss = new ArrayList<Chatter>();

    }

    public void addConnection(Chatter c) {
        ooss.add(c);
    }
    
    public void removeConnection(Chatter c) {
    	ooss.remove(c);
    }

    public void sendAll(String s, Chatter c) {
        Iterator<Chatter> i = ooss.iterator();
        while(i.hasNext()) {
        	Chatter temp = i.next();
        	if(!temp.equals(c)) {
        		temp.send(s);
        	}
        }
    }

}
