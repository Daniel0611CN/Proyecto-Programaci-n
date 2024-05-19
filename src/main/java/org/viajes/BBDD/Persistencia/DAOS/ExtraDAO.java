package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.viajes.BBDD.Persistencia.Interfaces.IDAO;
import org.viajes.BBDD.Persistencia.Models.Extra;

public class ExtraDAO extends BasicDAO implements IDAO<Extra, Integer> {

    public ExtraDAO() {
        super();
    }

    @Override
    public boolean createTable(String file) throws Exception {
        boolean result = false;
        try {
            result = super.createTable(file != null ? file : "src/bdFiles/extraTable.txt");
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    public Extra readItemFromFile(String fileName) throws Exception {
        Extra extra = new Extra();
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

                extra.setName(item[1]);
                extra.setPrice(Double.valueOf(item[2]));

                if(item[0] != "null") {
                    extra.setId(Integer.getInteger(item[0]));
                } else {
                    extra.setId(this.getIdFromBD("extra"));
                }
            } catch(Exception e) {
                throw new Exception(e);
            } finally {
                if (in != null)
                    in.close();
            }
        }

        return extra;
    }

    @Override
    public boolean insert(String fileName) throws Exception {
        boolean result = false;
        Extra extra = null;

        if (fileName != null) {
            String sql="INSERT INTO extra (id, name, price) VALUES(?, ? ,? )";
            PreparedStatement sentence;
            try {
                this.connect();
                extra = this.readItemFromFile(fileName);

                sentence = connection.prepareStatement(sql);
                sentence.setInt(1, extra.getId());
                sentence.setString(2, extra.getName());
                sentence.setDouble(3, extra.getPrice());

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
            String sql = "UPDATE extra SET name=?, price=? WHERE id = ?";
            try {
                Extra extra = this.readItemFromFile(fileName);
                this.connect();
                PreparedStatement sentence;
                sentence = connection.prepareStatement(sql);
                sentence.setString(1, extra.getName());
                sentence.setDouble(2, extra.getPrice());
                sentence.setInt(3, extra.getId());

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
        String sql = "SELECT * FROM extra";
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
                    out.write(String.valueOf(resultSet.getDouble("price")));

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
        String sql = "SELECT * FROM extra WHERE id = ?";
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
                    out.write(String.valueOf(resultSet.getDouble("price")));
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
        TravelExtraDAO travelExtraDao = new TravelExtraDAO();
        TransportExtraDAO transportExtraDao = new TransportExtraDAO();

        if (fileName != null) {
            try {
                String sql = "DELETE FROM extra WHERE id = ?";
                int id = this.readIdFromFile(fileName);
                this.connect();

                sentence = connection.prepareStatement(sql);
                sentence.setInt(1, id);
                int rowsAffected = sentence.executeUpdate();

                if (rowsAffected > 0) {
                    result = true && travelExtraDao.removeFromExtra(id) && transportExtraDao.removeFromExtra(id);
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
