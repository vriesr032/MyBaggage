package com.mybaggage.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rickdevries
 */
public class LoginSceneController implements Initializable {

    @FXML
    private TextField textField;
    
    @FXML
    private void onClick() {
        MainController.switchScherm("/com/mybaggage/controllers/LoginScene1.fxml");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textField.setText("Hallo Team 3 - Scene 1");
    }    
    
}
