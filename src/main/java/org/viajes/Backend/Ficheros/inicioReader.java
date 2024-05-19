package org.viajes.Backend.Ficheros;

import org.viajes.BBDD.Persistencia.DAOS.RegistrerUserDao;

public class inicioReader {

    public void leer() throws Exception {
        RegistrerUserDao registerUserDao = new RegistrerUserDao();
        registerUserDao.createTable(null);
    }

    public static void main(String[] args) throws Exception {
        new inicioReader().leer();
    }


}
