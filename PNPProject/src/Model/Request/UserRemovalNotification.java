package Model.Request;

import Model.DataOverNetwork;
import Model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RAIDER on 16.05.2015.
 */
public class UserRemovalNotification implements Serializable, DataOverNetwork{

    public final List<User> usersToRemove;

    public UserRemovalNotification(List<User> usersToRemove) {
        this.usersToRemove = usersToRemove;
    }
}
