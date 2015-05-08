package TestServer;

import GUI.GameWindow;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class ChatObserver implements Runnable{
    private ConcurrentLinkedQueue<String> strings;  //Eine Warteschlange für alle Strings, die in das Chatfenster geschrieben werden sollen
    private GameWindow gm;                          //Referenz zum GameWindow

    public ChatObserver(GameWindow gm) {
        strings = new ConcurrentLinkedQueue<String>();
        this.gm = gm;
    }

    public void addMessage(String s) {      //Gibt Messages an den Observer Weiter+
        strings.add(s);
    }


    @Override
    public void run() {
        while(true) {                           //Endlosschleife für aktualisierung von daten
            if(strings.peek() != null) {        //Falls daten da
                String s = strings.poll();      //Herausholen
                gm.appendChatText(s);           //Und ins chatfenster schreiben
            }
        }
    }
}
