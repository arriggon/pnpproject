package Model;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class User {
    private String username;

    public User(String str) {
        this.username = str;
    }

    @Override
    public String toString() {
        return "User{" +
                "str='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }
}
