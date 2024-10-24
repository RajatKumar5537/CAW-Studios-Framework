package Tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import Generic.BaseClass;
import Generic.JSONReader;
import Pages.LoginPage;

public class LoginTest extends BaseClass {
	LoginPage loginPage; // Instance of the LoginPage class to interact with the login page
	JSONObject testData; // Variable to hold test data as a JSON object

	// Test method using data provider for JSON data
	@Test(dataProvider = "jsonData", dataProviderClass = JSONReader.class)
	public void testInsertAndVerifyData(JSONArray jsonDataList) throws InterruptedException {
		loginPage = new LoginPage(driver);// Initialize the LoginPage instance with the WebDriver
		JSONArray jsonArray = new JSONArray();// Create a new JSONArray to hold processed JSON objects

		// Loop through the provided JSON data list to extract and store data in a new
		// JSON array
		for (int i = 0; i < jsonDataList.length(); i++) {
			JSONObject data = jsonDataList.getJSONObject(i);// Get the current JSON object from the input list
			JSONObject jsonObject = new JSONObject();// Create a new JSON object for each entry
			
			jsonObject.put("name", data.get("name"));// Add name to the new JSON object
			jsonObject.put("age", data.get("age"));// Add age to the new JSON object
			jsonObject.put("gender", data.get("gender"));// Add gender to the new JSON object
			jsonArray.put(jsonObject);// Add the new JSON object to the JSONArray
		}

		// Call the insertData method to input the JSON data into the application
		loginPage.insertData(jsonArray.toString());

		// Retrieve the data from the table after refreshing
		String[][] tableData = loginPage.getTableData();

		// Loop through the original JSON data list to verify that the table data matches the input data
		for (int i = 0; i < jsonDataList.length(); i++) {
			JSONObject jsonData = jsonDataList.getJSONObject(i);// Get the current JSON object for comparison

			// Assert that the name in the table matches the expected name from the JSON data
			Assert.assertEquals(tableData[i][0], jsonData.get("name").toString(), "Name mismatch at row " + i);
			
			// Assert that the age in the table matches the expected age from the JSON data
			Assert.assertEquals(tableData[i][1], jsonData.get("age").toString(), "Age mismatch at row " + i);
			
			// Assert that the gender in the table matches the expected gender from the JSON data
			Assert.assertEquals(tableData[i][2], jsonData.get("gender").toString(), "Gender mismatch at row " + i);
		}
	}

}
