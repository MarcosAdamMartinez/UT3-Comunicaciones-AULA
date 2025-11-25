package psp.comms.clienteservidorudp;


import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClienteUDP {

    public static void main(String[] args) {

        int PUERTO = 6789;
        int BUFFER_SIZE = 1000;
        System.out.println("Soy el cliente y voy a enviar un datagrama");

        String mensaje = "Hola soy el cliente UDP";

        try (DatagramSocket socketUDP = new DatagramSocket()) {

            byte[] mensajeBytes = mensaje.getBytes();
            InetAddress hostServidor = InetAddress.getByName("localhost");

            DatagramPacket peticion = new DatagramPacket(mensajeBytes, mensajeBytes.length, hostServidor, PUERTO);
            socketUDP.send(peticion);

            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket respuesta = new DatagramPacket(buffer, BUFFER_SIZE);
            socketUDP.receive(respuesta);

            System.out.println("Respuesta: " + new String(respuesta.getData()).trim());


        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
