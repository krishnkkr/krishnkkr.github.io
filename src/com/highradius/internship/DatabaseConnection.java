package com.highradius.internship;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	protected static Connection initializeDatabase() 
	        throws SQLException, ClassNotFoundException 
	    { 
	        // Initialize all the information regarding 
	        // Database Connection  com.mysql.cj.jdbc.Driver com.mysql.jdbc.Driver
	        String dbDriver = "com.mysql.cj.jdbc.Driver"; 
	        String dbURL = "jdbc:mysql://localhost:3306/"; 
	        String dbName = "winter_internship"; 
	        String dbUsername = "root"; 
	        String dbPassword = "loginkkr12"; 
	        
	        Class.forName(dbDriver); 
	     
	        Connection con = DriverManager.getConnection(dbURL + dbName, 
                    dbUsername,  
                    dbPassword);
	     	        return con; 
	    } 
}
