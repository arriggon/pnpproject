package ServerN.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Alexander on 25.04.2015.
 */
public class Operator extends User {


    private  boolean isAdmin;
    private boolean isGM;

    public Operator(Socket s, UserInfo usi, ObjectInputStream ios, ObjectOutputStream oos, boolean isGM, boolean isAdmin    ) throws IOException {
        super(s, usi, ios, oos);
        this.isGM = isGM;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public boolean isGM() {
        return this.isGM;
    }

    public static Operator turnIntoOperator(User u, boolean isadmin, boolean isGM) throws IOException {
        Operator o = new Operator(u.getS(), u.getUsi(), u.getIos(), u.getOos(), isGM, isadmin);
        return o;
    }
}
