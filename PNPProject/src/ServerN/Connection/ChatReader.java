package ServerN.Connection;


import ServerN.Chat.Messengar;
import ServerN.Model.ServerChat;
import ServerN.Model.User;
import ServerN.ServerCommandWrapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by RAIDER on 19.04.2015.
 */
public class ChatReader implements Runnable {

    private ObjectInputStream ios;
    private Messengar m;
    private User u;

    public ChatReader(ObjectInputStream ios, User u)  {
        this.ios = ios;
        this.u = u;
    }





    @Override
    public void run(){
        while(true) {
            Object o = null;
            try {
                o = ios.readObject();
                if (ServerChat.class.isInstance(o)) {
                    ServerChat sc = (ServerChat) o;
                    m.handOverMessage(sc.getMessage(), u.getUsi().getUsername());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }


}
