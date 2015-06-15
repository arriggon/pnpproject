package Model;

import Server.Service.DataRetriever;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * This class creates a list, which holds all Users connected to the server
 */
public class UserList {

    /**
     * This list holds every player connected to the server
     */
    private ObservableList<User> usersObseved;

    /**
     * Parameterless Constructor
     */
    public UserList() {
        usersObseved = FXCollections.synchronizedObservableList(FXCollections.observableList(new ArrayList<User>()));
    }

    /**
     * Get-Method for the user-list
     * @return Returns the ObservableList<User> usersObseved
     */
    public ObservableList<User> getList() {
        return usersObseved;
    }

    /**
     * Adds a user to the user-list
     * @param e The user-object to be added
     */
    public void addUser(User e)  {
        usersObseved.add(e);
    }

    /**
     * Removes the last added user of the list
     */
    public void removeUser() {
        usersObseved.remove(usersObseved.size()-1);
    }

    /**
     * Removes the specified user from the user-list
     * @param u The user-object to be removed
     */
    public void removeUser(User u) {
        Iterator<User> i = usersObseved.iterator();
        while (i.hasNext()) {
            User temp = i.next();
            if(temp.getUsername() == u.getUsername()) {
                i.remove();
                return;
            }
        }
    }






}
