package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.viajes.BBDD.Persistencia.Interfaces.IDAO;
import org.viajes.BBDD.Persistencia.Models.RegisterUser;

public class RegisterUserDAO extends BasicDAO implements IDAO<RegisterUser, Integer> {

    public RegisterUserDAO() throws Exception {
        super();
    }

    @Override
    public RegisterUser readItemFromFile(String fileName) throws Exception {
        RegisterUser user = new RegisterUser();
        BufferedReader in = null;

        if (fileName != null) {
            try {
                int cont = 0;
                String[] item = new String[5];
                in = new BufferedReader(new FileReader(fileName));
                String line = in.readLine();

                while(line != null) {
                    item[cont] = line;
                    line = in.readLine();
                    cont++;
                }

                user.setName(item[1]);
                user.setSurname(item[2]);
                user.setEmail(item[3]);
                user.setTelephone(item[4]);

                if(item[0] != "null") {
                    user.setId(Integer.getInteger(item[0]));
                } else {
                    user.setId(this.getIdFromBD("registeruser"));
                }
            } catch(Exception e) {
                throw new Exception(e);
            } finally {
                if (in != null)
                    in.close();
            }
        }

        return user;
    }

    @Override
    public boolean insert(String fileName) throws Exception {
        boolean result = false;
        RegisterUser user = new RegisterUser();
        this.connect();

        if (fileName != null) {
            String sql="INSERT INTO registerUser (id, name, surname, email, telephone) VALUES(?, ? ,?,  ?, ?)";
            PreparedStatement sentence;
            try {
                this.connect();
                user = this.readItemFromFile(fileName);

                sentence = connection.prepareStatement(sql);
                sentence.setInt(1, user.getId());
                sentence.setString(2, user.getName());
                sentence.setString(3, user.getSurname());
                sentence.setString(4, user.getEmail());
                sentence.setString(5, user.getTelephone());

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
            String sql = "UPDATE registerUser " + "SET name=?, surname=?, email=?, telephone=? WHERE id = ?";
            try {
                RegisterUser user = this.readItemFromFile(fileName);
                this.connect();
                PreparedStatement sentence;
                sentence = connection.prepareStatement(sql);
                sentence.setString(1, user.getName());
                sentence.setString(2, user.getSurname());
                sentence.setString(3, user.getEmail());
                sentence.setString(4, user.getTelephone());
                sentence.setInt(5, user.getId());

                int rowsAffected = sentence.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
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
        String sql = "SELECT * FROM registerUser";
        PreparedStatement sentence = null;
        ResultSet resultSet = null;
        BufferedWriter out = null;

        if(fileName != null) {
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
                    out.write(resultSet.getString("surname"));
                    out.newLine();
                    out.write(resultSet.getString("email"));
                    out.newLine();
                    out.write(resultSet.getString("telephone"));
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
    public boolean getItem(String fileName) throws Exception {
        boolean result = false;
        String sql = "SELECT * FROM registerUser WHERE id = ?";
        PreparedStatement sentence = null;
        ResultSet resultSet = null;
        BufferedWriter out = null;

        if(fileName != null) {
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
                    out.write(resultSet.getString("surname"));
                    out.newLine();
                    out.write(resultSet.getString("email"));
                    out.newLine();
                    out.write(resultSet.getString("telephone"));
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
        String sql = "DELETE FROM registerUser WHERE id = ?";

        if (fileName != null) {
            try {
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

