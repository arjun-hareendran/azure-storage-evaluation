package com.postgre.read;

import java.sql.*;
import java.util.Properties;

public class DataReader {

	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			Properties props = new Properties();
			props.setProperty("", "name");
			props.setProperty("password", "password");
			props.setProperty("ssl", "true");
			c = DriverManager.getConnection(
					"", props);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		try {
			stmt = c.createStatement();
			String sql = " ";
			ResultSet rs = stmt.executeQuery(sql);
			
			String vehicle_id;
			String vehicle_model;
			while (rs.next()) {
				vehicle_id = rs.getString(1) ;
				vehicle_model = rs.getString(1) ;
				
				

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
	}
}