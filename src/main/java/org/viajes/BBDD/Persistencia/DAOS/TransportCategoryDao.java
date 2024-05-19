package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistance.interfaces.IDAO;
import persistance.models.TransportCategory;

public class TransportCategoryDao extends BasicDao implements IDAO<TransportCategory, Integer> {

	public TransportCategoryDao() {
		super();
	}
	
	@Override
	public boolean createTable(String file) throws Exception {
		boolean result = false;
		try {
			result = super.createTable(file != null ? file : "org/viajes/BBDD/bdFiles/transportCategoryTable.txt");
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return result;
	}
	
	
	private TransportCategory readItemFromFile(String fileName) throws Exception {
		TransportCategory transportCategory = new TransportCategory();
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

				transportCategory.setTransportId(Integer.getInteger(item[1]));
				transportCategory.setCategoryId(Integer.getInteger(item[2]));
			
				if(item[0] != "null") {
					transportCategory.setId(Integer.getInteger(item[0]));	
				} else {
					transportCategory.setId(this.getIdFromBD("transportcategory"));
				}
			} catch(Exception e) {
				throw new Exception(e);
			} finally {
				if (in != null)
					in.close();
			}
		}
		
		return transportCategory;
	}

	@Override
	public boolean insertOne(String fileName) throws Exception {
		boolean result = false;
		TransportCategory transportCategory = null;
		
		if (fileName != null) {		
			String sql="INSERT INTO transportcategory (id, transportId, categoryId) VALUES(?, ? ,? )";
			PreparedStatement sentence;
			try {
				this.connect();
				transportCategory = this.readItemFromFile(fileName);
				
				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, transportCategory.getId());
				sentence.setInt(2, transportCategory.getTransportId());
				sentence.setInt(3, transportCategory.getCategoryId());
	
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
			String sql = "UPDATE transportcategory SET transportId=?, categoryId=? WHERE id = ?";
			try {
				TransportCategory transportCategory = this.readItemFromFile(fileName);
				this.connect();
				PreparedStatement sentence;
				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, transportCategory.getTransportId());
				sentence.setInt(2, transportCategory.getCategoryId());
				sentence.setInt(3, transportCategory.getId());

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
	    String sql = "SELECT * FROM transportcategory";
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
					out.write(resultSet.getInt("categoryId"));
		
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
	    String sql = "SELECT * FROM transportcategory WHERE id = ?";
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
					out.write(resultSet.getInt("categoryId"));
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
				String sql = "DELETE FROM transportcategory WHERE id = ?";
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


	public boolean removeFromCategory(Integer categoryId) throws Exception {
		boolean result = false;
		PreparedStatement sentence = null;
		
		try {
			String sql = "DELETE FROM transportcategory WHERE categoryId = ?";
		    this.connect();
		   
		    sentence = connection.prepareStatement(sql);
		    sentence.setInt(1, categoryId);
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
			String sql = "DELETE FROM transportcategory WHERE transportId = ?";
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
