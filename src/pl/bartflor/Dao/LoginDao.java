package pl.bartflor.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;


public class LoginDao {
	private DaoFactory daoFactory;
	private DatabaseAcces dbAcces;
	
	public LoginDao(ServletContext sContext) {
		this.daoFactory = new DaoFactory(sContext);
		this.dbAcces = daoFactory.createDaoObject();
	}
	
	public boolean validate(User user){
		String sqlQuerry = "SELECT user_name, password FROM login_tlb WHERE user_name=? AND password=?";
		ResultSet querryResult = dbAcces.getQuerryResult(sqlQuerry, user.getName(), user.getPassword());
		try {
			return querryResult.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			dbAcces.close();;
		}
	}
	public boolean setNew(User newUser) {
		String sqlQuerry = "SELECT user_name FROM login_tlb WHERE user_name=?";
		ResultSet querryResult = dbAcces.getQuerryResult(sqlQuerry, newUser.getName());
		try {
			if(querryResult.next() == false) {
				String sqlInsert = "INSERT INTO login_tlb(user_name, password) VALUES (?, ?)";
				dbAcces.executeStatement(sqlInsert, newUser.getName(), newUser.getPassword());
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			dbAcces.close();
		}
		return true;
	}
}
