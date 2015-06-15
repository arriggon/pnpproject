package Model;

import java.io.Serializable;

/**
 * Is a container for the chat-message as well as its sender.
 */
public class ChatUnit implements Serializable, DataOverNetwork {

    /**
     * Holds the sender's username
     */
    private String username;

    /**
     * Holds the message sent
     */
    private String message;

    /**
     * Constructor
     * @param username Username of the sender
     * @param message Message to be sent
     */
    public ChatUnit(String username, String message) {
        this.username = username;
        this.message = message;
    }

    /**
     * Get-Method for the username
     * @return Returns the username of the sender
     */
    public String getU() {
        return username;
    }

    /**
     * Set-Method for the username
     * @param u User name of the sender
     */
    public void setU(String u) {
        this.username = u;
    }

    /**
     * Get-Method for the message
     * @return Returns the message to be sent
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set-Method for the message
     * @param message Message to be sent
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    /**
     * To-String method of the ChatUnit
     */
    public String toString() {
        return "["+username+"]: "+message;
    }
}
