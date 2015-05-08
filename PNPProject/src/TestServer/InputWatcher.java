package TestServer;

import GUI.GameWindow;
import TestServer.ConnectionManagement;

import java.lang.reflect.GenericArrayType;
import java.util.Scanner;

/**
 * Created by Alexander on 28.04.2015.
 */
public class InputWatcher{

   private ConnectionManagement connectionManagement;
    private GameWindow gm;

    public InputWatcher(ConnectionManagement cm, GameWindow gm) {
        connectionManagement = cm;
        this.gm = gm;
    }


    public void run(String str) {
            gm.appendChatText(str);
            connectionManagement.sendAll(str, null);
    }
}
