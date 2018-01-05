  package com.mybaggage.old.osman;

import com.mybaggage.Database;
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


    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private Button btnLoad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void loadChart() {
        
        barChart.getData().clear();
       
        try{

            Connection myConn = Database.connectdb();
            String query;      
            query = "SELECT vluchtnummer,labelnummer FROM registratie ";
            PreparedStatement pst;
            pst = myConn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            XYChart.Series<String, Double> series;
            series = new XYChart.Series<>();

            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            barChart.getData().add(series);

        } catch (SQLException e) {

        }
        
    }
}