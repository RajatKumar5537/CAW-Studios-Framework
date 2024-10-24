package Pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver; // WebDriver instance to interact with the browser

	@FindBy(xpath = "//summary[normalize-space()='Table Data']")
	WebElement tableDataButton; // Button to open the table data section

	@FindBy(xpath = "//textarea[@id='jsondata']")
	WebElement inputTextBox; // Text area for inputting JSON data

	@FindBy(xpath = "//button[@id='refreshtable']")
	WebElement refreshButton; // Button to refresh the table with new data

	@FindBy(xpath = "//table[@id='dynamictable']/tr")
	List<WebElement> tableRows; // List of rows in the dynamic table

	@FindBy(xpath = "//table[@id='dynamictable']/tr/td")
	List<WebElement> tableCells; // List of cells in the dynamic table

	// Constructor to initialize the WebDriver and PageFactory elements
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// Initialize web elements
	}

	// Method to insert data into the input text box and click the refresh button
	public void insertData(String jsonData) throws InterruptedException {
		tableDataButton.click();// Click on the button to open the table data section
		inputTextBox.clear();// Clear any existing text in the input text box
		inputTextBox.sendKeys(jsonData); // Send JSON data into the text box

		// Scroll to the refresh button and click it to refresh the table
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", refreshButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", refreshButton);
	}

	// Method to retrieve data from the table in a 2D String array format
	public String[][] getTableData() {
		int numberOfRows = tableCells.size() / 3;
		String[][] tableData = new String[numberOfRows][3];
		for (int i = 0; i < numberOfRows; i++) {
			tableData[i][0] = tableCells.get(i * 3 + 1).getText(); // Name (2nd column)
			tableData[i][1] = tableCells.get(i * 3 + 2).getText(); // Age (3rd column)
			tableData[i][2] = tableCells.get(i * 3).getText(); // Gender (1st column)
		}
		return tableData;// Return the populated table data
	}
}
