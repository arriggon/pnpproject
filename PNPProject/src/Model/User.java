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
        return "<"+username+">";
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
