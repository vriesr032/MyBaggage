/*package com.mybaggage.old.mitchell;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mybaggage.Main;
import com.mybaggage.controllers.MainController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegistratieSysteem implements Initializable {

    final String DEFAULT_STRING = "";
    final int DEFAULT_INTEGER = 1;

    @FXML
    private JFXTextField txtFormuliernummer;

    @FXML
    private JFXTextField txtLostAndFoundID;

    @FXML
    private JFXTextField txtLabelnummer;

    @FXML
    private JFXTextField txtVluchtnummer;

    @FXML
    private JFXTextField txtKlantnummer;

    @FXML
    private JFXTextField txtTijd;

    @FXML
    private JFXDatePicker txtDatum;

    @FXML
    private JFXTextField txtNaam;

    @FXML
    private JFXTextField txtAdres;

    @FXML
    private JFXTextField txtWoonplaats;

    @FXML
    private JFXTextField txtPostcode;

    @FXML
    private JFXTextField txtLand;

    @FXML
    private JFXTextField txtTelefoonnummer;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtMerk;

    @FXML
    private JFXTextField txtKleur;

    @FXML
    private JFXTextArea txtKenmerken;

    @FXML
    private JFXTextField txtBestemming;

    @FXML
    private JFXTextField txtLuchthaven;

    @FXML
    private JFXButton btn_GoToZoekResultaten;

    @FXML
    private JFXButton btn_RegistreerGevondenBagage;

    @FXML
    private ImageView btn_GoToVerlorenBagageRegistratie;

    @FXML
    private void goToZoekResultaten(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
        if (event.getTarget() == btn_GoToZoekResultaten) {
            Utilities.root = FXMLLoader.load(getClass().getResource("/fxml/Test Case.fxml"));

            // Maak verbinding met database
            Utilities.setMySQLConnectionParameters("bagageregistratie", "root", "Nightfeather007!");
            Utilities.databaseConnection.getConnection();

            // !!!Test Case values:
            int aantalResultaten = 3;
            String[] types = {"Zakenkoffers", "Handbagage koffers", "Kinderkoffers"};
            String[] tijdstippen = {"10:15", "18:10", "07:15"};
            String[] datums = {"27-11-2017", "25-11-2017", "10-11-2017"};
            String[] merken = {"Samsonite", "CarryOn", "Titan"};
            String[] luchthavens = {"Amsterdam", "Japan", "Engeland"};
            String[] kleuren = {"Rood", "Geel", "Paars"};
            // !!!End of Test Case values.

            // !!!Begin of the Test Case:
            // Get opmaak
            Pane[] sjablonen = getZoekResultatenOpmaak(aantalResultaten, types, tijdstippen, datums, merken, luchthavens, kleuren);

            // Genereer/toon de opmaak
            genereerZoekResultaten(sjablonen, event);

            // !!!End of the Test Case.
           
        }
    }

    @FXML
    private void goToVerlorenBagageRegistratie(MouseEvent event) throws IOException {
        if (event.getTarget() == btn_GoToVerlorenBagageRegistratie) {
            Utilities.root = FXMLLoader.load(getClass().getResource("/fxml/VerlorenBagageRegistratie.fxml"));
            Utilities.setStage(Utilities.root, event);
        }
    }

    @FXML
    private Pane[] getZoekResultatenOpmaak(
            int aantalResultaten,
            String[] types,
            String[] tijdstippen,
            String[] datums,
            String[] merken,
            String[] luchthavens,
            String[] kleuren) {
        Pane[] sjablonen = new Pane[aantalResultaten];

        // Initialiseer de beginwaardes van de sjabloon's attributen
        int lengteSjabloon = 65;
        int breedteSjabloon = 1366;
        int xCoördinaatSjabloon = 0;
        int yCoördinaatSjabloon = 46;

        // Initialiseer de opmaak objecten
        Label type;
        Label tijdstip;
        Label datum;
        Label merk;
        Label luchthaven;
        Label kleur;
        Button claimKoffer;
        Line scheidingslijn;

        // Vul de sjablonen in
        for (int kofferIndex = 0; kofferIndex < aantalResultaten; kofferIndex++) {
            // Creëer sjabloon
            sjablonen[kofferIndex] = new Pane();
            sjablonen[kofferIndex].setPrefSize(lengteSjabloon, breedteSjabloon);
            sjablonen[kofferIndex].setLayoutX(xCoördinaatSjabloon);
            sjablonen[kofferIndex].setLayoutY(yCoördinaatSjabloon);

            // Set type
            type = new Label();
            type.setText(types[kofferIndex]);
            type.setFont(Font.font("System", FontWeight.BOLD, 13));
            type.setLayoutX(49);
            type.setLayoutY(50);
            sjablonen[kofferIndex].getChildren().add(type);

            // Set tijdstip
            tijdstip = new Label();
            tijdstip.setText(tijdstippen[kofferIndex]);
            tijdstip.setLayoutX(49);
            tijdstip.setLayoutY(67);
            sjablonen[kofferIndex].getChildren().add(tijdstip);

            // Set datum
            datum = new Label();
            datum.setText(datums[kofferIndex]);
            datum.setLayoutX(49);
            datum.setLayoutY(82);
            sjablonen[kofferIndex].getChildren().add(datum);

            // Set merk
            merk = new Label();
            merk.setText(merken[kofferIndex]);
            merk.setLayoutX(49);
            merk.setLayoutY(97);
            sjablonen[kofferIndex].getChildren().add(merk);

            // Set luchthaven
            luchthaven = new Label();
            luchthaven.setText(luchthavens[kofferIndex]);
            luchthaven.setLayoutX(176);
            luchthaven.setLayoutY(82);
            sjablonen[kofferIndex].getChildren().add(luchthaven);

            // Set kleur
            kleur = new Label();
            kleur.setText(kleuren[kofferIndex]);
            kleur.setLayoutX(176);
            kleur.setLayoutY(97);
            sjablonen[kofferIndex].getChildren().add(kleur);

            // Set button
            claimKoffer = new Button();
            claimKoffer.setText("Claim");
            claimKoffer.setTextFill(Color.WHITE);
            claimKoffer.setLayoutX(1216);
            claimKoffer.setLayoutY(60);
            claimKoffer.setPrefSize(90, 45);
            sjablonen[kofferIndex].getChildren().add(claimKoffer);

            // Set scheidingslijn
            scheidingslijn = new Line();
            scheidingslijn.setLayoutX(149);
            scheidingslijn.setLayoutY(127);
            scheidingslijn.setStartX(-150);
            scheidingslijn.setEndX(1200);
            sjablonen[kofferIndex].getChildren().add(scheidingslijn);

            // Verander de positie van de sjabloon voor een consistente opmaak
            yCoördinaatSjabloon += 100;
        }
        return sjablonen;
    }

    @FXML
    private void genereerZoekResultaten(Pane[] sjablonen, MouseEvent event) {
        Utilities.rootPane = new Pane();
        Utilities.rootPane.getChildren().addAll(Arrays.asList(sjablonen));

        // Set limiet aan scroll ruimte in de zoekresultaten overzicht
        Utilities.rootPane.setPrefSize(1366, 800);

        Utilities.rootScrollPane = new ScrollPane();
        Utilities.rootScrollPane.setContent(Utilities.rootPane);

        // Schakel horizontale scroll bar 
        Utilities.rootScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Utilities.setStage(Utilities.rootScrollPane, event);
    }

    @FXML
    private Bagageregistratie vulGevondenBagageFormulierIn() {
        Bagageregistratie gevondenBagageFormulier = new Bagageregistratie();

        gevondenBagageFormulier.setDatum(txtDatum.getPromptText());
        gevondenBagageFormulier.setTijd(txtTijd.getText());
        gevondenBagageFormulier.setLuchthaven(txtLuchthaven.getText());
        gevondenBagageFormulier.setLabelnummer(txtLabelnummer.getText());
        gevondenBagageFormulier.setVluchtnummer(txtVluchtnummer.getText());
        gevondenBagageFormulier.setBestemming(txtBestemming.getText());
        gevondenBagageFormulier.setLostAndFoundID(txtLostAndFoundID.getText());
        gevondenBagageFormulier.setType(txtType.getText());
        gevondenBagageFormulier.setMerk(txtMerk.getText());
        gevondenBagageFormulier.setKleur(txtKleur.getText());
        gevondenBagageFormulier.setKenmerken(txtKenmerken.getText());

        return gevondenBagageFormulier;
    }

    @FXML
    private void insertGevondenBagageFormulier(MouseEvent event) throws ClassNotFoundException, SQLException, ParseException {
        if (event.getTarget() == btn_RegistreerGevondenBagage) {

            Bagageregistratie gevondenBagageFormulier = vulGevondenBagageFormulierIn();

            String query = "INSERT INTO registratie (naam, adres, woonplaats, postcode, land, telefoonnummer, type, "
                    + "merk, kleur, kenmerken, labelnummer, vluchtnummer, bestemming, tijd, datum, luchthaven, lostandfoundID)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                // Maak verbinding met database
                Utilities.setMySQLConnectionParameters("bagageregistratie", "root", "root123");
                Utilities.databaseConnection.getConnection();

                Utilities.preparedStatement = Utilities.databaseConnection.connection.prepareStatement(query);
                Utilities.preparedStatement.setString(1, DEFAULT_STRING);
                Utilities.preparedStatement.setString(2, DEFAULT_STRING);
                Utilities.preparedStatement.setString(3, DEFAULT_STRING);
                Utilities.preparedStatement.setString(4, DEFAULT_STRING);
                Utilities.preparedStatement.setString(5, DEFAULT_STRING);
                Utilities.preparedStatement.setString(6, DEFAULT_STRING);
                Utilities.preparedStatement.setString(7, gevondenBagageFormulier.getType());
                Utilities.preparedStatement.setString(8, gevondenBagageFormulier.getMerk());
                Utilities.preparedStatement.setString(9, gevondenBagageFormulier.getKleur());
                Utilities.preparedStatement.setString(10, gevondenBagageFormulier.getKenmerken());
                Utilities.preparedStatement.setInt(11, gevondenBagageFormulier.getLabelnummer());
                Utilities.preparedStatement.setInt(12, gevondenBagageFormulier.getVluchtnummer());
                Utilities.preparedStatement.setString(13, gevondenBagageFormulier.getBestemming());
                Utilities.preparedStatement.setString(14, gevondenBagageFormulier.getTijd());
                Utilities.preparedStatement.setString(15, gevondenBagageFormulier.getDatum());
                Utilities.preparedStatement.setString(16, gevondenBagageFormulier.getLuchthaven());
                Utilities.preparedStatement.setInt(17, gevondenBagageFormulier.getLostAndFoundID());

                Utilities.preparedStatement.executeUpdate();

                // Sluit de verbinding met de database
                Utilities.databaseConnection.closeConnection();
            } catch (ClassNotFoundException | SQLException mySQLException) {
                throw mySQLException;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.getScene().getStylesheets().add("/styles/StylesMitchell.css");
        
        Utilities.databaseConnection = new DatabaseConnection();
    }
}*/
