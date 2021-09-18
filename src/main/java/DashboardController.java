import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

	@FXML
	private JFXButton btn_ajouter;
	@FXML
	private JFXButton btn_summary;
	@FXML
	private JFXButton btn_ledger;
	@FXML
	private JFXButton btn_list;
	@FXML
	private JFXButton btn_pay;
	@FXML
	private Pane show_pane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

	@FXML
	private void handleButton(ActionEvent event) throws IOException {
		if (event.getSource() == btn_ajouter){

			populatePane("/fxml/AjouterTenue.fxml");
		} else if (event.getSource() == btn_list){
			populatePane("/fxml/AfficherTenue.fxml");
		}else if (event.getSource() == btn_pay){
			populatePane("/fxml/UniformPaymentTab.fxml");
		}else if (event.getSource() == btn_summary){
			populatePane("/fxml/Sommaire.fxml");
		}else if (event.getSource() == btn_ledger){
			populatePane("/fxml/Ledger.fxml");
		}


	}

	private void populatePane(String location) throws IOException {
		show_pane.getChildren().clear();
		Node node = FXMLLoader.load(getClass().getResource(location));
		show_pane.getChildren().add(node);
	}


}
