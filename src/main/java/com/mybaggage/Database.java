/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage;

import java.sql.*;
import javax.swing.*;

public class Database {

    Connection conn = null;

    public static Connection connectdb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//LET OP SWINGAPP IS DE NAAM VAN DATABASE!! https://codingbybushan.wordpress.com/2017/04/09/login-form-in-javafx-and-mysql/
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bagage_registratie?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "admin");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
