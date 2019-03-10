package com.crypto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.crypto.utility.StaticInfo;


public class DatabaseConnection {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = StaticInfo.url;
		String username = StaticInfo.dbUser;
		String password = StaticInfo.dbPass;
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, username, password);

	}
}
