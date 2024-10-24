package Generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;

public class ReusableComponent {
	// Method to retrieve property data from a configuration file
	public static String getCongigPropertyData(String key) throws IOException {
		// Create a FileInputStream to read the properties file
		FileInputStream fis = new FileInputStream("src\\test\\resources\\credential.properties");
		
		Properties prop = new Properties();  // Create a Properties object to hold the properties from the file
		prop.load(fis); // Load the properties from the input stream
		String data = prop.getProperty(key); // Retrieve the value associated with the provided key
		return data; // Return the retrieved data
	}

	public static ExtentReports extent; // Static variable to hold the ExtentReports instance
	// Method to get the singleton instance of ExtentReports
	public static synchronized ExtentReports getInstance() {
		// Check if the instance is null
		if (extent == null) {
			// If it is, create a new instance of ExtentReports
			extent = new ExtentReports();
		}
		// Return the existing or newly created instance
		return extent;
	}
}
