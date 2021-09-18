package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {


	@FXML
	private Button ajouterTenue;
	@FXML
	private Button afficherTenue;
	@FXML
	private Button payerTenue;

	@FXML
	public void switchToScene(ActionEvent event){
		if (event.getSource() == ajouterTenue){
			loadStage("/fxml/AjouterTenue.fxml");
		}else if (event.getSource() == afficherTenue){
			loadStage("/fxml/AfficherTenue.fxml");
		}else if (event.getSource() == payerTenue){
			loadStage("/fxml/PayerTenue.fxml");
		}
	}

	private void loadStage(String fxml) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fxml));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
