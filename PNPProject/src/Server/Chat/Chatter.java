package Server.Chat;

import Server.Chat.Model.ChatUnit;
import Server.Model.Client;
import Server.Model.Connection;

import java.io.IOException;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class Chatter implements Runnable {

    private Connection c;
    private Client ci;
    private ChatObserver co;

    public Chatter(Client ci, Connection c, ChatObserver co) {
        this.ci = ci;
        this.c = c;
        this.co = co;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Object o = c.ios.readObject();
                if(ChatUnit.class.isInstance(o)) {
                    ChatUnit u =  (ChatUnit) o;
                    co.addUnit(u);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
