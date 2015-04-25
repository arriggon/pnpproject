package ServerN.Connection;


import java.io.ObjectInputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by RAIDER on 19.04.2015.
 */
public class ChatReader implements Callable<Object> {

    private ObjectInputStream ios;

    public ChatReader(ObjectInputStream ios)  {
        this.ios = ios;

    }





    @Override
    public Object call() throws Exception {

       Object o = ios.readObject();
        return o;

    }


}
