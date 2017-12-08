/*package com.mybaggage.old.rik;

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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rick de Vries (500758516)
 *
public class MainSceneController implements Initializable {

    @FXML
    private Connection conn = null;
    @FXML
    private PreparedStatement pst = null;
    @FXML
    private ResultSet rs = null;

    @FXML
    private ObservableList<BagageToevoegen> data;
    @FXML
    private TableView<BagageToevoegen> tabelBagage;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Database.Connect();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();
    }
//Geeft alle cellen van de tabel een waarde

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
    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from bagage");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new BagageToevoegen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabelBagage.setItems(data);
    }
}
*/