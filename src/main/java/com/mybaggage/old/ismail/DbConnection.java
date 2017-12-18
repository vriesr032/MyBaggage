package com.mybaggage.old.ismail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ismail
 */
public class DbConnection {

    public Connection Connect() {
        try {
            //De database connectie
            String url = "jdbc:mysql://localhost:3306/sys?useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "admin";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public Connection getConnection() throws SQLException, ClassNotFoundException{	
		
	          
	      Class.forName("com.mysql.jdbc.Driver");
	      String url = "jdbc:mysql://localhost:3306/sys?useLegacyDatetimeCode=false&serverTimezone=UTC"; 
	      Connection conn = DriverManager.getConnection(url,"root","admin");   
	      return conn;

}
}