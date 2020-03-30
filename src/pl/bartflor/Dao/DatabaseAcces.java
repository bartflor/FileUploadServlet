package pl.bartflor.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAcces {
	Connection connection;
	private ResultSet result;
	
	public DatabaseAcces(Connection connection) {
		this.connection = connection;
	}
	
	public ResultSet getQuerryResult(String sqlQuerry) {
		try {
			Statement dbStatement = connection.createStatement();
			result = dbStatement.executeQuery(sqlQuerry);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	public ResultSet getQuerryResult(String sqlPrepareStatmentQuerry, String ... querryParam) {
		try {
			int numberOfParams = sqlPrepareStatmentQuerry.length() - sqlPrepareStatmentQuerry.replace("?", "").length();
			PreparedStatement preparedQuerry = connection.prepareStatement(sqlPrepareStatmentQuerry);
			for(int i=1; i<=numberOfParams; i++) {
				preparedQuerry.setString(i, querryParam[i-1]);
				//TODO: handle out of querryParam array exceptions
			}
			result = preparedQuerry.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public boolean executeStatement(String sqlPrepareStatmentQuerry, String ... querryParam) {
		try {
			int numberOfParams = sqlPrepareStatmentQuerry.length() - sqlPrepareStatmentQuerry.replace("?", "").length();
			PreparedStatement preparedQuerry = connection.prepareStatement(sqlPrepareStatmentQuerry);
			for(int i=1; i<=numberOfParams; i++) {
				preparedQuerry.setString(i, querryParam[i-1]);
				//TODO: handle out of querryParam array exceptions
			}
			preparedQuerry.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
