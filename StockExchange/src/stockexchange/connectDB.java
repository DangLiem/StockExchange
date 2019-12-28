/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchange;

import java.sql.*;

/**
 *
 * @author dangliem
 */
public class connectDB {
    private static String username = "root";
    private static String password = "";
    private static String host = "localhost";
    private static String dbName = "db_stockexchange";
    private static String urlMySQL = "jdbc:mysql://" + host + "/" + dbName + "";
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException");
        }
        //ket noi
        try {
            conn = DriverManager.getConnection(urlMySQL, username, password);//ket noi
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        return conn;

    }
    
}
