package ServerN.Model;


import ServerN.Connection.ChatReader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class User {

    private Socket s;
    private UserInfo usi;
    private ObjectInputStream ios;
    private ObjectOutputStream oos;
    private ChatReader c;

    public UserInfo getUsi() {
        return usi;
    }

    public Socket getS() {
        return s;
    }

    public ObjectInputStream getIos() {
        return ios;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ChatReader getC() {
        return c;
    }

    public User(Socket s, UserInfo usi, ObjectInputStream ios, ObjectOutputStream oos) throws IOException {
        this.s = s;
        this.usi = usi;
        this.ios = ios;
        this.oos = oos;
        this.c = new ChatReader(ios, this);
    }

    public boolean disconnect() throws IOException {
        this.ios.close();
        this.oos.close();
        this.s.close();
        return true;
    }

    public ObjectInputStream getInput() {
        return this.ios;
    }

    public ObjectOutputStream getOutput() {
        return this.oos;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!usi.equals(user.usi)) return false;

        return true;
    }

    public ChatReader getChatter() {
        return this.c;
    }

    @Override
    public int hashCode() {
        return usi.hashCode();
    }
}
