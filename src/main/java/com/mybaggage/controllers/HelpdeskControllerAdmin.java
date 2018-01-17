package com.mybaggage.controllers;

import com.mybaggage.old.ismail.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Deze controller zorgt ervoor dat admins de helpdesk kunnen gebruiken.
 *
 * @author Ismail Bahar (500783727)
 */
public class HelpdeskControllerAdmin implements Initializable {

    @FXML
    private GridPane newEntryDialog;
    @FXML
    private TableView<HelpdeskDetails> tableUser;
    @FXML
    private TableColumn columnIdTicket;
    @FXML
    private TableColumn columnVoornaam;
    @FXML
    private TableColumn columnAchternaam;
    @FXML
    private TableColumn columnDatum;
    @FXML
    private TableColumn columnToegewezenAan;
    @FXML
    private TableColumn columnBeschrijving;
    @FXML
    private TextField idTicketTextField;
    @FXML
    private TextField voornaamTextField;
    @FXML
    private TextField achternaamTextField;
    @FXML
    private DatePicker datumTextField;
    @FXML
    private TextField toegewezenAanTextField;
    @FXML
    private TextField beschrijvingTextField;
    @FXML
    private Button addTicket;
    @FXML
    private Button closeTicket;
    private ObservableList<HelpdeskDetails> data; //A list that allows listeners to track changes when they occur.
    private DbConnection dc;
    AddTickets data2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
    }

    //Methode om de pop-up window te openen en sluiten.
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == addTicket) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("AddTicketAdmin.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(addTicket.getScene().getWindow());
            stage.showAndWait();
        } else {
            stage = (Stage) closeTicket.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void loadDataFromDatabase() {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            //SQL Query 1 pakt alles van tabel: ticket.
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM bagage_registratie.ticket");
            while (rs.next()) {
                //Pakt alle gegevens uit de database.
                data.add(new HelpdeskDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //Set functie naar de table selecteert elke kolom stap voor stap.
        columnIdTicket.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
        columnVoornaam.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        columnAchternaam.setCellValueFactory(new PropertyValueFactory<>("achternaam"));
        columnDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        columnToegewezenAan.setCellValueFactory(new PropertyValueFactory<>("toegewezenAan"));
        columnBeschrijving.setCellValueFactory(new PropertyValueFactory<>("beschrijving"));

        tableUser.setItems(null);
        tableUser.setItems(data); //Zet alle data erin.

    }

    @FXML
    private void ticketToevoegen() {

        DbConnection conn = new DbConnection();

        String idTicket = idTicketTextField.getText();
        String voornaam = voornaamTextField.getText();
        String achternaam = achternaamTextField.getText();
        LocalDate datum = datumTextField.getValue();
        String toegewezenAan = toegewezenAanTextField.getText();
        String beschrijving = beschrijvingTextField.getText();
        //SQL Query 2 data toevoegen.
        String query = "INSERT INTO bagage_registratie.ticket (idTicket, voornaam, achternaam, datum, toegewezen_aan, beschrijving) VALUES (' " + idTicket + "','" + voornaam + "','" + achternaam + "','" + datum + "','" + toegewezenAan + "','" + beschrijving + "')";

        PreparedStatement pst;

        try {

            Connection myConn = conn.getConnection();
            pst = myConn.prepareStatement(query);
            pst.executeUpdate();

            data2.setId(idTicket);
            data2.setVoornaam(voornaam);
            data2.setAchternaam(achternaam);
            data2.setDatum(datum);
            data2.setToegewezenAan(toegewezenAan);
            data2.setBeschrijving(beschrijving);
            //Zet alle gegevens weer in de tabel.

        } catch (Exception ex) {
            System.out.println(ex);
        }

        Stage stage = (Stage) newEntryDialog.getScene().getWindow();
        stage.close();
    }

    //Methode om tickets te verwijderen. 
    @FXML
    private void verwijderTicket() {
        DbConnection conn = new DbConnection();
        HelpdeskDetails data = tableUser.getSelectionModel().getSelectedItem();

        if (data != null) {

            try {
                Connection myConn = conn.getConnection();
                //SQL Query 3 verwijdert een rij gebaseerd op de id van een ticket.
                String query = "DELETE FROM bagage_registratie.ticket WHERE idTicket = '" + data.getIdTicket() + "'";

                PreparedStatement pst;
                pst = myConn.prepareStatement(query);
                pst.executeUpdate();

                tableUser.getSelectionModel().clearSelection(); //Pakt het model van de tabel en verwijdert het geselecteerde.

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

}
