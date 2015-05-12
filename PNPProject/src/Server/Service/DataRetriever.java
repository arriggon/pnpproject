package Server.Service;

import Model.*;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.xml.crypto.Data;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by RAIDER on 09.05.2015.
 */
public class DataRetriever extends Service<ChatUnit> {

    private ObjectInputStream ios;
    private User u;
    private DataInput dataInput;
    private ExecutorService service;

    public DataRetriever(ObjectInputStream ios, User u, DataInput dataInput, ExecutorService service) {
        this.ios = ios;
        this.dataInput = dataInput;
        this.u = u;
        this.service = service;
        this.setExecutor(this.service);

        this.setOnSucceeded(e -> {
            ChatUnit unit = DataRetriever.this.getValue();
            if(unit != null) {
                System.out.print("Data added:\n");
                dataInput.add(unit);
            }
            DataRetriever.this.restart();
        });

        this.setOnFailed(e -> {
            Exception exception = (Exception) DataRetriever.this.getException();
            if(SocketTimeoutException.class.isInstance(exception)) {
                DataRetriever.this.restart();
            }
        });
    }

    public void stop()  {
        this.setOnFailed(e -> {
            return;
        });

        this.setOnSucceeded( e -> {
            return;
        });
    }

    @Override
    protected Task<ChatUnit> createTask() {
        final ObjectInputStream ios = this.ios;
        return new Task<ChatUnit>(){
            @Override
            protected ChatUnit call() throws Exception {
                if(true) {
                    Object o = ios.readObject();
                    if(String.class.isInstance(o)){
                        String s = (String) o;
                        ChatUnit chatUnit = new ChatUnit(u, s);
                        return chatUnit;
                    }

                }

                return  null;
            }
        };
    }
}
