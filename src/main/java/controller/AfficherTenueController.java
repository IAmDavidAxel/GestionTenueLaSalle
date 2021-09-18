package controller;

import domain.uniform.Uniform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import utils.ConnectionUtils;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AfficherTenueController implements Initializable {

	private ObservableList<ObservableList> data;

	@FXML
	private TableView tableData;
	private Connection connection;
	private String SQL = "SELECT * FROM uniform_stock";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		data = FXCollections.observableArrayList();
		connection = (Connection) ConnectionUtils.connectToDb();


		try {
			ResultSet resultSet = connection.createStatement().executeQuery(SQL);

			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn column = new TableColumn(resultSet.getMetaData().getColumnName(i + 1).toUpperCase());
				column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});

				tableData.getColumns().addAll(column);

				System.out.println("Column [" + i + "]");
			}

				while (resultSet.next()) {

					ObservableList row = FXCollections.observableArrayList();
					for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
						row.add(resultSet.getString(i));
					}
					System.out.println("Row [1]" + row);
					data.add(row);
				}
				tableData.setItems(data);

			}
		catch (SQLException exception) {
			Logger.getGlobal().log(Level.SEVERE, exception.getMessage());
		}
	}
}
