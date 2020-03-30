package pl.bartflor.Dao;

import java.sql.Connection;

import javax.servlet.ServletContext;

public class DaoFactory {
	ServletContext sContext;
	
	public DaoFactory(ServletContext sContext) {
		this.sContext = sContext;
	}

	public DatabaseAcces createDaoObject() {
		ConnectionFactory connectionFactory = new ConnectionFactory(sContext.getInitParameter("url")+sContext.getInitParameter("database"), sContext.getInitParameter("user_name"), sContext.getInitParameter("password")); 
		Connection connection = connectionFactory.getConnection();
		return new DatabaseAcces(connection);
	}
}
