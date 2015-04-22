package ServerN.Connection;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by Alexander on 22.04.2015.
 */
public class ChatReaderManager implements Runnable{

    private ArrayList<Future> chatters;
    private ExecutorService t;

    public ChatReaderManager(ExecutorService t) {
        this.chatters = new ArrayList<Future>();
        this.t = t;
    }

    public void addChatter(ChatReader reader) {

    }

    @Override
    public void run() {

    }
}
