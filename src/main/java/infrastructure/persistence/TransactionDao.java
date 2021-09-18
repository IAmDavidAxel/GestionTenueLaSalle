package infrastructure.persistence;

import domain.transaction.Transaction;
import utils.ConnectionUtils;

import java.sql.*;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionDao {

	private static  final String DATABASE_URL = "jdbc:mysql://localhost:3306/gestion_tenue?allowPublicKeyRetrieval=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&useSSL=false&serverTimezone=UTC";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "ZACKryder45";

	private String INSERT_QUERY = "INSERT INTO transaction_tenue(date_transaction,montant_transaction,nom_eleve,prenom_eleve,type_tenue,model,taille) VALUES (?,?,?,?,?,?,?)";

	public void save(Transaction transaction){
		try{
			Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

			preparedStatement.setString(1, transaction.getCreatedAt().toString());
			preparedStatement.setFloat(2,transaction.getTransactionAmount());
			preparedStatement.setString(3,transaction.getStudentLastName());
			preparedStatement.setString(4, transaction.getStudentFirstName());
			preparedStatement.setString(5,transaction.getType());
			preparedStatement.setString(6,transaction.getModelTenue());
			preparedStatement.setString(7,transaction.getTaille());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

		}catch (SQLException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}
	}
}
