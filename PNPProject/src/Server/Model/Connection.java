package Server.Model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Alexander on 27.04.2015.
 */
public class Connection {

    public final ObjectInputStream ios;
    public final ObjectOutputStream oos;

    public Connection(ObjectInputStream ios, ObjectOutputStream oos) {
        this.ios = ios;
        this.oos = oos;
    }




}
