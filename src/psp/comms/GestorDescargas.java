package psp.comms;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GestorDescargas {

    InputStream inputStream  ;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    FileWriter escritor;

    public void descargarArchivo(String urlDescargar, String nombreFichero){
        System.out.println("Descargando: " + urlDescargar + "...");

        try {
            URL url = new URL(urlDescargar);

            inputStream = url.openStream(); //Esto va a leer byte a byte
            inputStreamReader = new InputStreamReader(inputStream); //Este va a leer linea por linea
            bufferedReader = new BufferedReader(inputStreamReader);

            escritor = new FileWriter(nombreFichero);

            String linea;
            while ((linea = bufferedReader.readLine()) != null ){
                escritor.write(linea + "\n");
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                escritor.close();
                bufferedReader.close();
                inputStreamReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static void main(String[] args) {
        GestorDescargas gestorDescargas = new GestorDescargas();

        String url = "https://www.bbc.com/robots.txt";

        gestorDescargas.descargarArchivo(url, "descarga.txt");
    }
}
