/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.old.osman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    
	public Connection getConnection() throws SQLException, ClassNotFoundException{	
		
	          
	      Class.forName("com.mysql.jdbc.Driver");
	      String url = "jdbc:mysql://localhost:3306/bagage_registratie"; 
	      Connection conn = DriverManager.getConnection(url,"root","");   
	      return conn;
		 
	
	}


    
    
}
