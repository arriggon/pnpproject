package Model.Request;

import Clinet.Service.DataRetriever;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * Created by Alexander on 19.05.2015.
 */
public class CharacterRequest implements Serializable, DataOverNetwork {

    public final String username;

    public CharacterRequest(String username) {
        this.username = username;
    }

}
