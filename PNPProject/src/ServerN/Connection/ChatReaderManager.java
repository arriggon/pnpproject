
package ServerN.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


/**
 * Created by Alexander on 22.04.2015.
 */

public class ChatReaderManager{

    private HashMap<ChatReader, Future<Object>> chatters;
    private ExecutorService t;

    public ChatReaderManager(ExecutorService t) {
        this.chatters = new HashMap<ChatReader, Future<Object>>();
        this.t = t;
    }

    public void addChatter(ChatReader reader) {
        Future f = t.submit(reader);
        chatters.put(reader, f);
    }

    public void removeChatter(ChatReader r) {
        Future<Object> future = chatters.get(r);
        chatters.remove(r);
        future.cancel(true);
    }


}

