package Model.Request;

import Model.ChatUnit;

import java.io.Serializable;

/**
 * Created by RAIDER on 15.05.2015.
 */
public class UserListRequest extends ChatUnit implements Serializable {

    public UserListRequest() {
        super(null, "UserListRequest");
    }

}
