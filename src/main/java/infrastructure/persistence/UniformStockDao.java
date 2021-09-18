package infrastructure.persistence;

import com.sun.deploy.trace.LoggerTraceListener;
import domain.uniform.UniformStock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UniformStockDao {


	private static  final String DATABASE_URL = "jdbc:mysql://localhost:3306/gestion_tenue?allowPublicKeyRetrieval=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&useSSL=false&serverTimezone=UTC";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "ZACKryder45";
	private static final String INSERT_QUERY = "INSERT INTO uniform_stock (type_tenue,model,taille,prix,stock) VALUES (?,?,?,?,?)";

	public void save(UniformStock uniformStock) {

		try{
			Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1,uniformStock.getType());
			preparedStatement.setString(2,uniformStock.getModel());
			preparedStatement.setString(3,uniformStock.getTaille());
			preparedStatement.setFloat(4,uniformStock.getPrix());
			preparedStatement.setInt(5,uniformStock.getNombre());

			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();

		}catch (SQLException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}

	}
}
