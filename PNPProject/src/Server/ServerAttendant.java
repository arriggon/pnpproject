package Server;

import java.nio.channels.SocketChannel;
import java.util.Queue;

/**
 * Created by Alexander on 24.03.2015.
 */
public abstract class ServerAttendant {
    private SocketChannel connection;
    private final String id;
    private AttendantInfo info;
    private Queue<AttendantEvent> eventRec;

    public ServerAttendant(SocketChannel connection, String id, AttendantInfo info) {
        this.connection = connection;
    }

    private abstract void sendInfo(ServerChange sch);
    private AttendantInfo getAttendantInfo() {
        return info;
    }

    private boolean setConnection()
}
