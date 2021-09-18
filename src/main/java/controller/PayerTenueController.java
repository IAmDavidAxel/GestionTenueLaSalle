package controller;

import domain.transaction.Transaction;
import infrastructure.persistence.TransactionDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import utils.ConnectionUtils;
import utils.ReceiptMaker;
import utils.TransactionReceiptMaker;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayerTenueController implements Initializable {

	@FXML
	private ComboBox tailleBox;
	@FXML
	private TextField modelField;
	@FXML
	private ComboBox typeBox;
	@FXML
	private TextField numberField;

	@FXML
	private TextField matriculeField;
	@FXML
	private Button submitButton;
	@FXML
	private Button cancelButton;

	private String SQL = "select matricule from eleve";
	private ResultSet resultSet;
	private Connection  connection;
	private List<String> matriculeList;
	private ObservableList typeList;
	private List<String> modelList;
	private TransactionDao transactionDao;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connection = (Connection) ConnectionUtils.connectToDb();
		transactionDao = new TransactionDao();
		autocompleteMatricule();
		autoCompleteType();
		autoCompleteModele();
		autoCompleteTaille();
	}

	private void autoCompleteTaille() {
		try{
			resultSet = connection.createStatement().executeQuery("select distinct taille from tenue");

			while (resultSet.next()){
				tailleBox.getItems().addAll(resultSet.getString(1));
			}

		}catch (SQLException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}
	}


	private void autoCompleteModele() {
		modelList = new ArrayList<>();
		try {
			resultSet = connection.createStatement().executeQuery("select distinct sous_type_tenue from tenue");

			while (resultSet.next()){
				String model = resultSet.getString(1);
				modelList.add(model);
			}
			TextFields.bindAutoCompletion(modelField,modelList);
		}catch (SQLException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}
	}

	private void autoCompleteType() {

		try {
			resultSet = connection.createStatement().executeQuery("select distinct type_tenue from tenue");

			while (resultSet.next()){
				typeBox.getItems().addAll(resultSet.getString(1));
			}

		}catch (SQLException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}
	}

	public void autocompleteMatricule() {
		matriculeList = new ArrayList<>();

		try{
			resultSet = connection.createStatement().executeQuery(SQL);

			while (resultSet.next()){
				String matricule =resultSet.getString(1);
				 matriculeList.add(matricule);
			}
			TextFields.bindAutoCompletion(matriculeField,matriculeList);

		}catch (SQLException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}
	}

	@FXML
	private void payerTenue() {
		Transaction transaction = new Transaction();
		String type = typeBox.getValue().toString();
		String model = modelField.getText();
		String matriculeEleve = matriculeField.getText();
		String taille = tailleBox.getValue().toString();

		transaction.setType(type);
		transaction.setModelTenue(model);
		transaction.setTaille(taille);
		float nombreTenueAcheter = Float.parseFloat(numberField.getText());
		try {
			String findStudentQuery = "select nom,prenom from eleve where matricule=?";
			PreparedStatement preparedStatement = connection.prepareStatement(findStudentQuery);
			preparedStatement.setString(1,matriculeEleve);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				String studentFirstName = resultSet.getString(2);
				String studentLastName = resultSet.getString(1);
				transaction.setStudentFirstName(studentFirstName);
				transaction.setStudentLastName(studentLastName);
			}


			String findUniformPrice = "select prix from tenue where type_tenue=?";
			PreparedStatement priceStatement = connection.prepareStatement(findUniformPrice);
			priceStatement.setString(1,type);
			resultSet = priceStatement.executeQuery();

			while (resultSet.next()){
				float prixUnitaire = resultSet.getFloat(1);
				float prixGloble = nombreTenueAcheter * prixUnitaire;
				transaction.setTransactionAmount(prixGloble);
			}

		} catch (SQLException exception) {
			Logger.getGlobal().log(Level.SEVERE, exception.getMessage());
		}

		TransactionReceiptMaker transactionReceiptMaker = new TransactionReceiptMaker();
		transactionDao.save(transaction);
		transactionReceiptMaker.createPdf(matriculeEleve, transaction.getStudentLastName(), transaction.getStudentFirstName(),taille, (int) nombreTenueAcheter,model,type, transaction.getTransactionAmount());
		ReceiptMaker receiptMaker = new ReceiptMaker();
		receiptMaker.createPdf();

		try {
			for (int i = 0; i <= nombreTenueAcheter; i++) {
				connection.createStatement().executeUpdate("delete from tenue where type_tenue="+type+"& where model="+model+"& where taille="+taille);
			}
		} catch (SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage());
		}
	}
	}

