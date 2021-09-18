package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionTenueLaSalleServer extends Application {


	public void start(final Stage primaryStage) throws Exception {
		Parent root  = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
		primaryStage.setTitle("Gestion des tenues");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}
