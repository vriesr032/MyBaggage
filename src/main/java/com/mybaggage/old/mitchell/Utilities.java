package com.mybaggage.old.mitchell;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Het doel van deze class is om bepaalde methodes en variables te generalizeren
 *
 * @author Mitchell Gordon (500775386)
 */
public class Utilities {

    static public DatabaseConnection databaseConnection;

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

    @FXML
    static public void setStage(Parent root, MouseEvent event) {
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    static public void setStage(ScrollPane rootScrollPane, MouseEvent event) {
        scene = new Scene(rootScrollPane, 1366, 768);
        scene.getStylesheets().add("/styles/Styles.css");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    static public void setMySQLConnectionParameters(String url, String user, String password) {
        DatabaseConnection.url = "jdbc:mysql://localhost:3306/"
                + url
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
        DatabaseConnection.user = user;
        DatabaseConnection.password = password;
    }

    /*
    Convert String to the wrapper Date to allow JDBC to identify this as an SQL DATE value
     */
    static public Date convertStringToWrapperDate(String expectedPattern, String date) throws ParseException {
        // Use a String pattern to define the expected date format.
        SimpleDateFormat format = new SimpleDateFormat(date);

        java.util.Date utilDate = format.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    /*
    Convert String to the wrapper Time to allow JDBC to identify this as an SQL TIME value
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
}
