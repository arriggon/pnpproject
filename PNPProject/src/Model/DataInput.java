package Model;

import Server.Service.DataRetriever;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Is a buffer that holds Chat-Unis received by the server from the clients and keeps them until the ChatList is actualized
 */
public class DataInput {
    /**
     * Buffer that temporarily stores Chat Units
     */
    private ConcurrentLinkedQueue<ChatUnit> dataFlow;

    /**
     * Update flag that is set if new data has been given
     */
    private AtomicBoolean updated;

    /**
     * Parameterless Constructor
     */
    public DataInput() {
        dataFlow = new ConcurrentLinkedQueue<ChatUnit>();
        updated = new AtomicBoolean(false);
    }

    /**
     * Adds a chat-unit to the dataflow-queue
     * @param u The ChatUnit added to the queue
     */
    public void add(ChatUnit u) {
        dataFlow.add(u);
        updated.set(true);
    }

    /**
     * Get-Method for updated
     * @return Returns the AtomicBoolean updated
     */
    public boolean isUpdated(){
        return updated.get();
    }

    /**
     * Get-Method for the contents of the dataflow-queue represented by a list
     * @return Returns the List[ChatUnit] list
     */
    public List<ChatUnit> getAdditions() {
        ArrayList<ChatUnit> list = new ArrayList<ChatUnit>();
        while(!dataFlow.isEmpty()) {
            list.add(dataFlow.poll());
        }

        updated.set(false);

        return list;
    }

}
