package Model.Request;

import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * Created by RAIDER on 16.05.2015.
 */
public class GetIpCarrier implements Serializable, DataOverNetwork {

    public final String ipAddress;
    public final String username;

    public GetIpCarrier(String ipAddress, String username) {
        this.ipAddress = ipAddress;
        this.username = username;
    }

}
