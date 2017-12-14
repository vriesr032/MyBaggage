/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.controllers;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ilias
 */
public class AdminController implements Initializable {


    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnPricing;
    @FXML
    private JFXButton btnContacts;
    @FXML
    private JFXButton btnWidgets;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnAlerts;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnExit;
    
    AnchorPane bagage,um,pricing,profiles,widgets,controls;
    @FXML
    private JFXButton btnControls;
    
        //Zet waarden leeg en maakt nieuwe objecten via classes.
    Stage dialogStage = new Stage();
    Scene scene;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load all fxmls in a cache
        try {
             bagage = FXMLLoader.load(getClass().getResource("BagageOverzicht.fxml"));
             um = FXMLLoader.load(getClass().getResource("UM.fxml"));
             pricing = FXMLLoader.load(getClass().getResource("UM.fxml"));
             profiles = FXMLLoader.load(getClass().getResource("UM.fxml"));
             widgets = FXMLLoader.load(getClass().getResource("UM.fxml"));
             controls = FXMLLoader.load(getClass().getResource("UM.fxml"));
            setNode(pricing);
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
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

    @FXML
    private void switchPricing(ActionEvent event) {
        setNode(pricing);
    }

    @FXML
    private void switchBagage(ActionEvent event) {
        setNode(bagage);
    }

    @FXML
    private void switchWidget(ActionEvent event) {
        setNode(widgets);
    }

    @FXML
    private void switchProfile(ActionEvent event) {
        setNode(profiles);
    }

    @FXML
    private void switchUM(ActionEvent event) {
        setNode(um);
    }

    @FXML
    private void switchControls(ActionEvent event) {
        setNode(controls);
    }

}
