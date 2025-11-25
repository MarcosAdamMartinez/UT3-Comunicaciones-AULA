package psp.comms.clienteservidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {
        InetAddress direccion; // Aqui sacamos la direccion
        Socket servidor = null; // Aqui nos conectamos al servidor
        int PUERTO = 5000; // Por este puerto constante nos conectamos

        System.out.println("Soy el cliente :D");

        try{
            direccion = InetAddress.getLocalHost();
            servidor = new Socket(direccion, PUERTO);
            // Al intentar conectar en un Puerto cerrado, nos salta un error UnknownHostException

            System.out.println("La conexion se hizo correctamente");

            DataInputStream datos = new DataInputStream(servidor.getInputStream()); // Recibimos los datos del servidor
            // System.out.println(datos.readLine()); // Leemos los datos, el readLine esta deprecado
            System.out.println(datos.readUTF()); // Leemos los datos

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (servidor != null){
                    servidor.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
