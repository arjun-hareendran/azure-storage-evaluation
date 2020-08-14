package com.azure.blob.table;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

public class AzureStorageTableManagerRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String storageConnectionString = "Connection String";

		try {
			// Define constants for filters.
			final String PARTITION_KEY = "PartitionKey";
		    final String ROW_KEY = "RowKey";
		    final String TIMESTAMP = "Timestamp";
	
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

			// Create the table client.
			CloudTableClient tableClient = storageAccount.createCloudTableClient();

			// Create a cloud table object for the table.
			CloudTable cloudTable = tableClient.getTableReference("devarjunpeople");

			// Create a filter condition where the partition key is "Smith".
			String partitionFilter = TableQuery.generateFilterCondition(PARTITION_KEY, QueryComparisons.EQUAL, "Harp");

			// Specify a partition query, using "Smith" as the partition key
			// filter.
			TableQuery<CustomerEntity> partitionQuery = TableQuery.from(CustomerEntity.class).where(partitionFilter);

			// Loop through the results, displaying information about the
			// entity.
			for (CustomerEntity entity : cloudTable.execute(partitionQuery)) {
				System.out.println(entity.getPartitionKey() + " " + entity.getRowKey() + "\t" + entity.getEmail() + "\t"
						+ entity.getPhoneNumber());
			}
		} catch (Exception e) {
			// Output the stack trace.
			e.printStackTrace();
		}

	}

}
