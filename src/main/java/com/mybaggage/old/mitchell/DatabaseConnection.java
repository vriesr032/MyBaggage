package com.mybaggage.old.mitchell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Het doel van deze class is om een verbinding te maken met de MySQL database 
 * die op een localhost wordt uitgevoerd.
 *
 * @author Mitchell Gordon (500775386)
 */
public class DatabaseConnection {

    // URL, gebruikersnaam en wachtwoord van de MySQL server
    public static String url = "";
    public static String user = "";
    public static String password = "";

    // Regelt het openen en beheren van de database verbinding
    public Connection connection = null;

    // Maak connectie met de database
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            // Laad de MySQL driver, elke database heeft zijn eigen driver
            Class.forName("com.mysql.jdbc.Driver");

            // Bereid de verbinding met de database voor
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException mysqlException) {
            throw mysqlException;
        }

        return connection;
    }
    
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
