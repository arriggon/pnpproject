package ServerNNN.Lobby;

import ServerNNN.Model.Client;
import ServerNNN.Server;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alexander on 27.04.2015.
 */
public class Lobby{

    private Server s;
    private ConcurrentHashMap<Client, Character> characters;



    public Lobby(Server s) {
        this.s = s;
        characters = new ConcurrentHashMap<Client, Character>();
    }




}
