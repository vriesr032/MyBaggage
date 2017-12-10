/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.controllers;

import com.mybaggage.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ludo Bak
 */
public class InlogschermController implements Initializable {

 


    @FXML
    private TextField textGebruikersnaam;

    @FXML
    private PasswordField textPassword;

    @FXML
    private ChoiceBox choiceFunctie;

    @FXML
    private Button button;

    //Zet waarden leeg en maakt nieuwe objecten via classes.
    Stage dialogStage = new Stage();
    Scene scene;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;

    public InlogschermController() {
        connection = Database.connectdb();
    }

    @FXML
    public void loadFxml(ActionEvent event) throws IOException {

    }

    @FXML
    private void logOff(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("Inlogscherm.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    

    //Vergelijkt de ingevulde gegevens met die van de database en kijkt of ze correct zijn en wat de rol van de persoon is.
    //Vervolgens worden verschillende rollen naar verschillende schermen toegestuurd.
    @FXML
    private void logIn(ActionEvent event) {
        String gebruikersnaam = textGebruikersnaam.getText().toString();
        String wachtwoord = textPassword.getText().toString();
        String functie = choiceFunctie.getValue().toString();
        String verify = "SELECT * FROM persoonsgegevens WHERE gebruikersnaam = ? and wachtwoord = ? and functie = ?";

        try {
            preparedStatement = connection.prepareStatement(verify);
            preparedStatement.setString(1, gebruikersnaam);
            preparedStatement.setString(2, wachtwoord);
            preparedStatement.setString(3, functie);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                infoBox("Voeg alstublieft een correcte ID en wachtwoord in.", "Failed", null);
            } else {

                if ("Medewerker".equals(functie)) {
                    infoBox("U heeft succesvol ingelogd.", "Success", null);
                    Node source = (Node) event.getSource();
                    dialogStage = (Stage) source.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("Medewerker.fxml")));
                    dialogStage.setScene(scene);
                    dialogStage.show();
                } else if ("Admin".equals(functie)) {
                    infoBox("U heeft succesvol ingelogd.", "Success", null);
                    Node source = (Node) event.getSource();
                    dialogStage = (Stage) source.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("Admin.fxml")));
                    dialogStage.setScene(scene);
                    dialogStage.show();
                }
            }

        } catch (IOException | SQLException e) {
        }
    }

    //De infoBox die aangeeft of de ingevulde gegevens kloppen.
    public static void infoBox(String berichtMain, String berichtTitle, String berichtHeader) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(berichtTitle);
        alert.setHeaderText(berichtHeader);
        alert.setContentText(berichtMain);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
