package com.mybaggage.old.jordy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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


public class FXMLController implements Initializable {

    final String DEFAULT_STRING = "";
    final int DEFAULT_INTEGER = 1;

    @FXML
    private JFXTextField txtTijd;

    @FXML
    private JFXDatePicker txtDatum;

    @FXML
    private JFXTextField txtNaam;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtMerk;

    @FXML
    private JFXTextField txtLuchthaven;

    @FXML
    private JFXTextField txtSchade;

    @FXML
    private JFXTextField txtVergoeding;

    @FXML
    private JFXTextField txtBankrekening;

    @FXML
    private JFXTextField txtLostAndFoundID;

    @FXML
    private JFXButton btn_GoToZoekResultaten;

    @FXML
    private JFXButton btn_RegistreerSchadevergoeding;

    @FXML
    private ImageView btn_GoToVerlorenBagageRegistratie;

    @FXML
    private void goToZoekResultaten(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
        if (event.getTarget() == btn_GoToZoekResultaten) {
            Utilities.root = FXMLLoader.load(getClass().getResource("/fxml/Test Case.fxml"));

            // Maak verbinding met database
            Utilities.setMySQLConnectionParameters("SchadeFormulier", "root", "admin");
            Utilities.databaseConnection.getConnection();

            // Test Case waarden:
            int aantalResultaten = 3;
            String[] types = {"Zakenkoffers", "Handbagage koffers", "Kinderkoffers"};
            String[] tijdstippen = {"10:15", "18:10", "07:15"};
            String[] namen = {"John Doe", "jack Jones", "Jordy"};
            String[] ids = {"1", "2", "3"};
            String[] datums = {"27-11-2017", "25-11-2017", "10-11-2017"};
            String[] merken = {"Samsonite", "CarryOn", "Titan"};
            String[] luchthavens = {"Amsterdam", "Japan", "Engeland"};
            String[] schades = {"Kwijt", "Missende onderdelen", "Spullen Missen"};
            String[] vergoedingen = {"€200", "€100", "€450"};
            String[] bankrekeningen = {"nl73INGB000023942913", "nl73INGB00002234343", "nl73INGB000027292913"};
            // Eind van Test Case waarden.

            // Begin van de Test Case:
            // Get opmaak
            Pane[] sjablonen = getZoekResultatenOpmaak(aantalResultaten, types, namen, ids, tijdstippen, datums, merken, luchthavens, schades, vergoedingen, bankrekeningen);

            // Genereer/toon de opmaak
            genereerZoekResultaten(sjablonen, event);
            // Eind van de Test Case.
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
            String[] schades,
            String[] namen,
            String[] ids,
            String[] vergoedingen,
            String[] bankrekeningen) {
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
        Label schade;
        Label vergoeding;
        Label bankrekening;
        Label naam;
        Label lostAndFoundID;
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
            
            // set naam
            naam = new Label();
            naam.setText(namen[kofferIndex]);
            naam.setLayoutX(49);
            naam.setLayoutY(170);
            sjablonen[kofferIndex].getChildren().add(naam);
            
            // set lostAndFoundID
            lostAndFoundID = new Label();
            lostAndFoundID.setText(ids[kofferIndex]);
            lostAndFoundID.setLayoutX(67);
            lostAndFoundID.setLayoutY(100);
            sjablonen[kofferIndex].getChildren().add(lostAndFoundID);
                        
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

            // Set schade
            schade = new Label();
            schade.setText(schades[kofferIndex]);
            schade.setLayoutX(176);
            schade.setLayoutY(97);
            sjablonen[kofferIndex].getChildren().add(schade);

            //set vergoedingen
            vergoeding = new Label();
            vergoeding.setText(vergoedingen[kofferIndex]);
            vergoeding.setLayoutX(176);
            vergoeding.setLayoutY(190);
            sjablonen[kofferIndex].getChildren().add(vergoeding);

            //set banrekening
            bankrekening = new Label();
            bankrekening.setText(bankrekeningen[kofferIndex]);
            bankrekening.setLayoutX(176);
            bankrekening.setLayoutY(213);
            sjablonen[kofferIndex].getChildren().add(bankrekening);

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
    private Schaderegistratie vulSchadeFormulierIn() {
        Schaderegistratie schadeFormulier = new Schaderegistratie();
        
        schadeFormulier.setLostAndFoundID(txtLostAndFoundID.getText());
        schadeFormulier.setBankrekening(txtBankrekening.getText());
        schadeFormulier.setDatum(txtDatum.getPromptText());
        schadeFormulier.setTijd(txtTijd.getText());
        schadeFormulier.setLuchthaven(txtLuchthaven.getText());
        schadeFormulier.setNaam(txtNaam.getText());
        schadeFormulier.setSchade(txtSchade.getText());
        schadeFormulier.setVergoeding(txtVergoeding.getText());
        schadeFormulier.setType(txtType.getText());
        schadeFormulier.setMerk(txtMerk.getText());

        return schadeFormulier;
    }

    @FXML
    private void insertSchadeFormulier(MouseEvent event) throws ClassNotFoundException, SQLException, ParseException {
        if (event.getTarget() == btn_RegistreerSchadevergoeding) {

            Schaderegistratie schadeFormulier = vulSchadeFormulierIn();

            String query = "INSERT INTO schade (lostandfoundID, type, datum, tijd, luchthaven, bankrekening, merk, vergoeding, schade, naam)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                // Maak verbinding met database
                Utilities.setMySQLConnectionParameters("SchadeFormulier", "root", "admin");
                Utilities.databaseConnection.getConnection();

                Utilities.preparedStatement = Utilities.databaseConnection.connection.prepareStatement(query);
                Utilities.preparedStatement.setInt(1, schadeFormulier.getLostAndFoundID());
                Utilities.preparedStatement.setString(2, schadeFormulier.getBankrekening());
                Utilities.preparedStatement.setString(3, schadeFormulier.getDatum());
                Utilities.preparedStatement.setString(4, schadeFormulier.getTijd());
                Utilities.preparedStatement.setString(5, schadeFormulier.getLuchthaven());
                Utilities.preparedStatement.setString(6, schadeFormulier.getNaam());
                Utilities.preparedStatement.setString(7, schadeFormulier.getSchade());
                Utilities.preparedStatement.setString(8, schadeFormulier.getVergoeding());
                Utilities.preparedStatement.setString(9, schadeFormulier.getType());
                Utilities.preparedStatement.setString(10, schadeFormulier.getMerk());
                
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
        Utilities.databaseConnection = new DatabaseConnection();
    }
}
