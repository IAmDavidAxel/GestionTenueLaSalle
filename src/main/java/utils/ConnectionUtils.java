package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtils {

	Connection  connection = null;

	public static  Connection connectToDb(){

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_tenue?allowPublicKeyRetrieval=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&useSSL=false&serverTimezone=UTC","root","ZACKryder45");
			return connection;
		}catch (ClassNotFoundException | SQLException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
			return null;
		}
	}
}
