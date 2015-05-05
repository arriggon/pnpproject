package ServerNNN.Rights;

import ServerNNN.Model.Client;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by RAIDER on 27.04.2015.
 */

//Right Management
public class Laws {
    private ConcurrentHashMap<Client, Rights> constitution;

    public Laws() {
        constitution = new ConcurrentHashMap<Client, Rights>();
    }

    public boolean appoint(Client c, Rights s) {
        Object o = constitution.put(c, s);
        if(o==null) {
            return false;
        }
        return true;
    }

    public boolean adopt(Client c) {
       Object o = constitution.remove(c);
        if(o==null){
            return false;
        }
        return true;
    }

    public boolean adapt(Client c, Rights s) {
        Object o = constitution.replace(c, s);
        if(o==null) {
            return false;
        }
        return true;

    }

}
