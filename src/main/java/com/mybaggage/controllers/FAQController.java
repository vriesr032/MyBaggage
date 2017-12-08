package com.mybaggage.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FAQController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label label;

    @FXML
    private void loadQuestion1(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Question1.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadQuestion2(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Question2.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadQuestion3(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Question3.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadQuestion4(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Question4.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadQuestion5(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Question5.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadContact(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Contact.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadFAQ(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/FAQ.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
