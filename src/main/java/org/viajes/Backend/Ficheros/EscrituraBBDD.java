package org.viajes.Backend.Ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EscrituraBBDD {


    public static void main(String[] args) throws IOException {

        String texto = "";
        BufferedReader br = new BufferedReader(new FileReader("fichero.txt"));
        try {
            String linea = br.readLine();
            while (linea != null) {
                texto = texto + linea + "\n";
                linea = br.readLine();
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

}
