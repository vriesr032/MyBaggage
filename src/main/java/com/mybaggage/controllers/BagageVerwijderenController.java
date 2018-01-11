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
public class BagageVerwijderenController implements Initializable {

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
        String sql = "Delete from bagage where RegistratieNummer = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_registratienummer.getText());
            int i = pst.executeUpdate();
            if (i == 1) {
                System.out.println("Bagage Verwijderd!");
                Utilities.switchSchermNaarFXML("BagageOverzicht.fxml", rootAnchorPane);            
            }
        } catch (SQLException ex) {
            Logger.getLogger(BagageVerwijderenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BagageVerwijderenController() {
        conn = Database.connectdb();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
