/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.controllers;

import com.mybaggage.old.ludo.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Ludo Bak
 */
public class MedewerkerController implements Initializable {

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnContact;

    @FXML
    private Pane functieScherm;

    //Zet waarden leeg en maakt nieuwe objecten via classes.
    Stage dialogStage = new Stage();
    Scene scene;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;

    public MedewerkerController() {
        connection = ConnectionUtil.connectdb();
    }

    @FXML
    public void loadFxml(ActionEvent event) throws IOException {

    }

    @FXML
    private void logOff(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("Inlogscherm.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void openUserManagement(ActionEvent event) throws IOException {
        Pane geklikteFunctie = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        functieScherm.getChildren().add(geklikteFunctie);
    }

    @FXML
    private void openRapportage(ActionEvent event) throws IOException {
        Pane geklikteFunctie = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        functieScherm.getChildren().add(geklikteFunctie);
    }

    @FXML
    private void openHome(ActionEvent event) throws IOException {
        Pane geklikteFunctie = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        functieScherm.getChildren().add(geklikteFunctie);
    }

    @FXML
    private void openBagageZoeken(ActionEvent event) throws IOException {
        Pane geklikteFunctie = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        functieScherm.getChildren().add(geklikteFunctie);
    }

    @FXML
    private void openContact(ActionEvent event) throws IOException {
        Pane geklikteFunctie = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        functieScherm.getChildren().add(geklikteFunctie);
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    //Vergelijkt de ingevulde gegevens met die van de database en kijkt of ze correct zijn en wat de rol van de persoon is.
    //Vervolgens worden verschillende rollen naar verschillende schermen toegestuurd.
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
