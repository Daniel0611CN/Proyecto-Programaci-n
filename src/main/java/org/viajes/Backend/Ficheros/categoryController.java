package org.viajes.Backend.Ficheros;

import org.viajes.BBDD.Persistencia.DAOS.CategoryDao;
import org.viajes.BBDD.Persistencia.DAOS.TransportDao;
import org.viajes.BBDD.Persistencia.Models.Category;
import org.viajes.BBDD.Persistencia.Models.Transport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class categoryController {

    public void chargeCategoryTable(String fileName) {

        CategoryDao categoryDao = new CategoryDao();
        TransportDao transportDao = new TransportDao();

        BufferedReader br = null;
        BufferedReader br2 = null;
        try {
            Category category = new Category();
            Transport transport = new Transport();
            categoryDao.getAll("src/main/java/org/viajes/Frontend/Informacion/categories.txt");
            transportDao.getAll("src/main/java/org/viajes/Frontend/Informacion/transports.txt");

            br = new BufferedReader(new FileReader("src/main/java/org/viajes/Frontend/Informacion/categories.txt"));
            br2 = new BufferedReader(new FileReader("src/main/java/org/viajes/Frontend/Informacion/transports.txt"));

            String line = br.readLine();
            String line2 = br2.readLine();

            ArrayList<String> item = new ArrayList<>();
            ArrayList<String> item2 = new ArrayList<>();

            while(line != null) {
                item.add(line);
                line = br.readLine();
            }

            category.setId(null);

            while(line2 != null) {
                item2.add(line2);
                line2 = br2.readLine();
            }

            transport.setId(null);

            Iterator<String> categories = item.iterator();
            while(categories.hasNext()) {
                String next = categories.next();
                item.add(next);
                category.setName(item.get(1));
                category.setIncrement(Double.valueOf(item.get(2)));
            }

            Iterator<String> transports = item2.iterator();
            while(transports.hasNext()) {
                String next = transports.next();
                item2.add(next);
                transport.setName(item2.get(1));
            }

            br.close();
            br2.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
