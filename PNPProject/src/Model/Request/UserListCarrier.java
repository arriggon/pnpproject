package Model.Request;

import Model.ChatList;
import Model.ChatUnit;
import Model.DataOverNetwork;
import Model.User;
import javafx.scene.control.ListCell;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RAIDER on 15.05.2015.
 */
public class UserListCarrier implements Serializable, DataOverNetwork {

    public final List<User> listToAddOn;

    public UserListCarrier(List<User> listToAddOn) {
        this.listToAddOn = listToAddOn;
    }

}
