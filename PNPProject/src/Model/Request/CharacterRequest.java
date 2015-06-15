package Model.Request;

import Clinet.Service.DataRetriever;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * This requests a character from the server
 */
public class CharacterRequest implements Serializable, DataOverNetwork {

    public final String username;

    public CharacterRequest(String username) {
        this.username = username;
    }

}
