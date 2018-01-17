package com.mybaggage.controllers;


import com.mybaggage.old.ilias.application.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Gebruiker Toevoegen
 * @author Ilias Boughaba (500775068)
 */

public class NewEntryController {
	
	
	@FXML
	private GridPane newEntryDialog;
	@FXML
	private GridPane gridPane;
	@FXML
	private Button addButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TextField idTextField;
	@FXML
	private TextField voornaamTextField;
	@FXML
	private TextField achternaamTextField;
	@FXML 
	private TextField gebruikersnaamTextField;
	@FXML
	private TextField wachtwoordTextField;
	@FXML
	private TextField functieTextField;
	@FXML
	private Label idLabel;
	@FXML
	private Label voornaamLabel;
	@FXML
	private Label achternaamLabel;
	@FXML 
	private Label gebruikersnaamLabel;
	@FXML
	private Label wachtwoordLabel;
	@FXML
	private Label functieLabel;
	@FXML
	public TableView<DataModel> userTable;
	@FXML
	public ObservableList<DataModel> userData;
        @FXML
	private ComboBox<DataModel> myComboBox;
	
	DataModel data;
	public NewEntryController(){}
	
	
	
	@FXML
	private void initialize(){
		addButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	DBConnection conn = new DBConnection();
				
				
				String s2 = voornaamTextField.getText();
				String s3 = achternaamTextField.getText();
				String s4 = gebruikersnaamTextField.getText();
				String s5 = wachtwoordTextField.getText();
				String s6 = functieTextField.getText();
				
                                //Insert Function
				String query = "INSERT INTO persoonsgegevens ( voornaam, achternaam, gebruikersnaam, wachtwoord, functie) VALUES ('"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"')";
				PreparedStatement pst;
				
				try{
					
				Connection myConn = conn.getConnection();
				pst = myConn.prepareStatement(query);
                                pst.executeUpdate();

                                data.setVoornaam(s2);
                                data.setAchternaam(s3);
                                data.setGebruikersnaam(s4);
                                data.setWachtwoord(s5);
                                data.setFunctie(s6);
		     
				}catch (Exception ex){
					System.out.println(ex);
				}
				
				Stage stage = (Stage) newEntryDialog.getScene().getWindow();
		        stage.close();
		        
		    }
		    	
		});
	}
		
	
	//Builds window.
	final void handleAddNewEntryMenuItem(GridPane gridPane){
		try{
			GridPane root = FXMLLoader.load(getClass().getResource("/view/NewEntryDialog.fxml"));	
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	

	@FXML
        //Close Window
	public void handleCancel(){
		Stage stage = (Stage) newEntryDialog.getScene().getWindow();
        stage.close();
	}

	
        	
        
	}
	

