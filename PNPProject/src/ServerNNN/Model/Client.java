package ServerNNN.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Alexander on 27.04.2015.
 */
public class Client {
    private String username;
    private String uid;

    public Client(String username) {
        this.username = username;
        Random r = new Random();
        long uidTemp = r.nextLong();
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd'-'hh:mm:ss");
         String str = f.format(d);
        str += String.valueOf(uidTemp);
        str += String.valueOf(str.hashCode());
        uid = str;

    }

    public String getUsername() {return username;}
}
