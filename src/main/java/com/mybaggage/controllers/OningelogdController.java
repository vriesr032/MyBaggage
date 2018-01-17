package com.mybaggage.controllers;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ludo Bak
 */
public class OningelogdController implements Initializable {

    @FXML
    private AnchorPane holderPane;

    @FXML
    private Button btnToLogin;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnContact;
    
    AnchorPane contact, helpdesk;

    
    Stage dialogStage = new Stage();
    Scene scene;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Laad de FXML's in cache geheugen.
        try {

            contact = FXMLLoader.load(getClass().getResource("Contact.fxml"));
        
            setNode(contact);

        } catch (IOException ex) {
            Logger.getLogger(MedewerkerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      @FXML
    private void openContact(ActionEvent event) {
        setNode(contact);
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage;
        stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void toLogin(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("Inlogscherm.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
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
