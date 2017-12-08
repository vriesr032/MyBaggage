package com.mybaggage.old.ilias.application;

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

public class EditExistingEntryController {
	
	@FXML
	private GridPane editExistingEntryDialog;
	@FXML
	private GridPane gridPane;
	@FXML
	private Button editBookButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField authorTextField;
	@FXML
	private TextField numberOfPagesTextField;
	@FXML 
	private TextField startDateTextField;
	@FXML
	private TextField endDateTextField;
	@FXML
	private TextField thoughtsTextField;
	@FXML
	private Label titleLabel;
	@FXML
	private Label authorLabel;
	@FXML
	private Label numberOfPagesLabel;
	@FXML
	private Label startDateLabel;
	@FXML
	private Label endDateLabel;
	@FXML
	private Label thoughtsLabel;
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
		editBookButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
				DBConnection conn = new DBConnection();
					
					try{
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
						pst.setString(1, titleTextField.getText());
						pst.setString(2, authorTextField.getText());
						pst.setString(3, numberOfPagesTextField.getText());
						pst.setString(4, startDateTextField.getText());
						pst.setString(5, endDateTextField.getText());
						pst.setString(6, thoughtsTextField.getText());
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
			loader.setLocation(getClass().getResource("/view/EditExistingEntryDialog.fxml"));
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
			titleTextField.setText(data.getId());
			authorTextField.setText(data.getVoornaam());
			numberOfPagesTextField.setText(data.getAchternaam());
			startDateTextField.setText(data.getGebruikersnaam());
			endDateTextField.setText(data.getWachtwoord());
			thoughtsTextField.setText(data.getFunctie());
		}
	}
	

	
	
	@FXML
	public void handleCancel(){
		Stage stage = (Stage) editExistingEntryDialog.getScene().getWindow();
        stage.close();
	}
	
	
	
	
}

