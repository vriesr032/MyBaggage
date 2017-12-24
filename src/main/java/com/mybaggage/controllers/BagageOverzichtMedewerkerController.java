package com.mybaggage.controllers;

import com.mybaggage.Database;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ludo Bak (500760041)
 */
public class BagageOverzichtMedewerkerController implements Initializable {

    private int indexNummer = 1;
    private IntegerProperty index = new SimpleIntegerProperty();
    @FXML
    private TableColumn kolomNaam;

    @FXML
    private TableColumn kolomKleur;

    @FXML
    private TableColumn kolomBagageGrootte;

    @FXML
    private TableColumn kolomSoortBagage;

    @FXML
    private TableColumn kolomStatus;

    @FXML
    private TableColumn kolomRegistratieNummer;
//Gaat naar het scherm Bagage Toevoegen

//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageWijzigen;

//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageVerwijderen;

    @FXML
    private void bagageVerwijderen() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageVerwijderen.fxml");
    }

    @FXML
    private void bagageWijzigen() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageWijzigen.fxml");
    }

    @FXML
    private void setCellTable() {
        kolomRegistratieNummer.setCellValueFactory(new PropertyValueFactory<>("registratieNummer"));
        kolomNaam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        kolomKleur.setCellValueFactory(new PropertyValueFactory<>("kleur"));
        kolomBagageGrootte.setCellValueFactory(new PropertyValueFactory<>("bagageGrootte"));
        kolomSoortBagage.setCellValueFactory(new PropertyValueFactory<>("soortBagage"));
        kolomStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

//Laad alle data van de database in de tabel
    @FXML
    private ObservableList<com.mybaggage.models.BagageOverzichtMedewerkerController> data;

    @FXML
    private TableView<com.mybaggage.models.BagageOverzichtMedewerkerController> tabelBagage;

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @FXML
    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from bagage");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new com.mybaggage.models.BagageOverzichtMedewerkerController(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BagageOverzichtMedewerkerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabelBagage.setItems(data);
    }

    public BagageOverzichtMedewerkerController() {
        conn = Database.connectdb();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();

        tabelBagage.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                index.set(data.indexOf(newValue));
            }
        });
    }

    public void onDeleteItem(ActionEvent event) throws SQLException {
        com.mybaggage.models.BagageOverzichtMedewerkerController i = tabelBagage.getSelectionModel().getSelectedItem();
        if (i != null) {

            String query = "DELETE FROM bagage_registratie.bagage WHERE RegistratieNummer = '" + i.getRegistratieNummer() + "'";

            PreparedStatement pst;
            pst = conn.prepareStatement(query);
            pst.executeUpdate();
            tabelBagage.getSelectionModel().clearSelection();
        }
    }
}
