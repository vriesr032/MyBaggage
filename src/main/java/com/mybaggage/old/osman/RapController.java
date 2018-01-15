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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


public class RapController implements Initializable {
    int count3;
    int count21;
    int countNooit;
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private BarChart<String, Double> barChart1;
    @FXML
    private Button btnLoad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Connection myConn = Database.connectdb();
            String query3; 
            String query21;
//            String queryNooit;
            query3 = "SELECT * FROM registratie WHERE datum >= (CURDATE() - INTERVAL 3 DAY);";
            query21 = "SELECT * FROM registratie WHERE datum >= (CURDATE() - INTERVAL 21 DAY);";
//            queryNooit = "SELECT * FROM registratie WHERE status = 'nooit';";
            PreparedStatement pst3;
            PreparedStatement pst21;
            pst3 = myConn.prepareStatement(query3);
            ResultSet rs3 = pst3.executeQuery();
            
            pst21 = myConn.prepareStatement(query21);
            ResultSet rs21 = pst21.executeQuery();
            
//            pst = myConn.prepareStatement(queryNooit);
//            ResultSet rsNooit = pst.executeQuery();
            

            while (rs3.next()) {
                ++count3;
            }
            while (rs21.next()) {
                ++count21;
            }
//            while (rsNooit.next()) {
//                ++countNooit;
//            }
            
            ObservableList<PieChart.Data> pieChartData
               = FXCollections.observableArrayList(
                        new PieChart.Data("Binnen 3 dagen("+ count3 +")" , count3),
                        new PieChart.Data("Binnen 21 dagen("+ count21 +")" , count21));
//                        new PieChart.Data("nooit("+ countNooit +")" , countNooit));
        
        pieChart.setData(pieChartData);
        }
        catch (SQLException e) {

        }
    }


//    @FXML
//    private void loadChart() {
//        
//        barChart.getData().clear();
//       
//        try{
//
//            Connection myConn = Database.connectdb();
//            String query;      
//            query = "SELECT lostandfoundID ,formuliernummer FROM registratie WHERE datum >= (CURDATE() - INTERVAL 3 DAY);";
//            PreparedStatement pst;
//            pst = myConn.prepareStatement(query);
//            ResultSet rs = pst.executeQuery();
//
//            XYChart.Series<String, Double> series;
//            series = new XYChart.Series<>();
//            
//
//            while (rs.next()) {
//                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
//            }
//            barChart.getData().add(series);
//
//        } catch (SQLException e) {
//
//        }
//        
//    }
//    @FXML
//    private void loadChart21() {
//        
//        barChart.getData().clear();
//       
//        try{
//
//            Connection myConn = Database.connectdb();
//            String query;      
//            query = "SELECT lostandfoundID ,formuliernummer FROM registratie WHERE datum >= (CURDATE() - INTERVAL 21 DAY);";
//            PreparedStatement pst;
//            pst = myConn.prepareStatement(query);
//            ResultSet rs = pst.executeQuery();
//
//            XYChart.Series<String, Double> series;
//            series = new XYChart.Series<>();
//            
//
//            while (rs.next()) {
//                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
//            }
//            barChart1.getData().add(series);
//
//        } catch (SQLException e) {
//
//        }
//        
//    }
}
