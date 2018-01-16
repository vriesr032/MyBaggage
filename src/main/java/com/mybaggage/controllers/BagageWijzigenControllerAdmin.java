package com.mybaggage.controllers;

import com.mybaggage.Database;
import com.mybaggage.Utilities;
import static com.mybaggage.Utilities.rootPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rick de Vries (500758516)
 */
public class BagageWijzigenControllerAdmin implements Initializable {

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
    private AnchorPane rootAnchorPane;

    @FXML
    private AnchorPane rootPane;
//Gaat terug naar het scherm Overzicht Bagage
    @FXML
    private Button btn_annuleren;

    private void annuleren() throws IOException {
        Utilities.switchSchermNaarFXML("BagageOverzicht.fxml", rootAnchorPane);
    }

//Maakt het mogelijk alle data aan te passen in de database
    @FXML
    private Button btn_hanldeAanpassen;

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private Stage dialogStage = new Stage();
    private Scene scene;
    private AnchorPane bagageOverzicht;

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
               Utilities.switchSchermNaarFXML("BagageOverzicht.fxml", rootAnchorPane);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BagageVerwijderenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void loadOverzichtAdmin(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BagageOverzicht.fxml"));
        rootAnchorPane.getChildren().setAll(pane);
    }

    public BagageWijzigenControllerAdmin() {
        conn = Database.connectdb();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
