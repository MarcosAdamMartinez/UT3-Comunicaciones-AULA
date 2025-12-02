package psp.comms.hilos.server;

import psp.comms.hilos.model.Ejemplo;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GestionClientes extends Thread{

    private int numCliente;
    private Socket cliente;

    public GestionClientes (Socket cliente, int numCliente) {
        this.cliente = cliente;
        this.numCliente = numCliente;
    }

    @Override
    public void run() {

        ObjectInputStream bufferObjetosEntrada = null;
        ObjectOutputStream bufferObjetosSalida = null;

        System.out.println("Hilo atencion al cliente: "+numCliente);

        try {
            bufferObjetosEntrada = new ObjectInputStream(cliente.getInputStream());
            bufferObjetosSalida = new ObjectOutputStream(cliente.getOutputStream());

            Ejemplo datosEntrada = (Ejemplo) bufferObjetosEntrada.readObject();
            System.out.println("Hemos recibido del cliente: "+datosEntrada.toString());

            Ejemplo datosSalida = new Ejemplo("Objeto del servidor", numCliente);
            bufferObjetosSalida.writeObject(datosSalida);
            bufferObjetosSalida.flush();
            System.out.println("Hemos enviado al cliente: "+numCliente+", el objeto: "+datosSalida.toString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferObjetosEntrada.close();
                bufferObjetosSalida.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
