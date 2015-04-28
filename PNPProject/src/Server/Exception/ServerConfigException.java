package Server.Exception;

/**
 * Created by Alexander on 24.03.2015.
 */
public class ServerConfigException extends Exception {
    public ServerConfigException() {
        super();
    }

    public ServerConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerConfigException(String message) {
        super(message);
    }

    public ServerConfigException(Throwable cause) {
        super(cause);
    }

    public ServerConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
