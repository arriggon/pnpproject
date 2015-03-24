package Server;

import java.util.ArrayList;

/**
 * Created by Alexander on 24.03.2015.
 */
public class ServerConnector {
    private ArrayList<ServerAttendant> attendants;

    public ServerConnector() {
        attendants = new ArrayList<ServerAttendant>();
    }

    public boolean addConnection(ServerAttendant sa) {
        if(attendants.contains(sa)) {
            return false;
        }
        attendants.add(sa);
        return true;


    }

    public boolean removeConnection(ServerAttendant sa) {
        if(attendants.contains(sa)) {

            attendants.remove(sa);
            return true;
        }else{
            return false;
        }
    }
}
