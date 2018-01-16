package com.mybaggage.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Het doel van deze class is om de bagage registratie, zoeken en claimen functioneel te maken
 *
 * @author Mitchell Gordon (500775386)
 */
public class RegistratieController implements Initializable {

    final String DEFAULT_STRING = "";
    final int DEFAULT_INTEGER = 1;
    final int MAX_LENGTE_KENMERKEN = 255;
    final int MAX_LENGTE_POSTCODE = 6;
    final int LAYOUT_X_VAN_SJABLOON = 0;
    final int LAYOUT_X_VAN_TYPE = 50;
    final int LAYOUT_Y_VAN_TYPE = 20;
    final int LAYOUT_X_VAN_TIJDSTIP = 50;
    final int LAYOUT_Y_VAN_TIJDSTIP = 50;
    final int LAYOUT_X_VAN_DATUM = 50;
    final int LAYOUT_Y_VAN_DATUM = 80;
    final int LAYOUT_X_VAN_LABELNUMMER = 50;
    final int LAYOUT_Y_VAN_LABELNUMMER = 110;
    final int LAYOUT_X_VAN_LUCHTHAVEN = 250;
    final int LAYOUT_Y_VAN_LUCHTHAVEN = 50;
    final int LAYOUT_X_VAN_BESTEMMING = 250;
    final int LAYOUT_Y_VAN_BESTEMMING = 80;
    final int LAYOUT_X_VAN_VLUCHTNUMMER = 250;
    final int LAYOUT_Y_VAN_VLUCHTNUMMER = 110;
    final int LAYOUT_X_VAN_MERK = 550;
    final int LAYOUT_Y_VAN_MERK = 50;
    final int LAYOUT_X_VAN_KLEUR = 550;
    final int LAYOUT_Y_VAN_KLEUR = 80;
    final int LAYOUT_X_VAN_KENMERK_LABEL = 725;
    final int LAYOUT_Y_VAN_KENMERK_LABEL = 50;
    final int LAYOUT_X_VAN_KENMERK_TEXT = 725;
    final int LAYOUT_Y_VAN_KENMERK_TEXT = 77;
    final int LAYOUT_X_VAN_CLAIM_BUTTON = 966;
    final int LAYOUT_Y_VAN_CLAIM_BUTTON = 50;
    final int LAYOUT_X_VAN_SCHEIDINGSLIJN = 149;
    final int LAYOUT_Y_VAN_SCHEIDINGSLIJN = 165;
    final int PREF_WIDTH_KENMERK_TEXT = 200;
    final int PREF_HEIGHT_KENMERK_TEXT = 67;
    final int PREF_WIDTH_CLAIM_BUTTON = 90;
    final int PREF_HEIGHT_CLAIM_BUTTON = 45;
    final int PREF_WIDTH_ROOT_SCROLLPANE = 1100;
    final int PREF_HEIGHT_ROOT_SCROLLPANE = 680;
    final int START_X_VAN_SCHEIDINGSLIJN = -150;
    final int END_Y_VAN_SCHEIDINGSLIJN = 1120;
    final int LAYOUT_Y_SJABLOON_INCREMENTER = 200;

    // Mag geen constante zijn omdat deze bij elke iteratie een nieuwe waarde krijgt
    int LAYOUT_Y_VAN_SJABLOON = 20;

    // Houd bij hoeveel matches worden gegeneerd bij een zoeksessie
    private int matches;

    private ArrayList<Integer> lijstVanFormuliernummers;
    private ArrayList<String> lijstVanTypes;
    private ArrayList<String> lijstVanTijdstippen;
    private ArrayList<String> lijstVanDatums;
    private ArrayList<Integer> lijstVanLabelnummers;
    private ArrayList<String> lijstVanLuchthavens;
    private ArrayList<String> lijstVanBestemmingen;
    private ArrayList<String> lijstVanVluchtnummers;
    private ArrayList<String> lijstVanMerken;
    private ArrayList<String> lijstVanKleuren;
    private ArrayList<String> lijstVanKenmerken;

    @FXML
    private AnchorPane vermisteBagageFormulierPane;

    @FXML
    private JFXComboBox luchthavenComboBox;

