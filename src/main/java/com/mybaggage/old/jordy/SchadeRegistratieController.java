package com.mybaggage.old.jordy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mybaggage.Database;
import com.mybaggage.Main;
import com.mybaggage.models.Bagageregistratie;
import com.mybaggage.Utilities;
import com.mybaggage.models.BagageFormulierQueryBuilder;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/* Het doel van deze code is dat de medewerker schade kan registreren.
 * @author Jordy Pouw (500783513)
 */

public  class SchadeRegistratieController implements Initializable {

    final String DEFAULT_STRING = "";
    final int DEFAULT_INTEGER = 1;

    @FXML
    private AnchorPane schadeFormulierPane;

    @FXML
    private JFXTextField txtTijd;

    @FXML
    private JFXDatePicker txtDatum;

    @FXML
    private JFXTextField txtNaam;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtMerk;

    @FXML
    private JFXTextField txtLuchthaven;

    @FXML
    private JFXTextField txtSchade;

    @FXML
    private JFXTextField txtVergoeding;

    @FXML
    private JFXTextField txtBankrekening;

    @FXML
    private JFXTextField txtLostAndFoundID;

    @FXML
    private JFXButton btn_RegistreerSchadevergoeding;
    
    @FXML
    private JFXButton btn_MaakFormulierLeeg;
    
    @FXML
    private Label labelReset;
    
    @FXML
    private Label labelRegistreer;

    @FXML
    private void insertSchadeFormulier(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
        if (event.getTarget() == btn_RegistreerSchadevergoeding) {

            Schaderegistratie schadeFormulier = vulSchadeFormulierIn();
            
            //Maak een label aan voor als de button registreer wordt aangeklikt.
            labelRegistreer.setText("Geregistreerd!");

            //Maak een query aan zodat de ingevulde tekst goed in de database wordt gezet.
            String query = "INSERT INTO bagage_registratie.schade (lostandfoundID, bankrekening, datum, tijd, luchthaven, naam, schade, "
                    + "vergoeding, merk, type)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //Maak een methode aan zodat de ingevulde tekst in de database wordt gezet.
            try {
                Utilities.preparedStatement = Database.connectdb().prepareStatement(query);               
                Utilities.preparedStatement.setInt(1, schadeFormulier.getLostAndFoundID());
                Utilities.preparedStatement.setString(10, schadeFormulier.getBankrekening());
                Utilities.preparedStatement.setString(3, schadeFormulier.getDatum());
                Utilities.preparedStatement.setString(4, schadeFormulier.getTijd());
                Utilities.preparedStatement.setString(5, schadeFormulier.getLuchthaven());
                Utilities.preparedStatement.setString(2, schadeFormulier.getNaam());
                Utilities.preparedStatement.setString(9, schadeFormulier.getSchade());
                Utilities.preparedStatement.setString(8, schadeFormulier.getVergoeding());
                Utilities.preparedStatement.setString(7, schadeFormulier.getType());
                Utilities.preparedStatement.setString(6, schadeFormulier.getMerk());

                Utilities.preparedStatement.executeUpdate();
            } catch (SQLException mySQLException) {
                throw mySQLException;
            }
        }
    }

    @FXML
    //zorg dat het formulier kan worden ingevuld.
    private Schaderegistratie vulSchadeFormulierIn() {
        Schaderegistratie schadeFormulier = new Schaderegistratie();

        schadeFormulier.setDatum(txtDatum.getValue());
        schadeFormulier.setTijd(); // Set systeem tijd
        schadeFormulier.setLuchthaven(txtLuchthaven.getText());
        schadeFormulier.setNaam(txtNaam.getText());
        schadeFormulier.setMerk(txtMerk.getText());
        schadeFormulier.setType(txtType.getText());
        schadeFormulier.setLostAndFoundID(txtLostAndFoundID.getText());
        schadeFormulier.setType(txtType.getText());
        schadeFormulier.setSchade(txtSchade.getText());
        schadeFormulier.setVergoeding(txtVergoeding.getText());
        schadeFormulier.setBankrekening(txtBankrekening.getText());

        return schadeFormulier;
    }
    
    @FXML
    //maak een methode aan voor de button reset formulier zodat het formulier handmatig kan worden gereset
    private void maakLeeg(){
        txtTijd.setText("");
        txtLuchthaven.setText("");
        txtNaam.setText("");
        txtMerk.setText("");
        txtType.setText("");
        txtLostAndFoundID.setText("");
        txtType.setText("");
        txtSchade.setText("");
        txtVergoeding.setText("");
        txtBankrekening.setText("");
               
        labelReset.setText("Formulier gereset!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.getScene().getStylesheets().add("/styles/StylesMitchell.css");        
    }
}
