package psp.comms.hilos.client;

import psp.comms.hilos.model.Ejemplo;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {
        InetAddress direccion; // Aqui sacamos la direccion
        Socket servidor = null; // Aqui nos conectamos al servidor
        int PUERTO = 5000; // Por este puerto constante nos conectamos

        System.out.println("Soy el cliente :D");
        ObjectOutputStream bufferDatosSalida = null;
        ObjectInputStream bufferDatosEntrada = null;
        try{
            direccion = InetAddress.getLocalHost();
            servidor = new Socket(direccion, PUERTO);
            // Al intentar conectar en un Puerto cerrado, nos salta un error UnknownHostException

            System.out.println("La conexion se hizo correctamente");

            bufferDatosSalida = new ObjectOutputStream(servidor.getOutputStream());
            Ejemplo datosSalida = new Ejemplo("Objeto del cliente", 1);
            bufferDatosSalida.writeObject(datosSalida);
            bufferDatosSalida.flush();
            System.out.println("Hemos enviado al servidor el objeto: "+datosSalida.toString());

            Thread.sleep(10000);

            bufferDatosEntrada = new ObjectInputStream(servidor.getInputStream());
            Ejemplo datosEntrada = (Ejemplo) bufferDatosEntrada.readObject();
            System.out.println("Hemos recibido del servidor: "+datosEntrada.toString());


//            DataInputStream datos = new DataInputStream(servidor.getInputStream()); // Recibimos los datos del servidor
            // System.out.println(datos.readLine()); // Leemos los datos, el readLine esta deprecado
//            System.out.println(datos.readUTF()); // Leemos los datos

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (servidor != null){
                    servidor.close();
                }
                bufferDatosEntrada.close();
                bufferDatosSalida.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
