package Model.Request;

import Model.ChatUnit;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * This requests the UserList from the server
 */
public class UserListRequest implements Serializable, DataOverNetwork {

    private String username;

    public UserListRequest() {
        this.username = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
