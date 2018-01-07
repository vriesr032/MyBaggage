package com.mybaggage.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
    @FXML
    private BorderPane borderPane;
    
    private static BorderPane staticBorderPane;
    
    
    public static void switchScherm(String fxml) {
        try {
            Parent parent = FXMLLoader.load(MainController.class.getResource(fxml));
            
            staticBorderPane.setCenter(parent);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staticBorderPane = borderPane;
        
        MainController.switchScherm("/com/mybaggage/controllers/LoginScene.fxml");
        
    }    
}
