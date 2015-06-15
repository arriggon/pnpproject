package Model.Request;

import Model.Character.*;
import Model.Character.Character;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * Sends a request to the server to change a character
 */
public class CharacterChangeNotification implements Serializable, DataOverNetwork {

    public final Model.Character.Character characterToTake;

    public CharacterChangeNotification(Character characterToTake) {
        this.characterToTake = characterToTake;
    }

}
