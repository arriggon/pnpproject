package Model.Request;

import Model.Character.*;
import Model.Character.Character;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * This is an object sent to the user, who sent a CharacterRequest-object, by the server containing the requested character.
 */
public class CharacterDisplayCarrier implements Serializable, DataOverNetwork {
    public final String username;
    public final Model.Character.Character character;

    public CharacterDisplayCarrier(String username, Character character) {
        this.username = username;
        this.character = character;
    }

}
