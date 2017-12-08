package com.mybaggage.old.rik;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rick de Vries (500758516)
 */
public class MainSceneController implements Initializable {

    Stage dialogStage = new Stage();
    Scene scene;

//Waardes voor de invoer voor het bagage toevoegen
    @FXML
    private Button btn_annuleren;
    @FXML
    private Button btn_bagageVerwijderen;
    @FXML
    private Button btn_bagageAanpassen;
    @FXML
    private Button btn_bagageToevoegen;
    @FXML
    private Button btn_aanpassen;
    @FXML
    private Button btn_verwijderen;
    @FXML
    private Button btn_toevoegen;
    @FXML
    private TextField txt_naam;
    @FXML
    private TextField txt_kleur;
    @FXML
    private TextField txt_grootte;
    @FXML
    private TextField txt_soortBagage;
    @FXML
    private TextField txt_status;
    @FXML
    private TextField txt_registratienummer;
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
//Gaat naar het scherm bagage toevoegen

    @FXML
    private void bagageToevoegen(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("BagageToevoegen.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
//Gaat terug naar het scherm mijn bagage

    @FXML
    private void annuleren(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("MainScene.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
//Gaat naar het scherm bagage aanpassen

    @FXML
    private void bagageAanpassen(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("BagageAanpassen.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
//Gaat naar het scherm bagage verwijderen

    @FXML
    private void bagageVerwijderen(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("BagageVerwijderen.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
//Voegt bagage toe aan de database

    @FXML
    private void handleBagageToevoegen(ActionEvent event) throws IOException, SQLException {
        String sql = "Insert into bagage(Naam, Kleur, Grootte, SoortBagage, Status) Values(?,?,?,?)";
        String naam = txt_naam.getText();
        String kleur = txt_kleur.getText();
        String grootte = txt_grootte.getText();
        String soortBagage = txt_soortBagage.getText();
        String status = txt_status.getText();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(2, naam);
            pst.setString(3, kleur);
            pst.setString(4, grootte);
            pst.setString(5, soortBagage);
            pst.setString(6, status);

            int i = pst.executeUpdate();
            if (i == 1) {
                System.out.println("Bagage Toegevoegd!");
                setCellTable();
                loadDataFromDatabase();
                clearTextField();
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("MainScene.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
        }
    }

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
//Maakt het mogelijk alle data aan te passen

    @FXML
    private void handleAanpassen(ActionEvent event) throws IOException {
        String sql = "Update bagage set naam = ?, kleur = ?, grootte = ?, soortBagage = ?, status = ? where RegistratieNummer = ?";
        try {
            String registratieNummer = txt_registratienummer.getText();
            String naam = txt_naam.getText();
            String kleur = txt_kleur.getText();
            String grootte = txt_grootte.getText();
            String soortBagage = txt_soortBagage.getText();
            String status = txt_status.getText();

            pst = conn.prepareStatement(sql);
            pst.setString(2, naam);
            pst.setString(3, kleur);
            pst.setString(4, grootte);
            pst.setString(5, soortBagage);
            pst.setString(6, status);

            int i = pst.executeUpdate();
            if (i == 1) {
                System.out.println("Bagage aangepast!");
                loadDataFromDatabase();
                clearTextField();
                clearRegistratieNummer();
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("MainScene.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Zorgt dat de tekst-invoervelden leeg worden gemaakt na invoer

    @FXML
    private void clearTextField() {
        txt_naam.clear();
        txt_kleur.clear();
        txt_grootte.clear();
        txt_soortBagage.clear();
        txt_status.clear();
    }

    @FXML
    private void clearRegistratieNummer() {
        txt_registratienummer.clear();
    }

    @FXML
    private void handleVerwijderen(ActionEvent event) throws IOException {
        String sql = "Delete from bagage where RegistratieNummer = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_registratienummer.getText());
            int i = pst.executeUpdate();
            if (i == 1) {
                System.out.println("Bagage Verwijderd!");
                loadDataFromDatabase();
                clearTextField();
                clearRegistratieNummer();
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("MainScene.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