    @FXML
    private JFXComboBox bestemmingComboBox;

    @FXML
    private JFXComboBox typeComboBox;

    @FXML
    private JFXComboBox merkComboBox;

    @FXML
    private JFXComboBox kleurComboBox;

    @FXML
    private JFXComboBox landComboBox;

    @FXML
    private JFXTextField txtLostAndFoundID;

    @FXML
    private JFXTextField txtLabelnummer;

    @FXML
    private JFXTextField txtVluchtnummer;

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
    private JFXTextField txtTelefoonnummer;

    @FXML
    private JFXTextArea txtKenmerken;

    @FXML
    private JFXButton btn_GetZoekResultaten;

    @FXML
    private JFXButton btn_RegistreerGevondenBagage;

    @FXML
    private boolean isVermisteBagageFormulierGeldig() {
        try {
            // Check of de datum is ingevuld
            if (txtDatum.getValue() == null) {
                Utilities.errorBox("Vul de datum in!");
                return false;
            } // Check of de ingevulde labelnummer alleen cijfers bevat
            else if (!txtLabelnummer.getText().matches("[0-9]+") && !"".equals(txtLabelnummer.getText())) {
                Utilities.errorBox("Labelnummer mag alleen cijfers bevatten!");
                return false;
            } // Check of de ingevulde vluchtnummer alleen cijfers en letters bevat
            else if (!txtVluchtnummer.getText().matches("^[a-zA-Z0-9]*$") && !"".equals(txtLabelnummer.getText())) {
                Utilities.errorBox("Vluchtnummer mag alleen cijfers en letters bevatten, zonder spatie!");
                return false;
            } // Check of de naam is ingevuld
            else if (txtNaam.getText().matches("")) {
                Utilities.errorBox("Naam moet worden ingevuld!");
                return false;
            } // Check of de ingevulde naam alleen letters bevat
            else if (!txtNaam.getText().trim().matches("^[ A-Za-z]+$")) {
                Utilities.errorBox("Naam mag alleen letters bevatten!");
                return false;
            } // Check of het adres is ingevuld
            else if (txtAdres.getText().matches("")) {
                Utilities.errorBox("Adres moet worden ingevuld!");
                return false;
            } // Check of het ingevulde adres alleen cijfers en letters bevat
            else if (!txtAdres.getText().trim().matches("^[a-zA-Z0-9]*$")) {
                Utilities.errorBox("Adres mag alleen cijfers en letters bevatten, zonder spatie!");
                return false;
            } // Check of de woonplaats is ingevuld
            else if (txtWoonplaats.getText().matches("")) {
                Utilities.errorBox("Woonplaats moet worden ingevuld!");
                return false;
            }// Check of de ingevulde woonplaats alleen letters bevat
            else if (!txtWoonplaats.getText().trim().matches("^[ A-Za-z]*$")) {
                Utilities.errorBox("Woonplaats mag alleen letters bevatten!");
                return false;
            } // Check of de postcode is ingevuld
            else if (txtPostcode.getText().matches("")) {
                Utilities.errorBox("Postcode moet worden ingevuld!");
                return false;
            } // Check of de ingevulde postcode alleen cijfers en letters bevat
            else if (!txtPostcode.getText().trim().matches("^[a-zA-Z0-9]*$")) {
                Utilities.errorBox("Postcode mag alleen cijfers en letters bevatten, zonder spatie!");
                return false;
            } // Check of de postcode bestaat 6 karakters
            else if (txtPostcode.getText().length() > MAX_LENGTE_POSTCODE) {
                Utilities.errorBox("Postcode moet bestaan uit 6 karakters, waaronder 4 cijfers en 2 letters!");
                return false;
            }// Check of een land is ingevuld
            else if (landComboBox.getValue() == null) {
                Utilities.errorBox("Land moet worden ingevuld!");
                return false;
            } // Check of de kenmerken text groter is dan is toegestaan
            else if (txtKenmerken.getText().length() > MAX_LENGTE_KENMERKEN) {
                Utilities.errorBox("Kenmerken mag maar 255 woorden lang zijn!");
                return false;
            }// Check of de telefoonnummer is ingevuld
            else if (txtTelefoonnummer.getText().matches("")) {
                Utilities.errorBox("Vul de telefoonnummer in!");
                return false;
            } // Check of de ingevulde telefoonnummer alleen cijfers bevat
            else if (!txtTelefoonnummer.getText().matches("[0-9]+")) {
                Utilities.errorBox("Telefoonnummer mag alleen cijfers bevatten!");
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }

    @FXML
    private boolean isGevondenBagageFormulierGeldig() {
        try {
            // Check of de datum is ingevuld
            if (txtDatum.getValue() == null) {
                Utilities.errorBox("Vul de datum in!");
                return false;
            } // Check of de ingevulde labelnummer alleen cijfers bevat
            else if (!txtLabelnummer.getText().matches("[0-9]+") && !"".equals(txtLabelnummer.getText())) {
                Utilities.errorBox("Labelnummer mag alleen cijfers bevatten!");
                return false;
            } // Check of de ingevulde lost-and-found-id alleen cijfers bevat
            else if (!txtLostAndFoundID.getText().matches("[0-9]+") && !"".equals(txtLostAndFoundID.getText())) {
                Utilities.errorBox("Lost-and-found-id mag alleen cijfers bevatten!");
                return false;
            } // Check of de bijzondere kenmerken text groter is dan is toegestaan
            else if (txtKenmerken.getText().length() > MAX_LENGTE_KENMERKEN) {
                Utilities.errorBox("Bijzondere kenmerken mag maar 255 karakaters lang zijn!");
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }

    @FXML
    private boolean isMatchOnstaan() {
        switch (matches) {
            case 0:
                // Er zijn geen matches gegenereerd
                Utilities.infoBox("Bagage Zoeken", "ALERT", String.format("%d matches!", matches));
                return false;
            case 1:
                // Toon hoeveel matches zijn gegenereerd
                Utilities.infoBox("Bagage Zoeken", "ALERT", String.format("%d match!", matches));
                return true;
            default:
                // Toon hoeveel matches zijn gegenereerd
                Utilities.infoBox("Bagage Zoeken", "ALERT", String.format("%d matches!", matches));
                return true;
        }
    }

    @FXML
    private void ShowZoekResultaten(ActionEvent event) throws IOException, ClassNotFoundException, SQLException, ParseException, InterruptedException {
        if (event.getTarget() == btn_GetZoekResultaten) {
            // Controleer of de vermiste bagage formulier op de juiste manier is ingevuld
            if (isVermisteBagageFormulierGeldig()) {
                // Vul de vermistebagageformulier in
                Bagageregistratie ingevuldeVermisteBagageFormulier = vulVermisteBagageFormulierIn();

                // Verzamel relevante zoekresultaten
                getZoekResultaten(ingevuldeVermisteBagageFormulier);

                // Controleer of er matches zijn gegeneerd
                if (isMatchOnstaan()) {
                    // Get de opmaak met behulp van de zoekresultaten
                    Pane[] sjablonen = CreateZoekResultatenLijst(ingevuldeVermisteBagageFormulier);

                    // Genereer de zoekresultaten
                    genereerZoekResultaten(sjablonen);
                }
            }
        }
    }

    @FXML
    private Bagageregistratie vulVermisteBagageFormulierIn() throws ParseException {
        Bagageregistratie vermisteBagageFormulier = new Bagageregistratie();

        vermisteBagageFormulier.setDatum(txtDatum.getValue());
        vermisteBagageFormulier.setLuchthaven((String) luchthavenComboBox.getValue());
        vermisteBagageFormulier.setLabelnummer(txtLabelnummer.getText());
        vermisteBagageFormulier.setVluchtnummer(txtVluchtnummer.getText());
        vermisteBagageFormulier.setBestemming((String) bestemmingComboBox.getValue());
        vermisteBagageFormulier.setNaam(txtNaam.getText());
        vermisteBagageFormulier.setAdres(txtAdres.getText());
        vermisteBagageFormulier.setWoonplaats(txtWoonplaats.getText());
        vermisteBagageFormulier.setPostcode(txtPostcode.getText());
        vermisteBagageFormulier.setLand((String) landComboBox.getValue());
        vermisteBagageFormulier.setTelefoonnummer(txtTelefoonnummer.getText());
        vermisteBagageFormulier.setType((String) typeComboBox.getValue());
        vermisteBagageFormulier.setMerk((String) merkComboBox.getValue());
        vermisteBagageFormulier.setKleur((String) kleurComboBox.getValue());
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

            // Haal de relevante data op op basis van de kolomnaam
            matches = 0;
            while (Utilities.resultSet.next()) {
                matches++;
                lijstVanFormuliernummers.add(Utilities.resultSet.getInt("formuliernummer"));
                lijstVanTypes.add(Utilities.resultSet.getString("type"));
                lijstVanTijdstippen.add(Utilities.resultSet.getString("tijd"));
                lijstVanDatums.add(Utilities.resultSet.getString("datum"));
                lijstVanLabelnummers.add(Utilities.resultSet.getInt("labelnummer"));
                lijstVanLuchthavens.add(Utilities.resultSet.getString("luchthaven"));
                lijstVanBestemmingen.add(Utilities.resultSet.getString("bestemming"));
                lijstVanVluchtnummers.add(Utilities.resultSet.getString("vluchtnummer"));
                lijstVanMerken.add(Utilities.resultSet.getString("merk"));
                lijstVanKleuren.add(Utilities.resultSet.getString("kleur"));
                lijstVanKenmerken.add(Utilities.resultSet.getString("kenmerken"));
            }
        } catch (SQLException mySQLException) {
            throw mySQLException;
        }
    }

    @FXML
    private Pane[] CreateZoekResultatenLijst(Bagageregistratie vermisteBagageFormulier) {
        // Bereid de sjablonen container voor
        Pane[] sjablonen = new Pane[lijstVanLuchthavens.size()];

        // Bereid de opmaak objecten voor
        Label type;
        Label tijdstip;
        Label datum;
        Label labelnummer;
        Label luchthaven;
        Label bestemming;
        Label vluchtnummer;
        Label merk;
        Label kleur;
        Label kenmerkLabel;
        TextArea kenmerkText;
        Button claimKoffer;
        Line scheidingslijn;

        // Vul de sjablonen in
        for (int kofferIndex = 0; kofferIndex < lijstVanLuchthavens.size(); kofferIndex++) {
            // Creëer sjabloon
            sjablonen[kofferIndex] = new Pane();
            sjablonen[kofferIndex].setLayoutX(LAYOUT_X_VAN_SJABLOON);
            sjablonen[kofferIndex].setLayoutY(LAYOUT_Y_VAN_SJABLOON);

            // Set type
            type = new Label();
            type.setText(lijstVanTypes.get(kofferIndex));
            type.setFont(Font.font("System", FontWeight.BOLD, 15));
            type.setTextFill(Color.RED);
            type.setLayoutX(LAYOUT_X_VAN_TYPE);
            type.setLayoutY(LAYOUT_Y_VAN_TYPE);
            sjablonen[kofferIndex].getChildren().add(type);

            // Set tijdstip
            tijdstip = new Label();
            tijdstip.setText("Tijd: " + lijstVanTijdstippen.get(kofferIndex));
            tijdstip.setLayoutX(LAYOUT_X_VAN_TIJDSTIP);
            tijdstip.setLayoutY(LAYOUT_Y_VAN_TIJDSTIP);
            sjablonen[kofferIndex].getChildren().add(tijdstip);

            // Set datum
            datum = new Label();
            datum.setText("Datum: " + lijstVanDatums.get(kofferIndex));
            datum.setLayoutX(LAYOUT_X_VAN_DATUM);
            datum.setLayoutY(LAYOUT_Y_VAN_DATUM);
            sjablonen[kofferIndex].getChildren().add(datum);

            // Set labelnummer
            labelnummer = new Label();
            labelnummer.setText("Labelnummer: " + lijstVanLabelnummers.get(kofferIndex));
            labelnummer.setLayoutX(LAYOUT_X_VAN_LABELNUMMER);
            labelnummer.setLayoutY(LAYOUT_Y_VAN_LABELNUMMER);
            sjablonen[kofferIndex].getChildren().add(labelnummer);

            // Set luchthaven
            luchthaven = new Label();
            luchthaven.setText("Luchthaven: " + lijstVanLuchthavens.get(kofferIndex));
            luchthaven.setLayoutX(LAYOUT_X_VAN_LUCHTHAVEN);
            luchthaven.setLayoutY(LAYOUT_Y_VAN_LUCHTHAVEN);
            sjablonen[kofferIndex].getChildren().add(luchthaven);

            // Set bestemming
            bestemming = new Label();
            bestemming.setText("Bestemming: " + lijstVanBestemmingen.get(kofferIndex));
            bestemming.setLayoutX(LAYOUT_X_VAN_BESTEMMING);
            bestemming.setLayoutY(LAYOUT_Y_VAN_BESTEMMING);
            sjablonen[kofferIndex].getChildren().add(bestemming);

            // Set vluchtnummer
            vluchtnummer = new Label();
            vluchtnummer.setText("Vluchtnummer: " + lijstVanVluchtnummers.get(kofferIndex));
            vluchtnummer.setLayoutX(LAYOUT_X_VAN_VLUCHTNUMMER);
            vluchtnummer.setLayoutY(LAYOUT_Y_VAN_VLUCHTNUMMER);
            sjablonen[kofferIndex].getChildren().add(vluchtnummer);

            // Set merk
            merk = new Label();
            merk.setText("Merk: " + lijstVanMerken.get(kofferIndex));
            merk.setLayoutX(LAYOUT_X_VAN_MERK);
            merk.setLayoutY(LAYOUT_Y_VAN_MERK);
            sjablonen[kofferIndex].getChildren().add(merk);

            // Set kleur
            kleur = new Label();
            kleur.setText("Kleur: " + lijstVanKleuren.get(kofferIndex));
            kleur.setLayoutX(LAYOUT_X_VAN_KLEUR);
            kleur.setLayoutY(LAYOUT_Y_VAN_KLEUR);
            sjablonen[kofferIndex].getChildren().add(kleur);

            // Set kenmerk label
            kenmerkLabel = new Label();
            kenmerkLabel.setText("Bijzondere kenmerken:");
            kenmerkLabel.setLayoutX(LAYOUT_X_VAN_KENMERK_LABEL);
            kenmerkLabel.setLayoutY(LAYOUT_Y_VAN_KENMERK_LABEL);
            sjablonen[kofferIndex].getChildren().add(kenmerkLabel);

            // Set kenmerk text
            kenmerkText = new TextArea();
            kenmerkText.setEditable(false);
            kenmerkText.setText(lijstVanKenmerken.get(kofferIndex));
            kenmerkText.setWrapText(true);
            kenmerkText.setPrefSize(PREF_WIDTH_KENMERK_TEXT, PREF_HEIGHT_KENMERK_TEXT);
            kenmerkText.setLayoutX(LAYOUT_X_VAN_KENMERK_TEXT);
            kenmerkText.setLayoutY(LAYOUT_Y_VAN_KENMERK_TEXT);
            sjablonen[kofferIndex].getChildren().add(kenmerkText);

            // Set button
            claimKoffer = new Button();
            claimKoffer.setText("Claim");
            claimKoffer.setTextFill(Color.WHITE);
            claimKoffer.setLayoutX(LAYOUT_X_VAN_CLAIM_BUTTON);
            claimKoffer.setLayoutY(LAYOUT_Y_VAN_CLAIM_BUTTON);
            claimKoffer.setPrefSize(PREF_WIDTH_CLAIM_BUTTON, PREF_HEIGHT_CLAIM_BUTTON);

            // Een lambda expressie is niet compitabel met een lokale variable 
            final int buttonIndex = kofferIndex;

            // Maak gebruik van een lambda expressie om ervoor te zorgen dat de gebruiker de juiste vermiste bagage kan claimen
            claimKoffer.setOnAction((ActionEvent e) -> {
                try {
                    claimVermisteBagage(vermisteBagageFormulier, buttonIndex);
                } catch (SQLException | InterruptedException | IOException ex) {
                    Logger.getLogger(RegistratieController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            sjablonen[kofferIndex].getChildren().add(claimKoffer);

            // Set scheidingslijn
            scheidingslijn = new Line();
            scheidingslijn.setLayoutX(LAYOUT_X_VAN_SCHEIDINGSLIJN);
            scheidingslijn.setLayoutY(LAYOUT_Y_VAN_SCHEIDINGSLIJN);
            scheidingslijn.setStartX(START_X_VAN_SCHEIDINGSLIJN);
            scheidingslijn.setEndX(END_Y_VAN_SCHEIDINGSLIJN);
            sjablonen[kofferIndex].getChildren().add(scheidingslijn);

            // Verander de positie van de sjabloon voor een consistente opmaak
            LAYOUT_Y_VAN_SJABLOON += LAYOUT_Y_SJABLOON_INCREMENTER;
        }
        return sjablonen;
    }

    @FXML
    private void genereerZoekResultaten(Pane[] sjablonen) throws IOException {
        // Voeg de ingevulde sjablonen toe aan een pane, deze groeit aan de hand van het aantal componenten
        Utilities.rootPane = new Pane();
        Utilities.rootPane.getChildren().addAll(Arrays.asList(sjablonen));

        // Voeg de pane toe aan de rootscrollpane zodat de gebruiker door de zoekresultaten kan scrollen
        Utilities.rootScrollPane = new ScrollPane();
        Utilities.rootScrollPane.setContent(Utilities.rootPane);

        // Zorgt ervoor dat alleen de grootte van de scrollpane zelf wordt gelimiteerd
        Utilities.rootScrollPane.setPrefSize(PREF_WIDTH_ROOT_SCROLLPANE, PREF_HEIGHT_ROOT_SCROLLPANE);

        // Schakel horizontale scroll bar uit
        Utilities.rootScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Schakel verticale scroll bar in
        Utilities.rootScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // Toon lijst met alle zoekresultaten
        Utilities.switchSchermNaarPane(Utilities.rootScrollPane, vermisteBagageFormulierPane);
    }

    @FXML
    private void claimVermisteBagage(Bagageregistratie vermisteBagageFormulier, int buttonIndex) throws SQLException, InterruptedException, IOException {
        String query = "UPDATE registratie SET naam = ?, adres = ?, woonplaats = ?, postcode = ?, land = ?, telefoonnummer = ? WHERE formuliernummer = ?";
        try {
            Utilities.preparedStatement = Database.connectdb().prepareStatement(query);
            Utilities.preparedStatement.setString(1, vermisteBagageFormulier.getNaam());
            Utilities.preparedStatement.setString(2, vermisteBagageFormulier.getAdres());
            Utilities.preparedStatement.setString(3, vermisteBagageFormulier.getWoonplaats());
            Utilities.preparedStatement.setString(4, vermisteBagageFormulier.getPostcode());
            Utilities.preparedStatement.setString(5, vermisteBagageFormulier.getLand());
            Utilities.preparedStatement.setString(6, vermisteBagageFormulier.getTelefoonnummer());
            Utilities.preparedStatement.setInt(7, getFormuliernummerVanBagageDieWordtGeclaimed(buttonIndex));

            Utilities.preparedStatement.executeUpdate();

            // Show resultaat
            Utilities.infoBox("Bagage Zoeken", "ALERT", "Bagage is succesvol geclaimed!");

            // Breng de gebruiker weer terug naar het eerste scherm (bagage zoeken)
            Utilities.switchSchermNaarFXML("VermisteBagageRegistratie.fxml", vermisteBagageFormulierPane);
        } catch (SQLException mySQLException) {
            throw mySQLException;
        }
    }

    @FXML
    private int getFormuliernummerVanBagageDieWordtGeclaimed(int buttonIndex) {
        return (lijstVanFormuliernummers.get(buttonIndex));
    }

    @FXML
    private void insertGevondenBagageFormulier(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
        if (event.getTarget() == btn_RegistreerGevondenBagage) {
            // Controleer of de gevonden bagage formulier op de juiste manier is ingevuld
            if (isGevondenBagageFormulierGeldig()) {
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
                    Utilities.preparedStatement.setString(12, gevondenBagageFormulier.getVluchtnummer());
                    Utilities.preparedStatement.setString(13, gevondenBagageFormulier.getBestemming());
                    Utilities.preparedStatement.setTime(14, gevondenBagageFormulier.getTijd());
                    Utilities.preparedStatement.setDate(15, gevondenBagageFormulier.getDatum());
                    Utilities.preparedStatement.setString(16, gevondenBagageFormulier.getLuchthaven());
                    Utilities.preparedStatement.setInt(17, gevondenBagageFormulier.getLostAndFoundID());

                    Utilities.preparedStatement.executeUpdate();

                    Utilities.infoBox("Bagageregistratie", "ALERT", "Bagage is succesvol geregistreerd!");
                } catch (SQLException mySQLException) {
                    throw mySQLException;
                }
            }
        }
    }

    @FXML
    private Bagageregistratie vulGevondenBagageFormulierIn() throws ParseException {
        Bagageregistratie gevondenBagageFormulier = new Bagageregistratie();

        gevondenBagageFormulier.setDatum(txtDatum.getValue());
        gevondenBagageFormulier.setTijd(); // Set systeem tijd
        gevondenBagageFormulier.setLuchthaven((String) luchthavenComboBox.getValue());
        gevondenBagageFormulier.setLabelnummer(txtLabelnummer.getText());
        gevondenBagageFormulier.setVluchtnummer(txtVluchtnummer.getText());
        gevondenBagageFormulier.setBestemming((String) bestemmingComboBox.getValue());
        gevondenBagageFormulier.setLostAndFoundID(txtLostAndFoundID.getText());
        gevondenBagageFormulier.setType((String) typeComboBox.getValue());
        gevondenBagageFormulier.setMerk((String) merkComboBox.getValue());
        gevondenBagageFormulier.setKleur((String) kleurComboBox.getValue());
        gevondenBagageFormulier.setKenmerken(txtKenmerken.getText());

        return gevondenBagageFormulier;
    }

    @FXML
    private void laadAlleComboboxen() {
        try {
            setLuchthavensLijst();
            setBestemmingenLijst();
            setTypesLijst();
            setMerkenLijst();
            setKleurenLijst();
            setLandenLijst();
        } catch (Exception e) {
        }
    }

    @FXML
    private void vulCombobox(ArrayList<String> lijst, JFXComboBox comboBox) {
        for (String item : lijst) {
            comboBox.getItems().add(item);
        }
    }

    @FXML
    private void setLuchthavensLijst() {
        ArrayList<String> LuchthavensLijst = new ArrayList<>();
        LuchthavensLijst.add("Amsterdam Schiphol");
        LuchthavensLijst.add("Hartsfield–Jackson Atlanta International");
        LuchthavensLijst.add("Beijing Capital International");
        LuchthavensLijst.add("London Heathrow");
        LuchthavensLijst.add("Tokyo International Airport");
        LuchthavensLijst.add("O’Hare International Airport");
        LuchthavensLijst.add("Los Angeles International Airport");
        LuchthavensLijst.add("Dubai International Airport");
        LuchthavensLijst.add("Paris Charles de Gaulle");
        LuchthavensLijst.add("Dallas-Fort Worth International Airport");
        LuchthavensLijst.add("Soekarno-Hatta International Airport");
        LuchthavensLijst.add("Hong Kong International Airport");
        LuchthavensLijst.add("Frankfurt Airport");
        LuchthavensLijst.add("Singapore Changi");
        LuchthavensLijst.add("Denver International Airport");

        Collections.sort(LuchthavensLijst);

        vulCombobox(LuchthavensLijst, luchthavenComboBox);
    }

    @FXML
    private void setBestemmingenLijst() {
        ArrayList<String> bestemmingenLijst = new ArrayList<>();
        bestemmingenLijst.add("Amsterdam");
        bestemmingenLijst.add("Atlanta");
        bestemmingenLijst.add("Peking");
        bestemmingenLijst.add("London");
        bestemmingenLijst.add("Tokyo");
        bestemmingenLijst.add("Chicago");
        bestemmingenLijst.add("Los Angeles");
        bestemmingenLijst.add("Dubai");
        bestemmingenLijst.add("Parijs");
        bestemmingenLijst.add("Dallas");
        bestemmingenLijst.add("Jakarta");
        bestemmingenLijst.add("Hong Kong");
        bestemmingenLijst.add("Frankfurt");
        bestemmingenLijst.add("Singapore");
        bestemmingenLijst.add("Denver");

        Collections.sort(bestemmingenLijst);

        vulCombobox(bestemmingenLijst, bestemmingComboBox);
    }

    @FXML
    private void setTypesLijst() {
        ArrayList<String> typesLijst = new ArrayList<>();
        typesLijst.add("Handbagage koffer");
        typesLijst.add("Harde koffer");
        typesLijst.add("Kinderkoffer");
        typesLijst.add("Lichtgewicht koffer");
        typesLijst.add("Pilotenkoffer");
        typesLijst.add("Trolley koffer");
        typesLijst.add("Zachte koffer");

        Collections.sort(typesLijst);

        vulCombobox(typesLijst, typeComboBox);
    }

    @FXML
    private void setMerkenLijst() {
        ArrayList<String> merkenLijst = new ArrayList<>();
        merkenLijst.add("American Tourister");
        merkenLijst.add("Adventure Bags");
        merkenLijst.add("BHPPY");
        merkenLijst.add("Burton");
        merkenLijst.add("Carlton");
        merkenLijst.add("Dakine");
        merkenLijst.add("Decent");
        merkenLijst.add("Delsey");
        merkenLijst.add("Eagle Creek");
        merkenLijst.add("Eastpak");
        merkenLijst.add("Enrico Benetti");
        merkenLijst.add("Fjallraven");
        merkenLijst.add("Herschel");
        merkenLijst.add("Jack Wolfskin");
        merkenLijst.add("Lojel");
        merkenLijst.add("Oakley");
        merkenLijst.add("Princess Traveller");
        merkenLijst.add("Samsonite");
        merkenLijst.add("Spiral");
        merkenLijst.add("SUITSUIT");
        merkenLijst.add("The Cuties and Pals");
        merkenLijst.add("The North Face");
        merkenLijst.add("Vaude");

        Collections.sort(merkenLijst);

        vulCombobox(merkenLijst, merkComboBox);
    }

    @FXML
    private void setKleurenLijst() {
        ArrayList<String> kleurenLijst = new ArrayList<>();
        kleurenLijst.add("Blauw");
        kleurenLijst.add("Grijs");
        kleurenLijst.add("Rood");
        kleurenLijst.add("Roze");
        kleurenLijst.add("Zwart");
        kleurenLijst.add("Bruin");
        kleurenLijst.add("Crème");
        kleurenLijst.add("Geel");
        kleurenLijst.add("Goud");
        kleurenLijst.add("Groen");
        kleurenLijst.add("Koper");
        kleurenLijst.add("Oranje");
        kleurenLijst.add("Paars");
        kleurenLijst.add("Wit");
        kleurenLijst.add("Zilver");
        kleurenLijst.add("Meerkleurig");

        Collections.sort(kleurenLijst);

        vulCombobox(kleurenLijst, kleurComboBox);
    }

    @FXML
    private void setLandenLijst() {
        ArrayList<String> landenLijst = new ArrayList<>();

        // Get ISO landen, en sla de namen op in de landen lijst
        String[] isoLanden = Locale.getISOCountries();
        for (String land : isoLanden) {
            Locale locale = new Locale("nl", land);
            landenLijst.add(locale.getDisplayCountry());
        }

        Collections.sort(landenLijst);

        vulCombobox(landenLijst, landComboBox);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.getScene().getStylesheets().add("/styles/StylesMitchell.css");
        lijstVanFormuliernummers = new ArrayList<>();
        lijstVanTypes = new ArrayList<>();
        lijstVanTijdstippen = new ArrayList<>();
        lijstVanDatums = new ArrayList<>();
        lijstVanLabelnummers = new ArrayList<>();
        lijstVanLuchthavens = new ArrayList<>();
        lijstVanBestemmingen = new ArrayList<>();
        lijstVanVluchtnummers = new ArrayList<>();
        lijstVanMerken = new ArrayList<>();
        lijstVanKleuren = new ArrayList<>();
        lijstVanKenmerken = new ArrayList<>();
        laadAlleComboboxen();
    }
}
