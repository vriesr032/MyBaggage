package com.mybaggage;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Het doel van deze class is om bepaalde methodes en variables te generalizeren
 *
 * @author Mitchell Gordon (500775386)
 */
public class Utilities {

    public static final String TIMEZONE_FIX = "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static final String RELATIVE_PATH_OF_FXML = "/com/mybaggage/controllers/";

    public static final String EXPECTED_SQL_DATE_PATTERN = "dd-MM-yyyy";
    public static final String EXPECTED_SQL_TIME_PATTERN = "HH:mm";

    public static PreparedStatement preparedStatement;

    public static ResultSet resultSet;

    @FXML
    public static Parent root;

    @FXML
    public static Stage stage;

    @FXML
    public static Scene scene;

    @FXML
    public static Pane rootPane;

    @FXML
    public static ScrollPane rootScrollPane;

    // Zorgt ervoor dat de gebruiker geen object kan aanmaken van deze class
    private Utilities() {

    }

    /**
     * Convert String to the SQL Date to allow JDBC to identify this as an SQL DATE value
     *
     * @param date Representeerd de datum in de volgende formaat: dd-MM-yyyy
     * @return de datum in sql.Date formaat
     * @throws ParseException
     */
    public static Date convertStringToSQLDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(EXPECTED_SQL_DATE_PATTERN);
        java.sql.Date sqlDate = new java.sql.Date(format.parse(date).getTime());
        return sqlDate;
    }

    /**
     *
     * @param time Representeerd de tijd in de volgende formaat: mm:HH
     * @return de tijd in sql.Time formaat
     * @throws ParseException
     */
    public static Time convertStringToSQLTime(String time) throws ParseException {
        DateFormat format = new SimpleDateFormat(EXPECTED_SQL_TIME_PATTERN);
        java.sql.Time sqlTime = new java.sql.Time(format.parse(time).getTime());
        return sqlTime;
    }

    /**
     * Converteert een LocalDate naar een String
     *
     * @param date Representeerd de datum in LocalDate formaat
     * @return een String afkomstig van een geconverteerde LocalDate
     */
    public static String convertLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = date.format(formatter);
        return formattedString;
    }

    /**
     * Get de huidige systeem tijd in een String formaat
     *
     * @return een String met de huidige systeem tijd
     */
    public static String getCurrentTimeString() {
        return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }

    /**
     * Switch de huidige (holder)scherm naar een pane van keuze
     *
     * @param node In dit object kan je allerlei soorten Panes zetten (Pane, AnchorPane, Scrollpane, etc)
     * @param holderPane Refereert naar de root anchorPane (hierin zitten al je containers, buttons, etc) uit je fxml bestand.
     */
    public static void switchSchermNaarPane(Node node, AnchorPane holderPane) {
        holderPane.getChildren().setAll(node);
    }

    /**
     * Switch de huidige (holder)scherm naar een FXML van keuze
     *
     * @param fxml Refereert naar de locatie van de FXML bestand waar je naar wilt switchen in String vorm
     * @param holderPane Refereert naar de root anchorPane (hierin zitten al je containers, buttons, etc) uit je fxml bestand.
     */
    public static void switchSchermNaarFXML(String fxml, AnchorPane holderPane) throws IOException {
        Parent parent = FXMLLoader.load(Utilities.class.getResource(RELATIVE_PATH_OF_FXML + fxml));
        AnchorPane pane = (AnchorPane) parent;
        holderPane.getChildren().setAll(pane);
    }

    /**
     * Laat een custom infoBox op het scherm verschijnen
     *
     * @param titel De titel van de infoBox
     * @param header De header van de infoBox
     * @param omschrijving De omschrijving van de infobox
     */
    public static void infoBox(String titel, String header, String omschrijving) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(header);
        alert.setContentText(omschrijving);
        alert.showAndWait();
    }

    /**
     * Laat een custom errorBox op het scherm verschijnen
     *
     * @param foutmelding De specificatie van de foutmelding
     */
    public static void errorBox(String foutmelding) {
        Optional<ButtonType> alert = new Alert(Alert.AlertType.ERROR, foutmelding).showAndWait();
    }
}
