package org.viajes.BBDD.Persistencia.Interfaces;

public interface IDAO<T, K>{

    boolean createTable(String fileName) throws Exception;
    boolean insert(String fileName) throws Exception;
    boolean update(String fileName) throws Exception;
    boolean remove(String fileName) throws Exception;
    boolean getAll(String fileName) throws Exception;
    boolean getItem(String fileName) throws Exception;
    T readItemFromFile(String fileName) throws Exception;

}

