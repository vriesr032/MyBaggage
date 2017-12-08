package com.mybaggage.old.osman;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import java.sql.PreparedStatement;


public class RapController implements Initializable {

    private DatabaseConnection databaseConnection;

    @FXML
    private BarChart<Double, Double> barChart;
    @FXML
    private Button btnLoad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadChart();
    }

    @FXML
    private void loadChart() {
        
        DatabaseConnection conn = new DatabaseConnection();
        try{

            Connection myConn = conn.getConnection();
            String query = "SELECT formuliernummer,klantnummer FROM registratie";		
            PreparedStatement pst;
            pst = myConn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            XYChart.Series<Double, Double> series;
            series = new XYChart.Series<>();

            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getDouble(1), rs.getDouble(2)));
            }
            barChart.getData().add(series);
        } catch (ClassNotFoundException | SQLException e) {

        }
    }
}