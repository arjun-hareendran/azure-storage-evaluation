package com.postgre.read;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.sql.Statement;

public class DumpData {

	public static void main(String args[]) throws SQLException, IOException {

		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			Properties props = new Properties();
			props.setProperty("", "username");
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
		

		CopyManager copyManager = new CopyManager((BaseConnection) c);
		File file = new File("C:\\Users\\AHAREEN\\Desktop\\timedimension-files\\dump-file");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		String query = "select * FROM eol_inspection.assembly_vehicle limit 2" ;
		
		copyManager.copyOut("COPY (" + query + ") TO STDOUT WITH (FORMAT CSV)", fileOutputStream);

	}

}
