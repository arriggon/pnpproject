package ServerN.Connection;

import ServerN.Chat.Messengar;
import ServerN.Model.ServerChat;
import ServerN.Server;


import java.io.ObjectInputStream;
import java.util.concurrent.Callable;

/**
 * Created by RAIDER on 19.04.2015.
 */
public class ChatReader implements Callable<ServerChat> {

    private ObjectInputStream ios;

    public ChatReader(ObjectInputStream ios)  {
        this.ios = ios;

    }





    @Override
    public ServerChat call() throws Exception {

       Object o = ios.readObject();


    }
}
