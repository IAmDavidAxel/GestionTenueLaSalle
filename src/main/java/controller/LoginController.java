package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

	@FXML
	private TextField identifiantField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button submitButton;

	@FXML
	public void login(ActionEvent actionEvent) throws SQLException{
		Window owner = submitButton.getScene().getWindow();

		System.out.println(identifiantField.getText());
		System.out.println(passwordField.getText());

		if (identifiantField.getText().isEmpty()){
			showAlert(Alert.AlertType.ERROR,owner,"From Error", "Entrez votre Identifiant s'il vous plait");
		}

		if (passwordField.getText().isEmpty()){
			showAlert(Alert.AlertType.ERROR,owner,"From Error", "Veuillez entré votre mot de passe");
		}

		String identifiant = identifiantField.getText();
		String motDePasse = passwordField.getText();

		showAlert(Alert.AlertType.CONFIRMATION,owner,"Authentifer avec succes", "Bienvenue"+identifiantField.getText());

		loadStage();
	}

	private void loadStage() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title,String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

}
