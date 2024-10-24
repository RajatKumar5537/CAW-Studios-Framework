package Generic;

import org.json.JSONArray;
import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

	@DataProvider(name = "jsonData") // Annotation to define a data provider method named "jsonData"
	public Object[][] getJsonData() throws IOException {
		StringBuilder jsonData = new StringBuilder(); // StringBuilder to accumulate JSON data
		
		// Create a BufferedReader to read the JSON file
		BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/TestData.json"));
		String line;// Variable to store each line read from the file

		// Read the file line by line until there are no more lines
		while ((line = reader.readLine()) != null) {
			jsonData.append(line);// Append each line to the StringBuilder
		}
		// Close the BufferedReader to free system resources
		reader.close();
		JSONArray jsonArray = new JSONArray(jsonData.toString());// Convert the accumulated JSON data to a JSONArray
		// Return the JSONArray wrapped in an Object array to be used by the TestNG DataProvider
		return new Object[][] { { jsonArray } };
	}
}
