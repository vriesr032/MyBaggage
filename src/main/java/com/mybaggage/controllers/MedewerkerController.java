/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.controllers;

import com.jfoenix.controls.JFXButton;
import com.mybaggage.Utilities;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ludo Bak
 */
public class MedewerkerController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnHelpdesk;
    @FXML
    private Button btnBagage;
    @FXML
    private Button btnBagageToevoegen;
    @FXML
    private Button btnBagageZoeken;
    @FXML
    private Button btnRegistreerSchadevergoeding;
    AnchorPane bagageOverzicht, faq, helpdesk, fxml2, inlogscherm, bagageZoeken, registreerSchadevergoeding;

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
            bagageOverzicht = FXMLLoader.load(getClass().getResource("BagageOverzichtMedewerker.fxml"));
            inlogscherm = FXMLLoader.load(getClass().getResource("Inlogscherm.fxml"));
            faq = FXMLLoader.load(getClass().getResource("FAQ.fxml"));
            fxml2 = FXMLLoader.load(getClass().getResource("Rapportage.fxml"));

            bagageZoeken = FXMLLoader.load(getClass().getResource("VermisteBagageRegistratie.fxml"));
            helpdesk = FXMLLoader.load(getClass().getResource("HelpdeskMedewerker.fxml"));
            registreerSchadevergoeding = FXMLLoader.load(getClass().getResource("RegistreerSchadevergoeding.fxml"));

            setNode(fxml2);
        } catch (IOException ex) {
            Logger.getLogger(MedewerkerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void logOff(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
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

    /**
     * Keyboard-shortcuts methode om te navigeren met F-Keys.
     *
     * @author Ismail Bahar (500783727)
     */
    @FXML
    private void keyPressed(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case F2:
                Utilities.switchSchermNaarFXML("BagageOverzichtMedewerker.fxml", holderPane);
                break;
            case F3:
                Utilities.switchSchermNaarFXML("GevondenBagageRegistratie.fxml", holderPane);
                break;
            case F4:
                Utilities.switchSchermNaarFXML("RegistreerSchadevergoeding.fxml", holderPane);
                break;
            case F5:
                Utilities.switchSchermNaarFXML("VermisteBagageRegistratie.fxml", holderPane);
                break;
            case F6:
                Utilities.switchSchermNaarFXML("FAQ.fxml", holderPane);
                break;
            case F7:
                Utilities.switchSchermNaarFXML("HelpdeskMedewerker.fxml", holderPane);
                break;
            default:
                break;
        }
    }

    @FXML
    private void openHome(ActionEvent event) {
        setNode(fxml2);
    }

    @FXML
    private void openBagage(ActionEvent event) throws IOException {
        //setNode(bagageOverzicht);
        Utilities.switchSchermNaarFXML("BagageOverzichtMedewerker.fxml", holderPane);
    }

    @FXML
    private void openContact(ActionEvent event) {
        setNode(faq);
    }

    @FXML
    private void openHelpdesk(ActionEvent event) {
        setNode(helpdesk);
    }

    @FXML
    private void openBagageToevoegen(ActionEvent event) throws IOException {
        //setNode(bagageToevoegen);
        Utilities.switchSchermNaarFXML("GevondenBagageRegistratie.fxml", holderPane);
    }

    @FXML
    private void openBagageOverzicht(ActionEvent event) {
        setNode(bagageOverzicht);
    }

    @FXML
    private void openBagageZoeken(ActionEvent event) throws IOException {
        //setNode(bagageZoeken);
        Utilities.switchSchermNaarFXML("VermisteBagageRegistratie.fxml", holderPane);
    }

    @FXML
    private void openRegistreerSchadevergoeding(ActionEvent event) throws IOException {
        //setNode(registreerSchadevergoeding);
        Utilities.switchSchermNaarFXML("RegistreerSchadevergoeding.fxml", holderPane);

    }
}
