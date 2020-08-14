package com.postgre.read;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.pojo.EntityClass;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobOutputStream;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PostgreReader {

	public static void main(String[] args) throws Exception {
		Connection c = null;
		Statement stmt = null;
		String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=dfldevhbase01;AccountKey=P0O65zvUpokgTFzADOViConG5Nf02H01zN7fgH0LgoB2HppTdJNuKIHtPfc2TF/KZBKK5FPpW6qf3/df48Easg==;EndpointSuffix=core.windows.net";

		EntityClass assemblyVehiclePojo = new EntityClass();

		List<String> dirname = new ArrayList<String>();
		List<String> filename = new ArrayList<String>();
		LocalDateTime now = LocalDateTime.now();
		NumberFormat f = new DecimalFormat("00");

		dirname.add(String.valueOf(now.getYear()));
		dirname.add(String.valueOf(f.format(now.getMonthValue())));
		dirname.add(String.valueOf(f.format(now.getDayOfMonth())));

		filename.add(String.valueOf(f.format(now.getHour())));
		filename.add(String.valueOf(f.format(now.getMinute())));
		filename.add(String.valueOf(f.format(now.getSecond())));

		String fullpath = String.join("-", dirname) + "/" + String.join("-", filename);
		System.out.println(fullpath);

		try {
			Class.forName("org.postgresql.Driver");
			Properties props = new Properties();
			props.setProperty("", "");
			props.setProperty("password", "");
			props.setProperty("ssl", "true");
			c = DriverManager.getConnection(
					"", props);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		storageAccount = CloudStorageAccount.parse(storageConnectionString);
		blobClient = storageAccount.createCloudBlobClient();
		container = blobClient.getContainerReference("pgsql");
		CloudBlockBlob blockBlob = container.getBlockBlobReference(fullpath + ".txt");

		BlobOutputStream blobOutputStream = blockBlob.openOutputStream();

		stmt = c.createStatement();

		String sql = "";
		ResultSet rs = stmt.executeQuery(sql);

		String buffer;
		ByteArrayInputStream inputStream;
		boolean firstline = true;
		while (rs.next()) {

			// Call pojo class
			buffer = assemblyVehiclePojo.formatRow(rs,firstline);
			inputStream = new ByteArrayInputStream(buffer.getBytes());
			blobOutputStream.write(buffer.getBytes());
		}

		System.out.println("End of Fetch");

		blobOutputStream.close();
		stmt.close();
		c.close();

		System.out.println("End of Execution");
	}
}