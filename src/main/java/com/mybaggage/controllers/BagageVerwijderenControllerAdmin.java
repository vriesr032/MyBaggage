package com.mybaggage.controllers;

import com.mybaggage.Database;
import com.mybaggage.Utilities;
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
 * @author rickdevries
 */
public class BagageVerwijderenControllerAdmin implements Initializable {

    @FXML
    private TextField txt_registratienummer;

    @FXML
    private AnchorPane rootAnchorPane;

//Gaat terug naar het scherm Overzicht Bagage
    @FXML
    private Button btn_annuleren;

    private void annuleren() throws IOException {
        Utilities.switchSchermNaarFXML("BagageOverzicht.fxml", rootAnchorPane);
    }
//Maakt het mogelijk data uit de database te verwijderen
    @FXML
    private Button btn_handleVerwijderen;

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private Stage dialogStage = new Stage();
    private Scene scene;
    private AnchorPane bagageOverzicht;

    @FXML
    private void handleVerwijderen(ActionEvent event) throws IOException {
        String sql = "DELETE FROM bagage_registratie.registratie WHERE formuliernummer = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_registratienummer.getText());
            int i = pst.executeUpdate();
            if (i == 1) {
                System.out.println("Bagage Verwijderd!");
                Utilities.switchSchermNaarFXML("BagageOverzicht.fxml", rootAnchorPane);            
            }
        } catch (SQLException ex) {
            Logger.getLogger(BagageVerwijderenControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BagageVerwijderenControllerAdmin() {
        conn = Database.connectdb();
    }
    
    @FXML
    private void loadOverzichtAdmin(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BagageOverzicht.fxml"));
        rootAnchorPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
