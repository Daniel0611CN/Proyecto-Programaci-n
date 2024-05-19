package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistance.interfaces.IDAO;
import persistance.models.TravelExtra;

public class TravelExtraDao extends BasicDao implements IDAO<TravelExtra, Integer> {

	public TravelExtraDao() {
		super();
	}
	
	@Override
	public boolean createTable(String file) throws Exception {
		boolean result = false;
		try {
			result = super.createTable(file != null ? file : "org/viajes/BBDD/bdFiles/travelExtraTable.txt");
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return result;
	}
	
	
	private TravelExtra readItemFromFile(String fileName) throws Exception {
		TravelExtra travelExtra = new TravelExtra();
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
				
				travelExtra.setExtraId(Integer.getInteger(item[1]));
				travelExtra.setTravelId(Integer.getInteger(item[2]));
			
				if(item[0] != "null") {
					travelExtra.setId(Integer.getInteger(item[0]));	
				} else {
					travelExtra.setId(this.getIdFromBD("travelextra"));
				}
			} catch(Exception e) {
				throw new Exception(e);
			} finally {
				if (in != null)
					in.close();
			}
		}
		
		return travelExtra;
	}

	@Override
	public boolean insertOne(String fileName) throws Exception {
		boolean result = false;
		TravelExtra travelExtra = null;
		
		if (fileName != null) {		
			String sql="INSERT INTO travelextra (id, extraId, travelId) VALUES(?, ? ,? )";
			PreparedStatement sentence;
			try {
				this.connect();
				travelExtra = this.readItemFromFile(fileName);
				
				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, travelExtra.getId());
				sentence.setInt(2, travelExtra.getExtraId());
				sentence.setInt(3, travelExtra.getTravelId());
	
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
			String sql = "UPDATE travelextra  SET extraId=?, travelId=? WHERE id = ?";
			try {
				TravelExtra travelExtra = this.readItemFromFile(fileName);
				this.connect();
				PreparedStatement sentence;
				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, travelExtra.getExtraId());
				sentence.setInt(2, travelExtra.getTravelId());
				sentence.setInt(3, travelExtra.getId());

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
	    String sql = "SELECT * FROM travelextra";
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
					out.write(resultSet.getInt("extraId"));
					out.newLine();
					out.write(resultSet.getInt("travelId"));
		
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
	    String sql = "SELECT * FROM travelextra WHERE id = ?";
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
					out.write(resultSet.getInt("extraId"));
					out.newLine();
					out.write(resultSet.getInt("travelId"));
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
				String sql = "DELETE FROM travelextra WHERE id = ?";
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
			String sql = "DELETE FROM travelextra WHERE extraId = ?";
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


	public boolean removeFromTravel(Integer travelId) throws Exception {
		boolean result = false;
		PreparedStatement sentence = null;
		
		try {
			String sql = "DELETE FROM travelextra WHERE travelId = ?";
		    this.connect();
		   
		    sentence = connection.prepareStatement(sql);
		    sentence.setInt(1, travelId);
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
