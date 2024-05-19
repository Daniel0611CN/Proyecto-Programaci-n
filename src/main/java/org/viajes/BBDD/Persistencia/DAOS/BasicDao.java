package org.viajes.BBDD.Persistencia.DAOS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicDao {
	private String userName = null;
	private String password = null;
	private String dataBaseName = null;
	private String url = "jdbc:mysql://localhost:3306/";
	protected Connection connection = null;
	
	public BasicDao() {
		super();
		this.initLoginData();
		this.createDataBase();
	}
	
	public void connect() throws Exception {
		String myUrl = url + dataBaseName;
		try {
			connection = DriverManager.getConnection(myUrl, userName, password);
		}catch(SQLException e) {
			throw new Exception(e.getMessage());
		}		
	}
	
	private void createDataBase() {
		 try {
			 connection = DriverManager.getConnection(url, userName, password);
			 Statement statement = connection.createStatement();
			 String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + dataBaseName;
			 statement.executeUpdate(createDatabaseSQL);
			 this.closeConnection();
        } catch (SQLException e) {
        	e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() throws Exception {
		try {
			connection.close();
		} catch(SQLException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void initLoginData() {
		String linea = null;
		BufferedReader in = null;
		try {
			in = new BufferedReader (new FileReader("C:\\Users\\danic\\IdeaProjects\\Proyecto\\src\\main\\java\\org\\viajes\\BBDD\\bdFiles\\login.txt"));
			linea = in.readLine(); 
			if(linea != null)
				this.userName = linea;
			linea = in.readLine();
			if(linea != null)
				this.password = linea;
			linea = in.readLine();
			this.dataBaseName = linea != null ? linea : "travelData";

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean createTable(String fileName) throws Exception {
		boolean result = false;
		String line = null;
		BufferedReader in = null;
		String query = "";

		if (fileName != null) {
			try {
				in = new BufferedReader (new FileReader(fileName)); 
				line = in.readLine(); 
				
				while(line!=null) {
					query += line;
				}
				
				if(query.length() > 0) {
					PreparedStatement sentence = connection.prepareStatement(query);
					
					result = sentence.execute();
				}
	
			} catch (FileNotFoundException e) {
				e.getMessage();
			} catch (IOException e) {
				e.getMessage();
			} finally {
				try {
					if(in != null)
						in.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}

		return result;	
	}
	
	public Integer readIdFromFile(String fileName) throws Exception {
		Integer id = null;
		BufferedReader in = null;

		if (fileName != null) {
			try {
				in = new BufferedReader(new FileReader(fileName));
				String line = in.readLine();
				
				if (line != null)
					id = Integer.getInteger(line);		
			} catch(Exception e) {
				throw new Exception(e);
			} finally {
				if (in != null)
					in.close();
			}
		}
		
		return id;
	}
	
	public Integer getIdFromBD(String tableName) throws Exception {
		this.connect();
		String sql="Select MAX(id) as maxId from "+ tableName;
		Statement query;
		int result=0;
		try {
			query = this.connection.createStatement();
			ResultSet rs = query.executeQuery(sql);
			while (rs.next()) {
				result=rs.getInt("maxId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}

		return result;
	}
}
