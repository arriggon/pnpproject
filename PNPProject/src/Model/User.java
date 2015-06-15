package Model;

import java.io.Serializable;

/**
 * This class is a abstract representation of a client, superclass of: ServerUser
 */
public class User implements Serializable{
    /**
     * Holds the username for the User
     */
    private String username;

    /**
     * Constructor
     * @param str The User's username
     */
    public User(String str) {
        this.username = str;
    }

    @Override
    /**
     * The toString-method for User
     */
    public String toString() {
        return "<"+username+">";
    }

    /**
     * Gets the User's username
     * @return Returns the String username
     */
    public String getUsername() {
        return username;
    }

    @Override
    /**
     * Equals-method comparing usernames
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    /**
     * Returns a username's hashcode
     */
    public int hashCode() {
        return username.hashCode();
    }
}
