package Generic;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver; // Static WebDriver instance to be used across the test class
	LoginPage loginPage; // Instance of LoginPage to interact with the login page

	@BeforeTest
	public void setUp() {
		// Setup the ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver(); // Initialize the ChromeDriver
		driver.manage().window().maximize(); // Maximize the browser window for better visibility
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Set implicit wait for elements to be found
	}

	@BeforeClass
	public void beforeTestMethod() throws IOException   {
		// Navigate to the application URL retrieved from configuration properties
		driver.get(ReusableComponent.getCongigPropertyData("url"));

		loginPage = new LoginPage(driver);// Initialize the LoginPage instance with the WebDriver
	}

	@AfterClass
	public void afterTestMethod() {
	}

	@AfterTest
	public void tearDown() {
		// Close the browser and quit the WebDriver session
		driver.quit();
	}
}
