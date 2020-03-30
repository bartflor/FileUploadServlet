package pl.bartflor.Dao;
import java.sql.*;
import java.util.Properties;
public class ConnectionFactory {
	String url;
	String uname;
	String pass;
	Connection connection;
	
	Properties props = new Properties(); 
	
	public ConnectionFactory(String url, String userName, String password) {
		
		this.url = url;
		this.uname = userName;
		this.pass = password;
	}
	
	Connection getConnection(){
		try {
			System.out.println(".... getting connection");
			Class.forName("com.mysql.cj.jdbc.Driver");		
			this.connection = DriverManager.getConnection(url, uname, pass);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return this.connection;
	}
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
