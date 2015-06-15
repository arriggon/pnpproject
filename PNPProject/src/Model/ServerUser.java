package Model;

import Model.Character.*;
import Model.Character.Character;
import Server.Server;
import Server.Service.DataRetriever;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class contains the data of every user connected to the server
 */
public class ServerUser extends User {

    /**
     * Contains connection to the client
     */
    private Socket s;
    private ObjectInputStream ios;
    private ObjectOutputStream oos;

    /**
     * Contains the thread handling data retrieved from the client
     */
    private DataRetriever dataRetriever;

    /**
     * Contains the user's character stored on the server
     */
    private Model.Character.Character character;

    /**
     * Constructor
     * @param str Contains username
     */
    public ServerUser(String str) {
        super(str);
        s = null;
        ios = null;
        oos = null;
        dataRetriever = null;
        character = null;
    }

    /**
     * Constructor
     * @param str Contains username
     * @param s Contains socket
     * @param ios Contains InputStream
     * @param oos Contains OutputStream
     */
    public ServerUser(String str, Socket s, ObjectInputStream ios, ObjectOutputStream oos) {
        super(str);
        this.s = s;
        this.ios = ios;
        this.oos = oos;
        this.dataRetriever = null;
        character = null;
    }

    /**
     * Get-Method for the Socket s
     * @return Returns the Socket s
     */
    public Socket getS() {
        return s;
    }

    /**
     * Set-Method for the Socket s
     * @param s Contains the Socket to be set with
     */
    public void setS(Socket s) {
        this.s = s;
    }

    /**
     * Get-Method for the ObjectInputStream ios
     * @return Returns the ObjectInputStream ios
     */
    public ObjectInputStream getIos() {
        return ios;
    }

    /**
     * Set-Method for the ObjectInputStream oos
     * @param ios Contains the ObjectInputStream to be set with
     */
    public void setIos(ObjectInputStream ios) {
        this.ios = ios;
    }

    /**
     * Get-Method for the ObjectOutputStream oos
     * @return Returns the ObjectOutputStream oos
     */
    public ObjectOutputStream getOos() {
        return oos;
    }

    /**
     * Set-Method for the ObjectOutputStream oos
     * @param oos Contains the ObjectOutputStream to be set with
     */
    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    /**
     * Get-Method for the DataRetriever dataRetriever
     * @return Returns the DataRetriever dataRetriever
     */
    public DataRetriever getDataRetriever() {
        return dataRetriever;
    }

    /**
     * Set-Method for the DataRetriever dataRetriever
     * @param dataRetriever Contains the DataRetriever to be set with
     */
    public void setDataRetriever(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    /**
     * Set-Method for the Character c of the user
     * @param c Contains the Character to be set with
     */
    public void setCharacter(Character c){
        this.character = c;
        System.out.println("Character set");


    }

    /**
     * Get-Method for the Character c of the user
     * @return Returns the Character c
     */
    public Character getCharacter() {
        return character;
    }
}
