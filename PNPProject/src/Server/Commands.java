package Server;

/**
 * Created by Alexander on 27.04.2015.
 */
public class Commands {
    public final static String SendClient = "SendClient";
    public final static String SendPassword = "SendPassword";
    public final static String ConnectionDenied = "cDenied";
    public final static String ConnectionDeniedBy(String cause) {
        return ConnectionDenied+"By="+cause;
    }
}
