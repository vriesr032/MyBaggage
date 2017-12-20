package com.mybaggage;

import com.mybaggage.old.mitchell.DatabaseConnection;
import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Het doel van deze class is om bepaalde methodes en variables te generalizeren
 *
 * @author Mitchell Gordon (500775386)
 */
public class Utilities {

    static public PreparedStatement preparedStatement;

    @FXML
    static public Parent root;

    @FXML
    static public Stage stage;

    @FXML
    static public Scene scene;

    @FXML
    static public Pane rootPane;

    @FXML
    static public ScrollPane rootScrollPane;

    static String timezoneFix = "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    // Voorkomt dat gebruikers een object van deze class kunnen aanmaken
    private Utilities(){
        
    }

    /*
    static public void setMySQLConnectionParameters(String url, String user, String password) {
        Database.url = "jdbc:mysql://localhost:3306/"
                + url
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Database.user = user;
        Database.password = password;
    }
     */

 /*
    WIP: Convert String to the wrapper Date to allow JDBC to identify this as an SQL DATE value 
     */
    static public Date convertStringToWrapperDate(String expectedPattern, String date) throws ParseException {
        // Use a String pattern to define the expected date format.
        SimpleDateFormat format = new SimpleDateFormat(date);

        java.util.Date utilDate = format.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    /*
    WIP: Convert String to the wrapper Time to allow JDBC to identify this as an SQL TIME value
     */
    static public Time convertStringToWrapperTime(String expectedPattern, String time) throws ParseException {
        Time convertedTime;
        DateFormat format = new SimpleDateFormat(expectedPattern);

        try {
            convertedTime = (Time) format.parse(time);

        } catch (ParseException parseException) {
            throw parseException;
        }
        return convertedTime;
    }

    static public String convertLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = date.format(formatter);
        return formattedString;
    }

    static public String getCurrentTimeString() {
        return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }

}
