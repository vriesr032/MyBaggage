package com.mybaggage.controllers;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rickdevries
 */
public class BagageToevoegenController implements Initializable {

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
    
//Gaat terug naar het scherm Overzicht Bagage
    @FXML
    private Button btn_annuleren;

    @FXML
    private void annuleren() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageOverzicht.fxml");
    }

//Voegt bagage toe aan de database
    @FXML
    private Button btn_handleBagageToevoegen;
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

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
                MainController.switchScherm("/com/mybaggage/controllers/BagageOverzicht.fxml");
            }

        } catch (SQLException ex) {
            Logger.getLogger(BagageToevoegenController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
