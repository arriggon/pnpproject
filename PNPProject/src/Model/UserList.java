package Model;

import Server.Service.DataRetriever;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class UserList {

    private ObservableList<User> usersObseved;

    public UserList() {
        usersObseved = FXCollections.synchronizedObservableList(FXCollections.observableList(new ArrayList<User>()));
    }

    public ObservableList<User> getList() {
        return usersObseved;
    }

    public void addUser(User e)  {
        usersObseved.add(e);
    }

    public void removeUser() {
        usersObseved.remove(usersObseved.size()-1);
    }






}
