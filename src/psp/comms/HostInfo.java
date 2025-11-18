package psp.comms;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;

public class HostInfo {
    public static void main(String[] args) {

        try {
//            Devuelve los datos de la adress local
            InetAddress localHost = InetAddress.getLocalHost();
//            System.out.println("Nombre del host local : "+localHost.getHostName());
//            System.out.println("Direccion IP local : "+localHost.getHostAddress());
////            Devuelve los datos de la adress de google
//            InetAddress google = InetAddress.getByName("www.google.com");
//            System.out.println("Nombre del host remoto : "+google.getHostName());
//            System.out.println("Direccion IP remota : "+google.getHostAddress());

//            Devuelve una enumeracion con todas las interfaces de red que tiene nuestro equipo
            Enumeration<NetworkInterface> interfaces =  NetworkInterface.getNetworkInterfaces();
//            Recorrer el Enumeration
            while (interfaces.hasMoreElements()) {
//                Pillamos cada interfaz individualmente
                NetworkInterface ni = interfaces.nextElement();

                System.out.println("Nombre de la interfaz: "+ni.getName());
                System.out.println("Interfaz activa: "+ni.isUp());
                System.out.println("MAC Address: "+ Arrays.toString(ni.getHardwareAddress()));
//                Recogemos todas las ips que tiene la interfaz:
                Enumeration<InetAddress> direcciones = ni.getInetAddresses();
//                Recorremos el Enumeration:
                while (direcciones.hasMoreElements()) {
                    InetAddress ip = direcciones.nextElement();
                    System.out.println("Direccion IP: "+ip.getHostAddress());
                }

            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }


    }
}
