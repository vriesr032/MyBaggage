package com.mybaggage.controllers;

import com.mybaggage.Database;
import com.mybaggage.Utilities;
import com.mybaggage.models.BagageToevoegen;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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

    private final int indexNummer = 1;
    private final IntegerProperty index = new SimpleIntegerProperty();

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private TableColumn type;

    @FXML
    private TableColumn formuliernummer;

    @FXML
    private TableColumn lostandfoundID;

    @FXML
    private TableColumn kenmerken;

    @FXML
    private TableColumn labelnummer;

    @FXML
    private TableColumn luchthaven;
//Gaat naar het scherm Bagage Toevoegen

//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageWijzigen;

//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageVerwijderen;

    @FXML
    private void bagageVerwijderen() throws IOException {
//MainController.switchScherm("/com/mybaggage/controllers/BagageVerwijderen.fxml");
        Utilities.switchSchermNaarFXML("BagageVerwijderen.fxml", rootAnchorPane);
    }

    @FXML
    private void bagageWijzigen() throws IOException {
//MainController.switchScherm("/com/mybaggage/controllers/BagageWijzigen.fxml");
        Utilities.switchSchermNaarFXML("BagageWijzigen.fxml", rootAnchorPane);
    }

    @FXML
    private void setCellTable() {
        formuliernummer.setCellValueFactory(new PropertyValueFactory<>("formuliernummer"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        lostandfoundID.setCellValueFactory(new PropertyValueFactory<>("lostandfoundID"));
        kenmerken.setCellValueFactory(new PropertyValueFactory<>("kenmerken"));
        labelnummer.setCellValueFactory(new PropertyValueFactory<>("labelnummer"));
        luchthaven.setCellValueFactory(new PropertyValueFactory<>("luchthaven"));
    }

//Laad alle data van de database in de tabel
    @FXML
    private ObservableList<com.mybaggage.models.BagageToevoegen> data;

    @FXML
    private TableView<com.mybaggage.models.BagageToevoegen> tabelBagage;

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @FXML
    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from registratie");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new com.mybaggage.models.BagageToevoegen(rs.getString(1), rs.getString(2), rs.getString(18), rs.getString(11), rs.getString(12), rs.getString(17)));
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

    }

    public void onDeleteItem(ActionEvent event) throws SQLException {
        BagageToevoegen i = tabelBagage.getSelectionModel().getSelectedItem();
        if (i != null) {

            String query = "DELETE FROM bagage_registratie.registratie WHERE formuliernummer = '" + i.getFormuliernummer() + "'";

            PreparedStatement pst;
            pst = conn.prepareStatement(query);
            pst.executeUpdate();
            tabelBagage.getSelectionModel().clearSelection();
        }
    }
}
