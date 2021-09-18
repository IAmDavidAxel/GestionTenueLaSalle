package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UniformPaymentTabController  implements Initializable {


	@FXML
	private JFXButton paymentButton;
	@FXML
	private JFXButton specialPaymentButton;
	@FXML
	private Pane showPane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void hanbleButon(ActionEvent event) throws IOException {
		if (event.getSource() == paymentButton){
			populatePane("/fxml/PayerTenue.fxml");
		}else if(event.getSource() == specialPaymentButton){
			populatePane("/fxml/PaiementSpecial.fxml");
		}
	}

	private void populatePane(String loaction) throws IOException{
		showPane.getChildren().clear();
		Node node  = FXMLLoader.load(getClass().getResource(loaction));
		showPane.getChildren().add(node);
	}
}
