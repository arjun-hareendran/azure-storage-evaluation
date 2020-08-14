package com.azure.blob;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class BlobStorageWriter {


	public static final String storageConnectionString = "connection string";

	public static void main(String[] args) throws Exception {

		File sourceFile = null;
		System.out.println("Azure Blob storage quick start sample");

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		storageAccount = CloudStorageAccount.parse(storageConnectionString);

		blobClient = storageAccount.createCloudBlobClient();
		container = blobClient.getContainerReference("pgsql");

		CloudBlockBlob blockBlob = container.getBlockBlobReference("Sampe-file.txt");

		BlobOutputStream blobOutputStream = blockBlob.openOutputStream();
		byte[] buffer = "Hello Azure ! Ping from Arjun".getBytes();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);

		int next = inputStream.read();
		while (next != -1) {
			blobOutputStream.write(next);
			next = inputStream.read();
		}

		blobOutputStream.close();

	}

}
