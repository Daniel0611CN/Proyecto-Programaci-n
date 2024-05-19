package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.viajes.BBDD.Persistencia.Interfaces.IDAO;
import org.viajes.BBDD.Persistencia.Models.Location;

public class LocationDAO extends BasicDAO implements IDAO<Location, Integer> {

    public LocationDAO() {
        super();
    }

    @Override
    public boolean createTable(String fileName) throws Exception {
        boolean result = false;
        try {
            result = super.createTable(fileName != null ? fileName : "src/bdFiles/locationTable.txt");
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    public Location readItemFromFile(String fileName) throws Exception {
        Location location = new Location();
        BufferedReader in = null;

        if (fileName != null) {
            try {
                int cont = 0;
                String[] item = new String[3];
                in = new BufferedReader(new FileReader(fileName));
                String line = in.readLine();

                while(line != null) {
                    item[cont] = line;
                    line = in.readLine();
                    cont++;
                }

                location.setName(item[1]);
                location.setCountry(item[2]);

                if(item[0] != "null") {
                    location.setId(Integer.getInteger(item[0]));
                } else {
                    location.setId(this.getIdFromBD("location"));
                }
            } catch(Exception e) {
                throw new Exception(e);
            } finally {
                if (in != null)
                    in.close();
            }
        }

        return location;
    }

    @Override
    public boolean insert(String fileName) throws Exception {
        boolean result = false;
        Location location = null;

        if (fileName != null) {
            String sql="INSERT INTO location (id, name, country) VALUES(?, ? ,? )";
            PreparedStatement sentence;
            try {
                this.connect();
                location = this.readItemFromFile(fileName);

                sentence = connection.prepareStatement(sql);
                sentence.setInt(1, location.getId());
                sentence.setString(2, location.getName());
                sentence.setString(3, location.getCountry());

                sentence.executeUpdate();
                result = true;
            } catch (SQLException e) {
                throw new Exception(e);
            } finally {
                try {
                    this.closeConnection();
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            }
        }

        return result;
    }

    @Override
    public boolean update(String fileName) throws Exception {
        boolean result = false;
        if (fileName != null) {
            String sql = "UPDATE location SET name=?, country=? WHERE id = ?";
            try {
                Location location = this.readItemFromFile(fileName);
                this.connect();
                PreparedStatement sentence;
                sentence = connection.prepareStatement(sql);
                sentence.setString(1, location.getName());
                sentence.setString(2, location.getCountry());
                sentence.setInt(3, location.getId());

                int rowsAffected = sentence.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                }
            } catch (SQLException ex) {
                throw new SQLException(ex);
            }finally {
                try {
                    this.closeConnection();
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            }
        }
        return result;
    }


    @Override
    public boolean getAll(String fileName) throws Exception {
        boolean result = false;
        String sql = "SELECT * FROM location";
        PreparedStatement sentence = null;
        ResultSet resultSet = null;
        BufferedWriter out = null;

        if (fileName != null) {
            try {
                boolean firstLine = true;
                this.connect();
                sentence = connection.prepareStatement(sql);
                resultSet = sentence.executeQuery();
                out = new BufferedWriter(new FileWriter(fileName, false));

                while (resultSet.next()) {
                    if(firstLine) {
                        firstLine = false;
                    } else {
                        out.newLine();
                    }
                    out.write(resultSet.getInt("id"));
                    out.newLine();
                    out.write(resultSet.getString("name"));
                    out.newLine();
                    out.write(resultSet.getString("country"));

                }
                result = true;
            } catch (SQLException e) {
                throw new Exception(e);
            } finally {
                try {
                    this.closeConnection();
                    if (resultSet != null)
                        resultSet.close();
                    if (sentence != null)
                        sentence.close();
                    if (out != null)
                        out.close();
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            }
        }
        return result;
    }

    @Override
    public boolean getItem(String fileName) throws Exception {
        boolean result = false;
        String sql = "SELECT * FROM location WHERE id = ?";
        PreparedStatement sentence = null;
        ResultSet resultSet = null;
        BufferedWriter out = null;

        if (fileName != null) {
            try {
                int id = this.readIdFromFile(fileName);
                this.connect();
                sentence = connection.prepareStatement(sql);
                sentence.setInt(1, id);
                resultSet = sentence.executeQuery();
                out = new BufferedWriter(new FileWriter(fileName, false));

                if (resultSet.next()) {
                    out.write(resultSet.getInt("id"));
                    out.newLine();
                    out.write(resultSet.getString("name"));
                    out.newLine();
                    out.write(resultSet.getString("country"));
                }
                result = true;
            } catch (SQLException e) {
                throw new Exception(e);
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    if (sentence != null)
                        sentence.close();
                    if (out != null)
                        out.close();
                    this.closeConnection();
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            }
        }

        return result;
    }

    @Override
    public boolean remove(String fileName) throws Exception {
        boolean result = false;
        PreparedStatement sentence = null;

        if (fileName != null) {
            try {
                String sql = "DELETE FROM location WHERE id = ?";
                int id = this.readIdFromFile(fileName);
                this.connect();

                sentence = connection.prepareStatement(sql);
                sentence.setInt(1, id);
                int rowsAffected = sentence.executeUpdate();

                if (rowsAffected > 0) {
                    result = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (sentence != null)
                        sentence.close();
                    this.closeConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;

    }


}

