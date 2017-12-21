/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.controllers;

import com.mybaggage.old.jordy.Record;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author Lenovo
 */
public class ExcelImport implements Initializable {
    
    @FXML
    private Label label;
    
    private final TableView<Record> tableView = new TableView<>();

    private final ObservableList<Record> dataList = FXCollections.observableArrayList();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("");
        label.setText("You Opened the excel");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//kolommen aanmaken
        Group root = new Group();
        TableColumn luchthaven = new TableColumn("Luchthaven");
        luchthaven.setCellValueFactory(new PropertyValueFactory<>("Luchthaven"));

        TableColumn datum = new TableColumn("Datum");
        datum.setCellValueFactory(new PropertyValueFactory<>("Datum"));

        TableColumn tijd = new TableColumn("Tijd");
        tijd.setCellValueFactory(new PropertyValueFactory<>("Tijd"));
        
        TableColumn labelNummer = new TableColumn("Label Nummer");
        labelNummer.setCellValueFactory(new PropertyValueFactory<>("Label Nummer"));
        
        TableColumn vluchtNummer = new TableColumn("Vlucht Nummer");
        vluchtNummer.setCellValueFactory(new PropertyValueFactory<>("Vlucht Nummer"));
        
        TableColumn bestemming = new TableColumn ("Bestemming");
        bestemming.setCellValueFactory(new PropertyValueFactory<>("Bestemming"));

        TableColumn naam = new TableColumn("Naam");
        naam.setCellValueFactory(new PropertyValueFactory<>("Naam"));

        TableColumn adres = new TableColumn("Adres");
        adres.setCellValueFactory(new PropertyValueFactory<>("Adres"));

        TableColumn woonplaats = new TableColumn("Woonplaats");
        woonplaats.setCellValueFactory(new PropertyValueFactory<>("Woonplaats"));

        TableColumn postcode = new TableColumn("Postcode");
        postcode.setCellValueFactory(new PropertyValueFactory<>("Postcode"));

        TableColumn telefoonNummer = new TableColumn("Telefoon Nummer");
        telefoonNummer.setCellValueFactory(new PropertyValueFactory<>("Telefoon Nummer"));

        TableColumn type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));

        TableColumn merk = new TableColumn("Merk");
        merk.setCellValueFactory(new PropertyValueFactory<>("Merk"));

        TableColumn bijzonder = new TableColumn("Bijzonder");
        bijzonder.setCellValueFactory(new PropertyValueFactory<>("Bijzonder"));

        
        tableView.setItems(dataList);
        //welke kolommen er worden geprint in de tableview
        tableView.getColumns().addAll(luchthaven, datum, tijd, naam, adres, woonplaats, postcode, telefoonNummer, type, merk, bijzonder);
        VBox vBox = new VBox();

        vBox.setSpacing(10);
        vBox.getChildren().add(tableView);
        root.getChildren().add(vBox);
    }    
    

    /*private void readCSV() {

        String CsvFile = ("C:/Users/Lenovo/Documents/HVA/Project FYS-A/Nieuwe map/New Folder/ExcelGoed/src/excelgoed/Map1.csv");

        String FieldDelimiter = ",";

        BufferedReader br;

        try {

            br = new BufferedReader(new FileReader(CsvFile));

            String line;

            while ((line = br.readLine()) != null) {

                String[] fields = line.split(FieldDelimiter, -1);
                Record record = new Record(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8], fields[9]);
                dataList.add(record);
            }

        } catch (FileNotFoundException ex) {

            Logger.getLogger(ExcelGoed.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException | UnsupportedOperationException ex) {

            Logger.getLogger(ExcelGoed.class.getName()).log(Level.SEVERE, null, ex);

        }

    }*/
}
