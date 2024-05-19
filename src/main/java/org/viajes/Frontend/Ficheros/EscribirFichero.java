package org.viajes.Frontend.Ficheros;

import org.viajes.BBDD.Persistencia.Models.RegisterUser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFichero {

    private final String url = "src/main/java/org/viajes/Frontend/Informacion/transportes.txt";

    public void writeFileRegisterUser(RegisterUser registerUser) throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter(url, false));
        out.write(registerUser.getName());
        out.newLine();
        out.write(registerUser.getSurname());
        out.newLine();
        out.write(registerUser.getEmail());
        out.newLine();
        out.write(registerUser.getTelephone());
        out.newLine();
        out.write(registerUser.getPassword());
        out.newLine();
        out.close();

    }

    public EscribirFichero() {
    }

    public String getUrl() {
        return url;
    }
}
