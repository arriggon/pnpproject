package Model.Request;

import Model.Character.*;
import Model.Character.Character;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * Created by Alexander on 19.05.2015.
 */
public class CharacterDisplayCarrier implements Serializable, DataOverNetwork {
    public final String username;
    public final Model.Character.Character character;

    public CharacterDisplayCarrier(String username, Character character) {
        this.username = username;
        this.character = character;
    }

}
