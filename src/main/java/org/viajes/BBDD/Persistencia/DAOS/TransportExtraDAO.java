package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.viajes.BBDD.Persistencia.Interfaces.IDAO;
import org.viajes.BBDD.Persistencia.Models.TransportExtra;

public class TransportExtraDAO extends BasicDAO implements IDAO<TransportExtra, Integer> {

    public TransportExtraDAO() {
        super();
    }

    @Override
    public boolean createTable(String file) throws Exception {
        boolean result = false;
        try {
            result = super.createTable(file != null ? file : "src/bdFiles/transportExtraTable.txt.txt");
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    public TransportExtra readItemFromFile(String fileName) throws Exception {
        TransportExtra transportExtra = new TransportExtra();
        BufferedReader in = null;

        if(fileName != null) {
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

                transportExtra.setTransportId(Integer.getInteger(item[1]));
                transportExtra.setExtraId(Integer.getInteger(item[2]));

                if(item[0] != "null") {
                    transportExtra.setId(Integer.getInteger(item[0]));
                } else {
                    transportExtra.setId(this.getIdFromBD("transportextra"));
                }
            } catch(Exception e) {
                throw new Exception(e);
            } finally {
                if (in != null)
                    in.close();
            }
        }

        return transportExtra;
    }

    @Override
    public boolean insert(String fileName) throws Exception {
        boolean result = false;
        TransportExtra transportExtra = null;

        if (fileName != null) {
            String sql="INSERT INTO transportExtra (id, transportId, extraId) VALUES(?, ? ,? )";
            PreparedStatement sentence;
            try {
                this.connect();
                transportExtra = this.readItemFromFile(fileName);

                sentence = connection.prepareStatement(sql);
                sentence.setInt(1, transportExtra.getId());
                sentence.setInt(2, transportExtra.getTransportId());
                sentence.setInt(3, transportExtra.getExtraId());

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
            String sql = "UPDATE transportExtra  SET transportId=?, extraId=? WHERE id = ?";
            try {
                TransportExtra transportExtra = this.readItemFromFile(fileName);
                this.connect();
                PreparedStatement sentence;
                sentence = connection.prepareStatement(sql);
                sentence.setInt(1, transportExtra.getTransportId());
                sentence.setInt(2, transportExtra.getExtraId());
                sentence.setInt(3, transportExtra.getId());

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
        String sql = "SELECT * FROM transportExtra";
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
                    out.write(resultSet.getInt("transportId"));
                    out.newLine();
                    out.write(resultSet.getInt("extraId"));

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
        String sql = "SELECT * FROM transportExtra WHERE id = ?";
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
                    out.write(resultSet.getInt("transportId"));
                    out.newLine();
                    out.write(resultSet.getInt("extraId"));
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
                String sql = "DELETE FROM transportExtra WHERE id = ?";
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


    public boolean removeFromExtra(Integer extraId) throws Exception {
        boolean result = false;
        PreparedStatement sentence = null;

        try {
            String sql = "DELETE FROM transportExtra WHERE extraId = ?";
            this.connect();

            sentence = connection.prepareStatement(sql);
            sentence.setInt(1, extraId);
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

        return result;

    }


    public boolean removeFromTransport(Integer transportId) throws Exception {
        boolean result = false;
        PreparedStatement sentence = null;

        try {
            String sql = "DELETE FROM transportExtra WHERE transportId = ?";
            this.connect();

            sentence = connection.prepareStatement(sql);
            sentence.setInt(1, transportId);
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

        return result;

    }



}

