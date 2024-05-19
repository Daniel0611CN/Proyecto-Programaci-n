package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.viajes.BBDD.Persistencia.Models.Price;
import org.viajes.BBDD.Persistencia.Interfaces.IDAO;

public class PriceDao extends BasicDao implements IDAO<Price, Integer> {

	public PriceDao() {
		super();
	}
	
	@Override
	public boolean createTable(String fileName) throws Exception {
		boolean result = false;
		try {
			result = super.createTable(fileName != null ? fileName : "org/viajes/BBDD/bdFiles/priceTable.txt");
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return result;
	}


	private Price readItemFromFile(String fileName) throws Exception {
		Price price = new Price();
		BufferedReader in = null;

		if (fileName != null) {
			try {
				int cont = 0;
				String[] item = new String[6];
				in = new BufferedReader(new FileReader(fileName));
				String line = in.readLine();
				
				while(line != null) {
					item[cont] = line;
					line = in.readLine();
					cont++;
				}
				
				price.setDepartureLocationId(Integer.getInteger(item[1]));
				price.setArrivalLocationId(Integer.getInteger(item[2]));
				price.setPlanePrice(Double.valueOf(item[3]));
				price.setTrainPrice(Double.valueOf(item[4]));
				price.setBoatPrice(Double.valueOf(item[5]));
			
				if(item[0] != "null") {
					price.setId(Integer.getInteger(item[0]));	
				} else {
					price.setId(this.getIdFromBD("price"));
				}
			} catch(Exception e) {
				throw new Exception(e);
			} finally {
				if (in != null)
					in.close();
			}
		}
		
		return price;
	}

	@Override
	public boolean insertOne(String fileName) throws Exception {
		boolean result = false;
		Price price = null;
		
		if (fileName != null) {		
			String sql="INSERT INTO price VALUES(?, ? ,?, ?, ?, ? )";
			PreparedStatement sentence;
			try {
				this.connect();
				price = this.readItemFromFile(fileName);

				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, price.getId());
				sentence.setInt(2, price.getDepartureLocationId());
				sentence.setInt(3, price.getArrivalLocationId());
				sentence.setDouble(4, price.getPlanePrice());
				sentence.setDouble(5, price.getBoatPrice());
				sentence.setDouble(6, price.getTrainPrice());
	
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
			String sql = "UPDATE price SET sourceLocationId=?, destinationLocationId=?, planePrice=?, boatPrice=?, trainPrice=? WHERE id = ?";
			try {
				Price price = this.readItemFromFile(fileName);
				this.connect();
				PreparedStatement sentence;
				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, price.getDepartureLocationId());
				sentence.setInt(2, price.getArrivalLocationId());
				sentence.setDouble(3, price.getPlanePrice());
				sentence.setDouble(4, price.getBoatPrice());
				sentence.setDouble(5, price.getTrainPrice());
				sentence.setInt(6, price.getId());

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
	    String sql = "SELECT * FROM price";
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
		        	out.write(resultSet.getInt("departureLocationId"));
					out.newLine();
		        	out.write(resultSet.getInt("arrivalLocationId"));
					out.newLine();
					out.write(String.valueOf(resultSet.getDouble("planePrice")));
					out.newLine();
					out.write(String.valueOf(resultSet.getDouble("trainPrice")));
					out.newLine();
					out.write(String.valueOf(resultSet.getDouble("boatPrice")));
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
	    String sql = "SELECT * FROM price WHERE id = ?";
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
		        	out.write(resultSet.getInt("departureLocationId"));
					out.newLine();
		        	out.write(resultSet.getInt("arrivalLocationId"));
					out.newLine();
					out.write(String.valueOf(resultSet.getDouble("planePrice")));
					out.newLine();
					out.write(String.valueOf(resultSet.getDouble("trainPrice")));
					out.newLine();
					out.write(String.valueOf(resultSet.getDouble("boatPrice")));
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
				String sql = "DELETE FROM price WHERE id = ?";
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
