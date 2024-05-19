package org.viajes.Backend.Clases;

import org.viajes.BBDD.Persistencia.DAOS.RegistrerUserDao;
import org.viajes.BBDD.Persistencia.Models.RegisterUser;

import java.io.*;

public class registerUserController {

    public void registerUser(String fileName) throws IOException {

        BufferedReader br = null;
        try {
            int cont = 0;
            String[] item = new String[5];
            RegisterUser user = new RegisterUser();
            br = new BufferedReader(new FileReader("src/main/java/org/viajes/Frontend/Informacion/transportes.txt"));
            String line = br.readLine();

            while(line != null) {
                item[cont] = line;
                line = br.readLine();
                cont++;
            }

            user.setName(item[0]);
            user.setSurname(item[1]);
            user.setEmail(item[2]);
            user.setTelephone(item[3]);
            user.setPassword(item[4]);
            user.setId(null);


            br.close();

            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/org/viajes/Frontend/Informacion/transportes.txt", false));
            out.write("");
            out.newLine();
            out.write(user.getName());
            out.newLine();
            out.write(user.getSurname());
            out.newLine();
            out.write(user.getEmail());
            out.newLine();
            out.write(user.getTelephone());
            out.newLine();
            out.write(user.getPassword());
            out.close();


            new RegistrerUserDao().insertOne("src/main/java/org/viajes/Frontend/Informacion/transportes.txt");
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
