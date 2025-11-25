package psp.comms.clienteservidorudp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {

    public static void main(String[] args) {

        int PUERTO = 6789;
        int BUFFER_SIZE = 1000;

        System.out.println("Soy el servidor y empiezo a escuchar por el puerto: " + PUERTO);

        // Creamos el socket
        try (DatagramSocket socketUDP = new DatagramSocket(PUERTO)){

            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {

                DatagramPacket peticion = new DatagramPacket(buffer, BUFFER_SIZE);
                socketUDP.receive(peticion);

                System.out.println("Datagrama recibido del host: " + peticion.getAddress()
                        + " desde el puerto remoto: " + peticion.getPort());

                DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(), peticion.getPort());
                socketUDP.send(respuesta);


            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
