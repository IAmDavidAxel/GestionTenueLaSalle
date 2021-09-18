package infrastructure.persistence.dao.uniform;

import domain.uniform.Uniform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UniformMySqlDao {

	private static  final String DATABASE_URL = "jdbc:mysql://localhost:3306/gestion_tenue?allowPublicKeyRetrieval=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&useSSL=false&serverTimezone=UTC";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "ZACKryder45";
	private static final String INSERT_QUERY = "INSERT INTO tenue (type_tenue,sous_type_tenue,model,taille,prix) VALUES(?,?,?,?,?)";

	public void save(Uniform uniform) {

		try{
			Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1, uniform.getType());
			preparedStatement.setString(2, uniform.getSous_type());
			preparedStatement.setString(3, uniform.getModel());
			preparedStatement.setString(4, uniform.getSize());
			preparedStatement.setFloat(5,uniform.getPrice());

			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();

		}catch (SQLException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}
	}
}
