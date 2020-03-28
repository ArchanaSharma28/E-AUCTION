package com.cts.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	public static Connection con;
public static Connection getConnection() throws SQLException,ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/auction","root","adm@123");
	return con;
}
}
