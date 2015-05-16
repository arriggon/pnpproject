package GUI.CharEdit;

/**
 * Created by RAIDER on 16.05.2015.
 */
public class CharacterCreatorException extends Exception {

    public CharacterCreatorException() {
    }

    public CharacterCreatorException(String message) {
        super(message);
    }

    public CharacterCreatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharacterCreatorException(Throwable cause) {
        super(cause);
    }

    public CharacterCreatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
