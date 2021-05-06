package com.ers.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class JDBCConnector {
	private static Logger log = Logger.getLogger(JDBCConnector.class);
	private static Connection conn;
	String username,passwd,url;
		
	public JDBCConnector() {
		super();
	
	}
	
	public static Connection getConn() {
		if (conn == null) {
			setConn();
		}
		return conn;
	}
	
	private static void setConn() {
	 
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url,username,password);
			log.trace("New connection to database established.");
		} catch (Exception e) {
			log.fatal("Error accessing database\n", e);
		}
	}
	
	public static void testDatabase() {
		try {
		
			Connection testConn = getConn();
			PreparedStatement st = testConn.prepareStatement("SELECT * FROM test_tabledb");
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				log.trace("Database connection established and tested");
			}
			rs.close();
		} catch (SQLException e) {
			log.warn("Trial connection to database failed.", e);
		} 
	}
	

}
