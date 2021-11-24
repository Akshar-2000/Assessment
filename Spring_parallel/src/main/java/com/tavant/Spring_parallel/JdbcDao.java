package com.tavant.Spring_parallel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

//@Repository("dao")
public class JdbcDao {
	public final static String URL = "jdbc:mysql://localhost:3306/Accounts";
	public final static String USERNAME = "root";
	public static final String USERPASS = "password26";
	
	Connection dbCon;
	PreparedStatement thePreparedStatement;
	
	
	void connectToDb() {
		try {
//			Load the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbCon = DriverManager.getConnection(URL, USERNAME, USERPASS);
			System.out.println("Connected.....");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Problems loading the driver or establishing the connection : " + e.getMessage());
		}	
	}
	
	
	//All Operations code...
	//All println Statements near the resultant set must be stored and removed...
	//If not put here as well
	
	
	//This Class is old....New One is DatabaseOperations....
}
