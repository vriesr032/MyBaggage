package com.mybaggage.controllers;

import com.mybaggage.old.ilias.application.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Wijzigen van gebruikers
 * @author Ilias Boughaba (500775068)
 */

public class EditExistingEntryController {
	
	@FXML
	private GridPane editExistingEntryDialog;
	@FXML
	private GridPane gridPane;
	@FXML
	private Button editButton;
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
	private TableView<DataModel> bookTable;
	
	public DataModel data;
	public ObservableList<DataModel> bookData;
	
	public EditExistingEntryController(){}
	public EditExistingEntryController(ObservableList<DataModel> bookData){
		this.bookData = bookData;
	}
	
	@FXML
	private void initialize(){
		editButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
				DBConnection conn = new DBConnection();
					
					try{
                                                //Update Function
						Connection myConn = conn.getConnection();
						String query = "UPDATE persoonsgegevens SET id = ?, "
								+ "voornaam = ?, "
								+ "achternaam = ?, "
								+ "gebruikersnaam = ?, "
								+ "wachtwoord = ?, "
								+ "functie = ? "
								+ "WHERE id = ?";
									
						PreparedStatement pst;
						pst = myConn.prepareStatement(query);
						pst.setString(1, idTextField.getText());
						pst.setString(2, voornaamTextField.getText());
						pst.setString(3, achternaamTextField.getText());
						pst.setString(4, gebruikersnaamTextField.getText());
						pst.setString(5, wachtwoordTextField.getText());
						pst.setString(6, functieTextField.getText());
						pst.setString(7, data.getId());
						pst.executeUpdate();
						
						
						
						
					}catch (Exception ex){
						System.out.println(ex);
					}
		    	
		    	
		    	Stage stage = (Stage) editExistingEntryDialog.getScene().getWindow();
				stage.close();	
		    	
		    }
		});
		
	}
	
	//Builds window.
	public void handleEditExistingEntryMenuItem(GridPane gridPane){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("EditExistingEntryDialog.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setScene(scene);
			
			EditExistingEntryController controller = loader.getController();
			controller.showBook(data);
			stage.showAndWait();
			
			
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void showBook(DataModel data){
		this.data = data;
		if (data != null){
			idTextField.setText(data.getId());
			voornaamTextField.setText(data.getVoornaam());
			achternaamTextField.setText(data.getAchternaam());
			gebruikersnaamTextField.setText(data.getGebruikersnaam());
			wachtwoordTextField.setText(data.getWachtwoord());
			functieTextField.setText(data.getFunctie());
		}
	}
	

	
	
	@FXML
        //Close Window
	public void handleCancel(){
		Stage stage = (Stage) editExistingEntryDialog.getScene().getWindow();
        stage.close();
	}
	
	
	
	
}

