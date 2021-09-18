package controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import utils.ConnectionUtils;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SommaireEtatController implements  Initializable {
	@FXML
	private BarChart<String, Double> barChart;

	private ResultSet resultSet;
	private String SQL  = "SELECT model, stock from uniform_stock group by model ";
	private Connection connection;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connection = (Connection) ConnectionUtils.connectToDb();
		loadChart();
	}

	private void loadChart() {
		try{
			XYChart.Series<String,Double> series = new XYChart.Series<>();

			resultSet = connection.createStatement().executeQuery(SQL);
			while(resultSet.next()){
				series.getData().add(new XYChart.Data<>(resultSet.getString(1),resultSet.getDouble(2)));
			}
			barChart.getData().add(series);


		}catch (SQLException e){
			Logger.getGlobal().log(Level.SEVERE,e.getMessage());
		}

	}

}
