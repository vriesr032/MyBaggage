package com.mybaggage.old.rik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rick de Vries (500758516)
 */
public class Database {

    public static Connection Connect() {
        try {
            //De database connectie
            String url = "jdbc:mysql://localhost:3306/bagage";
            String user = "root";
            String password = "root123";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}