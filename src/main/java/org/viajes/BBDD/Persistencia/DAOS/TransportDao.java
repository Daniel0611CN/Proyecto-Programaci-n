package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.viajes.BBDD.Persistencia.Models.Transport;
import org.viajes.BBDD.Persistencia.Interfaces.IDAO;

public class TransportDao extends BasicDao implements IDAO<Transport, Integer> {

	public TransportDao() {
		super();
	}
	
	@Override
	public boolean createTable(String fileName) throws Exception {
		boolean result = false;
		try {
			result = super.createTable(fileName != null ? fileName : "org/viajes/BBDD/bdFiles/transportTable.txt");
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return result;
	}


	private Transport readItemFromFile(String fileName) throws Exception {
		Transport transport = new Transport();
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
				
				transport.setName(item[1]);
			
				if(item[0] != "null") {
					transport.setId(Integer.getInteger(item[0]));	
				} else {
					transport.setId(this.getIdFromBD("transport"));
				}
			} catch(Exception e) {
				throw new Exception(e);
			} finally {
				if (in != null)
					in.close();
			}
		}
		
		return transport;
	}

	@Override
	public boolean insertOne(String fileName) throws Exception {
		boolean result = false;
		Transport transport = null;
		
		if (fileName != null) {		
			String sql="INSERT INTO transport (id, name) VALUES(? ,? )";
			PreparedStatement sentence;
			try {
				this.connect();
				transport = this.readItemFromFile(fileName);
				
				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, transport.getId());
				sentence.setString(2, transport.getName());
	
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
			String sql = "UPDATE transport  SET name=? WHERE id = ?";
			try {
				Transport transport = this.readItemFromFile(fileName);
				this.connect();
				PreparedStatement sentence;
				sentence = connection.prepareStatement(sql);
				sentence.setString(1, transport.getName());
				sentence.setInt(2, transport.getId());

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
	    String sql = "SELECT * FROM transport";
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
	    String sql = "SELECT * FROM transport WHERE id = ?";
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
		TransportExtraDao transportExtraDao = new TransportExtraDao();
		TransportCategoryDao transportCategoryDao = new TransportCategoryDao();

		if (fileName != null) {
			try {
				String sql = "DELETE FROM transport WHERE id = ?";
				int id = this.readIdFromFile(fileName);
			    this.connect();
			   
			    sentence = connection.prepareStatement(sql);
			    sentence.setInt(1, id);
			    int rowsAffected = sentence.executeUpdate();
			    
			    if (rowsAffected > 0) {
			        result = true && transportExtraDao.removeFromTransport(id) && transportCategoryDao.removeFromTransport(id);
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
