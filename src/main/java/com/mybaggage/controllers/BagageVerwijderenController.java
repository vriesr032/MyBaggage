package com.mybaggage.controllers;

import com.mybaggage.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author rickdevries
 */
public class BagageVerwijderenController implements Initializable {

    @FXML
    private TextField txt_registratienummer;

    @FXML
    private AnchorPane holderPane;

//Gaat terug naar het scherm Overzicht Bagage
    @FXML
    private Button btn_annuleren;

    @FXML
    private void annuleren() {
        setNode(bagageOverzicht);
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
                setNode(bagageOverzicht);
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
        try {
            bagageOverzicht = FXMLLoader.load(getClass().getResource("BagageOverzichtMedewerker.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(BagageVerwijderenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
}
