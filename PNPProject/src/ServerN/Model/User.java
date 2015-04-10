package ServerN.Model;

import java.net.Socket;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class User {

    private Socket s;
    private UserInfo usi;


    public User(Socket s, UserInfo usi) {
        this.s = s;
        this.usi = usi;
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
