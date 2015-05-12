package Model;

import Server.Service.DataRetriever;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by RAIDER on 10.05.2015.
 */
public class DataInput {

    private ConcurrentLinkedQueue<ChatUnit> dataFlow;
    private AtomicBoolean updated;

    public DataInput() {
        dataFlow = new ConcurrentLinkedQueue<ChatUnit>();
        updated = new AtomicBoolean(false);
    }

    public void add(ChatUnit u) {
        dataFlow.add(u);
        updated.set(true);
    }

    public boolean isUpdated(){
        return updated.get();
    }

    public List<ChatUnit> getAdditions() {
        ArrayList<ChatUnit> list = new ArrayList<ChatUnit>();
        while(!dataFlow.isEmpty()) {
            list.add(dataFlow.poll());
        }

        updated.set(false);

        return list;
    }

}
