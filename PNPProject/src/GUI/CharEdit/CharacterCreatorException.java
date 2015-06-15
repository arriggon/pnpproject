package GUI.CharEdit;

/**
 * This is an exception thrown if a value or multiple values in the character-creation do not fit the specified terms
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
