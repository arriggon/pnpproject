package TestClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alexander on 28.04.2015.
 */
public class Client {

    public static void main(String[] args) {
        ExecutorService str;
        str = Executors.newCachedThreadPool();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String ss = sc.nextLine();

        try {
            Socket so = new Socket();

            so.connect(new InetSocketAddress(s, Integer.parseInt(ss)));
            ObjectOutputStream oos = new ObjectOutputStream(so.getOutputStream());
            str.submit(() -> {
                ObjectInputStream ios;
                try {
                    ios = new ObjectInputStream(so.getInputStream());
                    while(true) {

                            Object o = ios.readObject();
                            System.out.print(o.toString());

                    }
                }
                    catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            });

            str.submit(() -> {
                while(true) {
                // TODO Auto-generated method stub
                String str1 = sc.next();
                try {
                    oos.writeObject(str1);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }
            });
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
