package com.mybaggage.old.ilias.application;

import java.sql.*;

//Used to connect to MySQL database.
public class DBConnection {
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{	
		
	          
	      Class.forName("com.mysql.jdbc.Driver");
	      String url = "jdbc:mysql://localhost:3306/personen"; 
	      Connection conn = DriverManager.getConnection(url,"root","root123");   
	      return conn;
		 
	
	}

}	
	
	
