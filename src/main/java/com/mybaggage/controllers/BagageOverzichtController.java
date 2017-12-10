package com.mybaggage.controllers;

import com.mybaggage.Database;
import com.mybaggage.old.ludo.ConnectionUtil;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author rickdevries
 */
public class BagageOverzichtController implements Initializable {

    @FXML
    private TableColumn kolomnaam;

    @FXML
    private TableColumn kolomkleur;

    @FXML
    private TableColumn kolomgrootte;

    @FXML
    private TableColumn kolomsoortbagage;

    @FXML
    private TableColumn kolomstatus;

    @FXML
    private TableColumn kolomRegistratieNummer;
//Gaat naar het scherm Bagage Toevoegen
    @FXML
    private Button btn_bagageToevoegen;

    @FXML
    private void bagageToevoegen() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageToevoegen.fxml");
    }
//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageWijzigen;

    @FXML
    private void bagageWijzigen() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageWijzigen.fxml");
    }
//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageVerwijderen;

    @FXML
    private void bagageVerwijderen() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageVerwijderen.fxml");
    }

    @FXML
    private void setCellTable() {
        kolomnaam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        kolomkleur.setCellValueFactory(new PropertyValueFactory<>("kleur"));
        kolomgrootte.setCellValueFactory(new PropertyValueFactory<>("grootte"));
        kolomsoortbagage.setCellValueFactory(new PropertyValueFactory<>("soortBagage"));
        kolomstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
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
            pst = conn.prepareStatement("Select * from bagage");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new com.mybaggage.models.BagageToevoegen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BagageOverzichtController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabelBagage.setItems(data);
    }
    
    public BagageOverzichtController(){
        conn = Database.connectdb();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = ConnectionUtil.connectdb();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();
    }
}
