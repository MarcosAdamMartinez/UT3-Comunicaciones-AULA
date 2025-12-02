package psp.comms.hilos.server;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket cliente = null;

        int numCliente = 0;
        int PUERTO = 5000;

        try{
            servidor = new ServerSocket(PUERTO);
            System.out.println("Soy el servidor y estoy eschando peticiones por el puerto: " + PUERTO);

            do {
                cliente = servidor.accept();
                numCliente ++;

                System.out.println("\nLlega el cliente " + numCliente);


                GestionClientes gc = new GestionClientes(cliente, numCliente);
                gc.start();
//                DataOutputStream outputStream = new DataOutputStream(cliente.getOutputStream());
//                outputStream.writeUTF("Usted es mi cliente: " + numClientes);
//
//                cliente.close();
//                System.out.println("\tSe ha termminado la conexion con el cliente..." + numClientes);



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
