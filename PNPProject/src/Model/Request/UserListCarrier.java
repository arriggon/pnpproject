package Model.Request;

import Model.ChatList;
import Model.ChatUnit;
import Model.DataOverNetwork;
import Model.User;
import javafx.scene.control.ListCell;

import java.io.Serializable;
import java.util.List;

/**
 * This is an object sent to the network-participant, who sent a UserListRequest-object, by the server containing the general UserList.
 */
public class UserListCarrier implements Serializable, DataOverNetwork {

    public final List<User> listToAddOn;

    public UserListCarrier(List<User> listToAddOn) {
        this.listToAddOn = listToAddOn;
    }

}
