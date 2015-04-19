package ServerN.Model;


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



    public User(Socket s, UserInfo usi) throws IOException {
        this.s = s;
        this.usi = usi;
        this.ios = new ObjectInputStream(s.getInputStream());
        this.oos = new ObjectOutputStream(s.getOutputStream());
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

    @Override
    public int hashCode() {
        return usi.hashCode();
    }
}
