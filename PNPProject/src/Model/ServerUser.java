package Model;

import Server.Server;
import Server.Service.DataRetriever;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class ServerUser extends User {

    private Socket s;
    private ObjectInputStream ios;
    private ObjectOutputStream oos;
    private DataRetriever dataRetriever;


    public ServerUser(String str) {
        super(str);
        s = null;
        ios = null;
        oos = null;
        dataRetriever = null;
    }

    public ServerUser(String str, Socket s, ObjectInputStream ios, ObjectOutputStream oos) {
        super(str);
        this.s = s;
        this.ios = ios;
        this.oos = oos;
        this.dataRetriever = null;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public ObjectInputStream getIos() {
        return ios;
    }

    public void setIos(ObjectInputStream ios) {
        this.ios = ios;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public DataRetriever getDataRetriever() {
        return dataRetriever;
    }

    public void setDataRetriever(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }
}
