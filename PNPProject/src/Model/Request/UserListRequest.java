package Model.Request;

import Model.ChatUnit;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * Created by RAIDER on 15.05.2015.
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
