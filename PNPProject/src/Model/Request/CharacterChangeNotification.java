package Model.Request;

import Model.Character.*;
import Model.Character.Character;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * Created by Alexander on 20.05.2015.
 */
public class CharacterChangeNotification implements Serializable, DataOverNetwork {

    public final Model.Character.Character characterToTake;

    public CharacterChangeNotification(Character characterToTake) {
        this.characterToTake = characterToTake;
    }

}
