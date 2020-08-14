package com.azure.blob.table;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableOperation;

public class AzureStorageTableManagerReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String storageConnectionString = "Connection String";

		try {

			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

			// Create the table client.
			CloudTableClient tableClient = storageAccount.createCloudTableClient();

			// Create a cloud table object for the table.
			CloudTable cloudTable = tableClient.getTableReference("devarjunpeople");

			// Retrieve the entity with partition key of "Smith" and row key of
			// "Jeff"
			TableOperation retrieveSmithJeff = TableOperation.retrieve("Harp", "Walter", CustomerEntity.class);

			// Submit the operation to the table service and get the specific
			// entity.
			CustomerEntity specificEntity = cloudTable.execute(retrieveSmithJeff).getResultAsType();

			// Output the entity.
			if (specificEntity != null) {
				System.out.println(specificEntity.getPartitionKey() + " " + specificEntity.getRowKey() + "\t"
						+ specificEntity.getEmail() + "\t" + specificEntity.getPhoneNumber());
			}
		} catch (Exception e) {
			// Output the stack trace.
			e.printStackTrace();
		}

	}

}
