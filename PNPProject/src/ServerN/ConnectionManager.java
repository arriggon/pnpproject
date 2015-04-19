package ServerN;

import ServerN.Model.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class ConnectionManager {
    private HashSet<User> users;

    public ConnectionManager() {
        this.users = new HashSet<User>();
    }

    public void addUser(User u) throws Exception{
        if(!users.add(u)) {
            throw new Exception("ConnectionManager: addUser: User "+u.toString()+"has already connected.");
        }
    }

    public Set<User> getAllUsers() {
        return (Set<User>)users.clone();
    }

    public void disconnectAllUsers(){
        try {
            Iterator<User> i = users.iterator();
            while(i.hasNext()) {
                User temp = i.next();
                temp.disconnect();
                i.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
