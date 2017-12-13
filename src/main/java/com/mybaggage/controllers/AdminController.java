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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Ludo Bak
 */
public class AdminController implements Initializable {

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnRapportage;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnContact;

    @FXML
    private Button btnUserManagament;

    @FXML
    private Pane functieScherm;

    //Zet waarden leeg en maakt nieuwe objecten via classes.
    Stage dialogStage = new Stage();
    Scene scene;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;

    public AdminController() {
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

    @FXML
    private void openUserManagement(ActionEvent event) throws IOException {
        Pane geklikteFunctie = FXMLLoader.load(getClass().getResource("UM.fxml"));
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
        Pane geklikteFunctie = FXMLLoader.load(getClass().getResource("BagageOverzicht.fxml"));
        functieScherm.getChildren().add(geklikteFunctie);
    }

    @FXML
    private void openContact(ActionEvent event) throws IOException {
        Pane geklikteFunctie = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        functieScherm.getChildren().add(geklikteFunctie);
        Pane geklikteFunctie2 = FXMLLoader.load(getClass().getResource("Contact.fxml"));
        functieScherm.getChildren().add(geklikteFunctie2);
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
