package psp.comms;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInfo {
    public static void main(String[] args) {

        try {

            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Nombre de"+localHost);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }
}
