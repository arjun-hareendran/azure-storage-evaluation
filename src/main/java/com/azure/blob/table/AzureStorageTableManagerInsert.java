package com.azure.blob.table;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableOperation;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class AzureStorageTableManagerInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String storageConnectionString = "Connection String";

		CloudStorageAccount storageAccount;
		try {
			storageAccount = CloudStorageAccount.parse(storageConnectionString);

			// Create the table client.
			CloudTableClient tableClient = storageAccount.createCloudTableClient();

			// Create the table if it doesn't exist.
			String tableName = "devarjunpeople";
			CloudTable cloudTable = tableClient.getTableReference(tableName);
			cloudTable.createIfNotExists();

			for (String table : tableClient.listTables()) {
				// Output each table name.
				System.out.println(table);
			}
			
			CustomerEntity customer1 = new CustomerEntity("Harp", "Walter");
			customer1.setEmail("Walter@contoso.com");
		    customer1.setPhoneNumber("425-555-0101");

		    TableOperation insertCustomer1 = TableOperation.insertOrReplace(customer1);
		    cloudTable.execute(insertCustomer1);

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
