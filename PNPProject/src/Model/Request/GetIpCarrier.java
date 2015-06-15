package Model.Request;

import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * This is an object sent to the user, who sent a GetIpRequest-object, by the server containing the requested IP-address.
 */
public class GetIpCarrier implements Serializable, DataOverNetwork {

    public final String ipAddress;
    public final String username;

    public GetIpCarrier(String ipAddress, String username) {
        this.ipAddress = ipAddress;
        this.username = username;
    }

}
