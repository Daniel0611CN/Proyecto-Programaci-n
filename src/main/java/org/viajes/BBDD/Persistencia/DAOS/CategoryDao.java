package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.viajes.BBDD.Persistencia.Models.Category;
import org.viajes.BBDD.Persistencia.Interfaces.IDAO;

public class CategoryDao extends BasicDao implements IDAO<Category, Integer> {

	public CategoryDao() {
		super();
	}
	
	@Override
	public boolean createTable(String file) throws Exception {
		boolean result = false;
		try {
			result = super.createTable(file != null ? file : "org/viajes/BBDD/bdFiles/categoryTable.txt");
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return result;
	}


	private Category readItemFromFile(String fileName) throws Exception {
		Category category = new Category();
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
				
				category.setName(item[1]);
				category.setIncrement(Double.valueOf(item[2]));
			
				if(item[0] != "null") {
					category.setId(Integer.getInteger(item[0]));	
				} else {
					category.setId(this.getIdFromBD("category"));
				}
			} catch(Exception e) {
				throw new Exception(e);
			} finally {
				if (in != null)
					in.close();
			}
		}
		
		return category;
	}

	@Override
	public boolean insertOne(String fileName) throws Exception {
		boolean result = false;
		Category category = null;
		
		if (fileName != null) {		
			String sql="INSERT INTO category (id, name, increment) VALUES(?, ? ,? )";
			PreparedStatement sentence;
			try {
				this.connect();
				category = this.readItemFromFile(fileName);
				
				sentence = connection.prepareStatement(sql);
				sentence.setInt(1, category.getId());
				sentence.setString(2, category.getName());
				sentence.setDouble(3, category.getIncrement());
	
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
			String sql = "UPDATE category  SET name=?, increment=? WHERE id = ?";
			try {
				Category category = this.readItemFromFile(fileName);
				this.connect();
				PreparedStatement sentence;
				sentence = connection.prepareStatement(sql);
				sentence.setString(1, category.getName());
				sentence.setDouble(2, category.getIncrement());
				sentence.setInt(3, category.getId());

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
	    String sql = "SELECT * FROM category";
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
					out.write(String.valueOf(resultSet.getDouble("increment")));
		
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
	    String sql = "SELECT * FROM category WHERE id = ?";
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
					out.write(String.valueOf(resultSet.getDouble("increment")));
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
		TransportCategoryDao transportCategoryDao = new TransportCategoryDao();
		
		if (fileName != null) {
			try {
				String sql = "DELETE FROM category WHERE id = ?";
				int id = this.readIdFromFile(fileName);
			    this.connect();
			   
			    sentence = connection.prepareStatement(sql);
			    sentence.setInt(1, id);
			    int rowsAffected = sentence.executeUpdate();
			    
			    if (rowsAffected > 0) {
			        result = true && transportCategoryDao.removeFromCategory(id);
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
