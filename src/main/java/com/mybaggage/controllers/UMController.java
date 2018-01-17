package com.mybaggage.controllers;

import com.mybaggage.Main;
import com.mybaggage.old.ilias.application.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;






public class UMController implements Initializable{
     
    @FXML
    private AnchorPane borderPane;
    @FXML
    public TableView<DataModel> userTable;
    @FXML
    private TableColumn<DataModel, String> id;
    @FXML
    private TableColumn<DataModel, String> voornaam;
    @FXML
    private TableColumn<DataModel, String> achternaam;
    @FXML
    private TableColumn<DataModel, String> gebruikersnaam;
    @FXML
    private TableColumn<DataModel, String> wachtwoord;
    @FXML
    private TableColumn<DataModel, String> functie;
    @FXML
    private TextField searchField;
    @FXML
    private Button Toevoegen;
    @FXML
    private Button deleteButton;
    @FXML
    private Button btnLoad;
    @FXML
    private MenuButton optionsMenuBar;
    @FXML
    private MenuItem addNewEntry;
    @FXML
    private MenuItem editExistingEntry;
    @FXML
    private GridPane newEntryDialog;
    @FXML
    private GridPane editExistingEntryDialog;
    @FXML 
    private NewEntryController newEntryDialogController;
    @FXML
    private EditExistingEntryController editExistingEntryDialogController;
    @FXML
    private Button Sluiten;
    
    
  

 
    
    
    public UMController(){}
    

	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
            //Creates columns.
            id.setCellValueFactory(new PropertyValueFactory<DataModel,String>("id"));		
            voornaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("voornaam"));	
            achternaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("achternaam"));	
            gebruikersnaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("gebruikersnaam"));
            wachtwoord.setCellValueFactory(new PropertyValueFactory<DataModel,String>("wachtwoord"));
            functie.setCellValueFactory(new PropertyValueFactory<DataModel,String>("functie"));

            DBConnection conn = new DBConnection();
	
	try{
		
            ObservableList<DataModel> bookData = FXCollections.observableArrayList();	

            //Connects to database.
            Connection myConn = conn.getConnection();
            String query = "SELECT * FROM persoonsgegevens";	
            PreparedStatement pst;
            pst = myConn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
		
        //Iterates through database and inserts information from .csv file into table.
        while (rs.next()) {
        	DataModel book = new DataModel();
            book.setId(rs.getString("id"));
            book.setVoornaam(rs.getString("voornaam"));
            book.setAchternaam(rs.getString("achternaam"));
            book.setGebruikersnaam(rs.getString("gebruikersnaam"));
            book.setWachtwoord(rs.getString("wachtwoord"));
            book.setFunctie(rs.getString("functie"));   
            bookData.add(book);
        	}
        
            userTable.setItems(bookData);
            userTable.setEditable(true);
        
            userTable.setRowFactory( tv -> {
            TableRow<DataModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    DataModel rowData = userTable.getSelectionModel().getSelectedItem();
                    editExistingEntryDialogController.showBook(rowData);
                    handleEdit();
                    
                }
            });
            return row ;
        });
        
        //Implement search function
        FilteredList<DataModel> filteredData = new FilteredList<>(bookData, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } if (data.getVoornaam().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true; 
                } if (data.getAchternaam().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getGebruikersnaam().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getWachtwoord().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getFunctie().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } else{
                	return false;
                }
            });
        });
        SortedList<DataModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(userTable.comparatorProperty());
        userTable.setItems(sortedData); 
		}
		
        catch (SQLException | ClassNotFoundException e){
        	System.out.println(e.getMessage());
        } 
			     
	}
        
    
    public void load(URL location, ResourceBundle resources) {
    	
		//Creates columns.
		id.setCellValueFactory(new PropertyValueFactory<DataModel,String>("id"));		
		voornaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("voornaam"));	
		achternaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("achternaam"));	
		gebruikersnaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("gebruikersnaam"));
		wachtwoord.setCellValueFactory(new PropertyValueFactory<DataModel,String>("wachtwoord"));
		functie.setCellValueFactory(new PropertyValueFactory<DataModel,String>("functie"));
		
		DBConnection conn = new DBConnection();
	
        try{
		
	    ObservableList<DataModel> bookData = FXCollections.observableArrayList();	
		
            //Connects to database.
            Connection myConn = conn.getConnection();
            String query = "SELECT * FROM persoonsgegevens";	
            PreparedStatement pst;
            pst = myConn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
		
        //Iterates through database and inserts information from .csv file into table.
        while (rs.next()) {
        	DataModel book = new DataModel();
            book.setId(rs.getString("id"));
            book.setVoornaam(rs.getString("voornaam"));
            book.setAchternaam(rs.getString("achternaam"));
            book.setGebruikersnaam(rs.getString("gebruikersnaam"));
            book.setWachtwoord(rs.getString("wachtwoord"));
            book.setFunctie(rs.getString("functie"));   
            bookData.add(book);
        	}
        
            userTable.setItems(bookData);
            userTable.setEditable(true);
        
            userTable.setRowFactory( tv -> {
            TableRow<DataModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    DataModel rowData = userTable.getSelectionModel().getSelectedItem();
                    editExistingEntryDialogController.showBook(rowData);
                    handleEdit();
                    
                }
            });
            return row ;
        });

        //Implement search function
        FilteredList<DataModel> filteredData = new FilteredList<>(bookData, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } if (data.getVoornaam().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true; 
                } if (data.getAchternaam().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getGebruikersnaam().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getWachtwoord().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getFunctie().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } else{
                	return false;
                }
            });
        });
        SortedList<DataModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(userTable.comparatorProperty());
        userTable.setItems(sortedData); 
		}
		
        catch (SQLException | ClassNotFoundException e){
        	System.out.println(e.getMessage());
        } 
			     
	}
	
    @FXML
    private void deleteEntry(ActionEvent e){
            
		DBConnection conn = new DBConnection();
		DataModel data = userTable.getSelectionModel().getSelectedItem();
		
		if(data != null){
			
			try{
				Connection myConn = conn.getConnection();
				String query = "DELETE FROM persoonsgegevens WHERE id = '"+data.getId()+"'";	
				PreparedStatement pst;
				pst = myConn.prepareStatement(query);
				pst.executeUpdate();
				userTable.getSelectionModel().clearSelection();
			
			} catch (Exception ex){
				System.out.println(ex);
			}
		}
		
                if (data == null){
                    // Nothing selected.
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.initOwner(Main.getStage());
                    alert.setTitle("Niets Geselecteerd");
                    alert.setHeaderText("Geen rij geselecteerd");
                    alert.setContentText("U moet als eerst een rij kiezen.");
                    alert.showAndWait();
                }
		
	}
    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
        
        
                id.setCellValueFactory(new PropertyValueFactory<DataModel,String>("id"));		
		voornaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("voornaam"));	
		achternaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("achternaam"));	
		gebruikersnaam.setCellValueFactory(new PropertyValueFactory<DataModel,String>("gebruikersnaam"));
		wachtwoord.setCellValueFactory(new PropertyValueFactory<DataModel,String>("wachtwoord"));
		functie.setCellValueFactory(new PropertyValueFactory<DataModel,String>("functie"));
       		DBConnection conn = new DBConnection();
	
		try{
		
                    ObservableList<DataModel> bookData = FXCollections.observableArrayList();	

                    //Connects to database.
                    Connection myConn = conn.getConnection();
                    String query = "SELECT * FROM persoonsgegevens";	
                    PreparedStatement pst;
                    pst = myConn.prepareStatement(query);
                    ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    DataModel book = new DataModel();
                    book.setId(rs.getString("id"));
                    book.setVoornaam(rs.getString("voornaam"));
                    book.setAchternaam(rs.getString("achternaam"));
                    book.setGebruikersnaam(rs.getString("gebruikersnaam"));
                    book.setWachtwoord(rs.getString("wachtwoord"));
                    book.setFunctie(rs.getString("functie"));   
                    bookData.add(book);
                }
           
                userTable.setItems(bookData);
            

                } catch (SQLException ex) {
                    System.err.println("Error"+ex);
                }   catch (ClassNotFoundException ex) {
                Logger.getLogger(UMController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
      
    }
    

        //Builds window.
	public void handleAddNewEntryMenuItem(ActionEvent event){
		try{
			GridPane root = FXMLLoader.load(getClass().getResource("NewEntryDialog.fxml"));	
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	      
	
    //Sets the New Entry menu item with its proper controller.
    @FXML
    public void showNewEntry(ActionEvent e){
		newEntryDialogController.handleAddNewEntryMenuItem(newEntryDialog);
    }
	
    //Handle editing entry
    @FXML
    public void handleEdit(){
	    	editExistingEntryDialogController.handleEditExistingEntryMenuItem(editExistingEntryDialog);
	    	
    }
	

}
        



	
	

	
		

