package com.mybaggage.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mybaggage.Database;
import com.mybaggage.Main;
import com.mybaggage.models.Bagageregistratie;
import com.mybaggage.Utilities;
import com.mybaggage.models.BagageFormulierQueryBuilder;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegistratieController implements Initializable {

    final String DEFAULT_STRING = "";
    final int DEFAULT_INTEGER = 1;

    private ArrayList<Button> lijstVanButtons;
    private ArrayList<Integer> lijstVanFormuliernummers;
    private ArrayList<String> lijstVanLuchthavens;
    private ArrayList<String> lijstVanTypes;
    private ArrayList<String> lijstVanMerken;
    private ArrayList<String> lijstVanKleuren;
    private ArrayList<String> lijstVanTijdstippen;
    private ArrayList<String> lijstVanDatums;

    @FXML
    private AnchorPane vermisteBagageFormulierPane;

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
    private JFXButton btn_GetZoekResultaten;

    @FXML
    private JFXButton btn_RegistreerGevondenBagage;

    @FXML
    private void ShowZoekResultaten(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        if (event.getTarget() == btn_GetZoekResultaten) {
            Bagageregistratie ingevuldeVermisteBagageFormulier = vulVermisteBagageFormulierIn();
            // Vul de vermistebagageformulier in en verzamel relevante zoekresultaten
            getZoekResultaten(ingevuldeVermisteBagageFormulier);

            // Get de opmaak met behulp van de zoekresultaten
            Pane[] sjablonen = CreateZoekResultatenLijst();

            // Genereer de zoekresultaten
            genereerZoekResultaten(sjablonen);

            //claimVermisteBagage(ingevuldeVermisteBagageFormulier);
        }
    }

    @FXML
    private Bagageregistratie vulVermisteBagageFormulierIn() {
        Bagageregistratie vermisteBagageFormulier = new Bagageregistratie();

        vermisteBagageFormulier.setDatum(txtDatum.getValue());
        vermisteBagageFormulier.setLuchthaven(txtLuchthaven.getText());
        vermisteBagageFormulier.setLabelnummer(txtLabelnummer.getText());
        vermisteBagageFormulier.setVluchtnummer(txtVluchtnummer.getText());
        vermisteBagageFormulier.setBestemming(txtBestemming.getText());
        vermisteBagageFormulier.setNaam(txtNaam.getText());
        vermisteBagageFormulier.setAdres(txtAdres.getText());
        vermisteBagageFormulier.setWoonplaats(txtWoonplaats.getText());
        vermisteBagageFormulier.setPostcode(txtPostcode.getText());
        vermisteBagageFormulier.setLand(txtLand.getText());
        vermisteBagageFormulier.setTelefoonnummer(txtTelefoonnummer.getText());
        vermisteBagageFormulier.setType(txtType.getText());
        vermisteBagageFormulier.setMerk(txtMerk.getText());
        vermisteBagageFormulier.setKleur(txtKleur.getText());
        vermisteBagageFormulier.setKenmerken(txtKenmerken.getText());

        return vermisteBagageFormulier;
    }

    @FXML
    private void getZoekResultaten(Bagageregistratie vermisteBagageFormulier) throws SQLException {
        String query = "SELECT * FROM registratie";
        try {

            // Bouw een dynamische WHERE query die zichzelf opbouwt aan de hand van wat de gebruiker wilt zoeken
            BagageFormulierQueryBuilder bagageFormulierQueryBuilder = new BagageFormulierQueryBuilder();
            query = bagageFormulierQueryBuilder.buildWhereQuery(query, vermisteBagageFormulier);
            Utilities.preparedStatement = Database.connectdb().prepareStatement(query);
            bagageFormulierQueryBuilder.setWhereQueryWaardes(Utilities.preparedStatement);
            Utilities.resultSet = Utilities.preparedStatement.executeQuery();

            // For testing purposes only, to see how many matches are generated per request
            //int matches = 0;
            // Haal de relevante data op op basis van de kolomnaam
            while (Utilities.resultSet.next()) {
                //matches++;
                lijstVanLuchthavens.add(Utilities.resultSet.getString("luchthaven"));
                lijstVanTypes.add(Utilities.resultSet.getString("type"));
                lijstVanMerken.add(Utilities.resultSet.getString("merk"));
                lijstVanKleuren.add(Utilities.resultSet.getString("kleur"));
                lijstVanDatums.add(Utilities.resultSet.getString("datum"));
                lijstVanTijdstippen.add(Utilities.resultSet.getString("tijd"));
                //lijstVanFormuliernummers.add(Utilities.resultSet.getInt("formuliernummer"));
            }

            // Show amount of matches that were generated
            //Optional<ButtonType> alert2 = new Alert(Alert.AlertType.ERROR, "Matches: " + matches).showAndWait();
        } catch (SQLException mySQLException) {
            throw mySQLException;
        }
    }

    @FXML
    private Pane[] CreateZoekResultatenLijst() {
        Pane[] sjablonen = new Pane[lijstVanLuchthavens.size()];

        // Initialiseer de beginwaardes van de sjabloon's attributen
        int lengteSjabloon = 675;
        int breedteSjabloon = 1100;
        int xCoördinaatSjabloon = 0;
        int yCoördinaatSjabloon = 20;

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
        for (int kofferIndex = 0; kofferIndex < lijstVanLuchthavens.size(); kofferIndex++) {
            // Creëer sjabloon
            sjablonen[kofferIndex] = new Pane();
            sjablonen[kofferIndex].setPrefSize(lengteSjabloon, breedteSjabloon);
            sjablonen[kofferIndex].setLayoutX(xCoördinaatSjabloon);
            sjablonen[kofferIndex].setLayoutY(yCoördinaatSjabloon);

            // Set type
            type = new Label();
            type.setText("Type: " + lijstVanTypes.get(kofferIndex));
            type.setFont(Font.font("System", FontWeight.BOLD, 13));
            type.setLayoutX(49);
            type.setLayoutY(20);
            sjablonen[kofferIndex].getChildren().add(type);

            // Set tijdstip
            tijdstip = new Label();
            tijdstip.setText("Tijd: " + lijstVanTijdstippen.get(kofferIndex));
            tijdstip.setLayoutX(49);
            tijdstip.setLayoutY(37);
            sjablonen[kofferIndex].getChildren().add(tijdstip);

            // Set datum
            datum = new Label();
            datum.setText("Datum: " + lijstVanDatums.get(kofferIndex));
            datum.setLayoutX(49);
            datum.setLayoutY(52);
            sjablonen[kofferIndex].getChildren().add(datum);

            // Set merk
            merk = new Label();
            merk.setText("Merk: " + lijstVanMerken.get(kofferIndex));
            merk.setLayoutX(49);
            merk.setLayoutY(67);
            sjablonen[kofferIndex].getChildren().add(merk);

            // Set luchthaven
            luchthaven = new Label();
            luchthaven.setText("Luchthaven: " + lijstVanLuchthavens.get(kofferIndex));
            luchthaven.setLayoutX(176);
            luchthaven.setLayoutY(52);
            sjablonen[kofferIndex].getChildren().add(luchthaven);

            // Set kleur
            kleur = new Label();
            kleur.setText("Kleur: " + lijstVanKleuren.get(kofferIndex));
            kleur.setLayoutX(176);
            kleur.setLayoutY(67);
            sjablonen[kofferIndex].getChildren().add(kleur);

            // Set button
            claimKoffer = new Button();
            claimKoffer.setText("Claim");
            claimKoffer.setTextFill(Color.WHITE);
            claimKoffer.setLayoutX(950);
            claimKoffer.setLayoutY(30);
            claimKoffer.setPrefSize(90, 45);
            sjablonen[kofferIndex].getChildren().add(claimKoffer);
            //lijstVanButtons.add(claimKoffer);

            // Set scheidingslijn
            scheidingslijn = new Line();
            scheidingslijn.setLayoutX(149);
            scheidingslijn.setLayoutY(97);
            scheidingslijn.setStartX(-150);
            scheidingslijn.setEndX(944);
            sjablonen[kofferIndex].getChildren().add(scheidingslijn);

            // Verander de positie van de sjabloon voor een consistente opmaak
            yCoördinaatSjabloon += 100;
        }
        return sjablonen;
    }

    @FXML
    private void genereerZoekResultaten(Pane[] sjablonen) throws IOException {
        // Voeg de ingevulde sjablonen toe aan een pane
        Utilities.rootPane = new Pane();
        Utilities.rootPane.getChildren().addAll(Arrays.asList(sjablonen));

        // Set een limiet aan de scroll ruimte in de zoekresultaten overzicht
        Utilities.rootPane.setPrefSize(1100, 675);

        // Voeg de pane toe aan de rootscrollpane zodat de gebruiker door de zoekresultaten kan scrollen
        Utilities.rootScrollPane = new ScrollPane();
        Utilities.rootScrollPane.setContent(Utilities.rootPane);

        // Schakel horizontale scroll bar 
        Utilities.rootScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Toon lijst met alle zoekresultaten
        Utilities.switchSchermNaarPane(Utilities.rootScrollPane, vermisteBagageFormulierPane);
    }

    @FXML
    private void claimVermisteBagage(Bagageregistratie vermisteBagageFormulier) throws SQLException {
        // Start alleen de query als de gebruiker op een claim knop heeft geklikt
        boolean isClaimButtonGeklikt = false;
        do {
            for (Button button : lijstVanButtons) {
                if (button.isPressed()) {
                    break;
                }

            }
        } while (isClaimButtonGeklikt == false);

        String query = "INSERT INTO registratie (naam, adres, woonplaats, postcode, land, telefoonnummer WHERE formuliernummer = ?";

        try {
            Utilities.preparedStatement = Database.connectdb().prepareStatement(query);
            Utilities.preparedStatement.setString(1, vermisteBagageFormulier.getNaam());
            Utilities.preparedStatement.setString(2, vermisteBagageFormulier.getAdres());
            Utilities.preparedStatement.setString(3, vermisteBagageFormulier.getWoonplaats());
            Utilities.preparedStatement.setString(4, vermisteBagageFormulier.getPostcode());
            Utilities.preparedStatement.setString(5, vermisteBagageFormulier.getLand());
            Utilities.preparedStatement.setString(6, vermisteBagageFormulier.getTelefoonnummer());
            Utilities.preparedStatement.setInt(7, getFormuliernummerVanBagageDieWordtGeclaimed());

            Utilities.preparedStatement.executeUpdate();

            // Show resultaat
            Optional<ButtonType> alert2 = new Alert(Alert.AlertType.ERROR, "Bagage is geclaimed!").showAndWait();
        } catch (SQLException mySQLException) {
            throw mySQLException;
        }
    }

    @FXML
    private int getFormuliernummerVanBagageDieWordtGeclaimed() {
        int formuliernummerVanBagageDieWordtGeclaimed = 0;
        for (int index = 0; index < lijstVanButtons.size(); index++) {
            if (lijstVanButtons.get(index).isPressed()) {
                formuliernummerVanBagageDieWordtGeclaimed = lijstVanFormuliernummers.get(index);
            }
        }
        return formuliernummerVanBagageDieWordtGeclaimed;
    }

    @FXML
    private void insertGevondenBagageFormulier(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
        if (event.getTarget() == btn_RegistreerGevondenBagage) {

            Bagageregistratie gevondenBagageFormulier = vulGevondenBagageFormulierIn();

            String query = "INSERT INTO registratie (naam, adres, woonplaats, postcode, land, telefoonnummer, type, "
                    + "merk, kleur, kenmerken, labelnummer, vluchtnummer, bestemming, tijd, datum, luchthaven, lostandfoundID)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                Utilities.preparedStatement = Database.connectdb().prepareStatement(query);
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
            } catch (SQLException mySQLException) {
                throw mySQLException;
            }
        }
    }

    @FXML
    private Bagageregistratie vulGevondenBagageFormulierIn() {
        Bagageregistratie gevondenBagageFormulier = new Bagageregistratie();

        gevondenBagageFormulier.setDatum(txtDatum.getValue());
        gevondenBagageFormulier.setTijd(); // Set systeem tijd
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.getScene().getStylesheets().add("/styles/StylesMitchell.css");
        lijstVanLuchthavens = new ArrayList<>();
        lijstVanTypes = new ArrayList<>();
        lijstVanMerken = new ArrayList<>();
        lijstVanKleuren = new ArrayList<>();
        lijstVanTijdstippen = new ArrayList<>();
        lijstVanDatums = new ArrayList<>();
    }
}
