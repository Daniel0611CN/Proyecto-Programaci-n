package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.viajes.BBDD.Persistencia.Models.Travel;
import org.viajes.BBDD.Persistencia.Interfaces.IDAO;

public class TravelDao extends BasicDao implements IDAO<Travel, Integer> {

	public TravelDao() {
		super();
	}
	
	@Override
	public boolean createTable(String fileName) throws Exception {
		boolean result = false;
		try {
			result = super.createTable(fileName != null ? fileName : "org/viajes/BBDD/bdFiles/travelTable.txt");
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return result;	
	}


	private Travel readItemFromFile(String fileName) throws Exception {
		Travel travel = new Travel();
		BufferedReader in = null;

		if (fileName != null) {
			try {
				int cont = 0;
				String[] itemTravel = new String[9];
				in = new BufferedReader(new FileReader(fileName));
				String line = in.readLine();
				
				while(line != null) {
					itemTravel[cont] = line;
					line = in.readLine();
					cont++;
				}
				
				travel.setArrivalDate(itemTravel[1]);
				travel.setDepartureDate(itemTravel[2]);
				travel.setNumberPassengers(Integer.getInteger(itemTravel[3]));
				travel.setUserId(Integer.getInteger(itemTravel[4]));
				travel.setPrice(Double.valueOf(itemTravel[5]));
				travel.setCategoryId(Integer.getInteger(itemTravel[6]));
				travel.setArrivalLocationId(Integer.getInteger(itemTravel[7]));
				travel.setDepartureLocationId(Integer.getInteger(itemTravel[8]));
			
				if(itemTravel[0] != "null") {
					travel.setId(Integer.getInteger(itemTravel[0]));	
				} else {
					travel.setId(this.getIdFromBD("travel"));
				}
				
			} catch(Exception e) {
				throw new Exception(e);
			} finally {
				if (in != null)
					in.close();
			}
		}
		
		return travel;
	}
	
	@Override
	public boolean insertOne(String fileName) throws Exception {
		boolean result = false;
		Travel travel = null;
		
		if (fileName != null) {
			try {
				this.connect();
				PreparedStatement sentence;
				String sql="INSERT INTO travel (id, arrivalDate, departureDate, numberPassengers, userId, price, categoryId, arrivalLocationId, departureLocationId) VALUES(? ,?, ?, ?, ?, ?, ?, ?, ?)";
				
				travel = this.readItemFromFile(fileName);
				
				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, travel.getId());
				sentence.setString(2, travel.getArrivalDate());
				sentence.setString(3, travel.getDepartureDate());
				sentence.setDouble(4, travel.getPrice());
				sentence.setInt(5, travel.getCategoryId());
				sentence.setInt(6, travel.getArrivalLocationId());
				sentence.setInt(7, travel.getDepartureLocationId());
				sentence.setInt(8, travel.getNumberPassengers());
				sentence.setInt(9, travel.getUserId());

				sentence.executeUpdate();
				result = true;
			} catch (SQLException e) {
				throw new Exception(e);
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
	public boolean update(String fileName) throws Exception {
		boolean result = false;
		if (fileName != null) {
			String sql = "UPDATE travel " + "SET arrivalDate=?, departureDate=?, priceId=?, categoryId=?, arrivalLocationId=?, departureLocationId=?, numberPassengers=?, userId=?" + "WHERE id = ?";
			try {
					Travel travel = this.readItemFromFile(fileName);
					this.connect();
					PreparedStatement sentence;
					sentence = connection.prepareStatement(sql);
					sentence.setString(1, travel.getArrivalDate());
					sentence.setString(2, travel.getDepartureDate());
					sentence.setDouble(3, travel.getPrice());
					sentence.setInt(4, travel.getCategoryId());
					sentence.setInt(5, travel.getArrivalLocationId());
					sentence.setInt(6, travel.getDepartureLocationId());
					sentence.setInt(7, travel.getNumberPassengers());
					sentence.setInt(8, travel.getUserId());
					sentence.setInt(9, travel.getId());

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
	    String sql = "SELECT * FROM travel";
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
		        	out.write(resultSet.getString("arrivalDate"));
					out.newLine();
		        	out.write(resultSet.getString("departureDate"));
					out.newLine();
		        	out.write(String.valueOf(resultSet.getDouble("price")));
					out.newLine();
		        	out.write(resultSet.getInt("categoryId"));
					out.newLine();
		        	out.write(resultSet.getInt("arrivalLocationId"));
					out.newLine();
		        	out.write(resultSet.getInt("departureLocationId"));
					out.newLine();
		        	out.write(resultSet.getInt("numberPassengers"));
					out.newLine();
		        	out.write(resultSet.getInt("userId"));
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
	    String sql = "SELECT * FROM travel WHERE id = ?";
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
		        	out.write(resultSet.getString("arrivalDate"));
					out.newLine();
		        	out.write(resultSet.getString("departureDate"));
					out.newLine();
		        	out.write(String.valueOf(resultSet.getDouble("price")));
					out.newLine();
		        	out.write(resultSet.getInt("categoryId"));
					out.newLine();
		        	out.write(resultSet.getInt("arrivalLocationId"));
					out.newLine();
		        	out.write(resultSet.getInt("departureLocationId"));
					out.newLine();
		        	out.write(resultSet.getInt("numberPassengers"));
					out.newLine();
		        	out.write(resultSet.getInt("userId"));	
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
		String sql = "DELETE FROM travel WHERE id = ?";
		PreparedStatement sentence = null;

		if (fileName != null) {
			try {
				TravelExtraDao travelExtraDao = new TravelExtraDao();
				int id = this.readIdFromFile(fileName);
			    this.connect();
			   
			    sentence = connection.prepareStatement(sql);
			    sentence.setInt(1, id);
			    int rowsAffected = sentence.executeUpdate();
			    if (rowsAffected > 0) {
			        result = true && travelExtraDao.removeFromTravel(id);
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
