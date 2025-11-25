package psp.comms.clienteservidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket cliente = null;

        int numClientes = 0;
        int PUERTO = 5000;

        try{
            servidor = new ServerSocket(PUERTO);
            System.out.println("Soy el servidor y estoy eschando peticiones por el puerto: " + PUERTO);

            do {
                cliente = servidor.accept();
                numClientes ++;

                System.out.println("\tLlega el cliente " + numClientes);

                DataOutputStream outputStream = new DataOutputStream(cliente.getOutputStream());
                outputStream.writeUTF("Usted es mi cliente: " + numClientes);

                cliente.close();
                System.out.println("\tSe ha termminado la conexion con el cliente..." + numClientes);

            } while (true); //Se hce un  bucle infinito, ya que queremos que el servidor este a la espera de que se conecte algun
            //Dispositivo
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                servidor.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
