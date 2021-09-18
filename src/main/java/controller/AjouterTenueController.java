package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import domain.uniform.Uniform;
import domain.uniform.UniformStock;
import infrastructure.persistence.UniformStockDao;
import infrastructure.persistence.dao.uniform.UniformMySqlDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AjouterTenueController implements Initializable {


	@FXML
	private TextField prixField;
	@FXML
	private JFXComboBox typeChoice;
	@FXML
	private JFXComboBox modelChoice;
	@FXML
	private JFXComboBox tailleChoice;
	@FXML
	private JFXTextField numberField;
	@FXML
	private Button submitButton;
	@FXML
	private Button cancelButton;

	private ObservableList<String> typeLists;
	private ObservableList<String> tailleLists;
	private ObservableList<String> modelLists;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeLists = FXCollections.observableArrayList();
		tailleLists = FXCollections.observableArrayList();
		modelLists = FXCollections.observableArrayList();

		typeLists.add("tenu scolaire");
		typeLists.add("tenue de sport");

		modelLists.add("tissus pantalon");
		modelLists.add("pagne chemise");
		modelLists.add("t-shirt simple");
		modelLists.add("lacoste");
		modelLists.add("survetement sans pantalon");
		modelLists.add("t-shirt");
		modelLists.add("survetement avec pantalon");

		modelChoice.setItems(modelLists);

		tailleLists.add("S");
		tailleLists.add("M");
		tailleLists.add("L");
		tailleLists.add("XL");
		tailleLists.add("XXL");
		tailleLists.add("XXXL");


		typeChoice.setItems(typeLists);
		tailleChoice.setItems(tailleLists);
	}

	@FXML
	public void ajouterTenue(ActionEvent event) throws SQLException{
		Window owner = submitButton.getScene().getWindow();

		String type = typeChoice.getValue().toString();
		String model  =modelChoice.getValue().toString();
		String taille = tailleChoice.getValue().toString();
		int nombre = Integer.parseInt(numberField.getText());
		float price = Float.parseFloat(prixField.getText());

		UniformStockDao uniformStockDao = new UniformStockDao();
		UniformStock uniformStock = new UniformStock(type,model,taille,price,nombre);
		uniformStockDao.save(uniformStock);

		UniformMySqlDao uniformMySqlDao = new UniformMySqlDao();
	}
}
