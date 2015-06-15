package Model.Request;

import Model.DataOverNetwork;
import Model.User;

import java.io.Serializable;
import java.util.List;

/**
 * This is notifies the server to remove a user from the UserList
 */
public class UserRemovalNotification implements Serializable, DataOverNetwork{

    public final List<User> usersToRemove;

    public UserRemovalNotification(List<User> usersToRemove) {
        this.usersToRemove = usersToRemove;
    }
}
