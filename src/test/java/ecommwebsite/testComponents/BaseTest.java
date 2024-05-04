package ecommwebsite.testComponents;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ecommwebsite.pageobjects.LandingPage;

public class BaseTest

{
	WebDriver driver;
	public LandingPage landingPage;
	

	public WebDriver initializeDriver() throws IOException {

		

		Properties properties = new Properties();

		FileInputStream fis = new FileInputStream(

				System.getProperty("user.dir") + "/src/main/java/ecommwebsite/resources/GlobalData.properties");

		properties.load(fis);

		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");

			driver = new ChromeDriver(options);

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.manage().window().maximize();

		}

		else

		{

			driver = new EdgeDriver();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.manage().window().maximize();
		}

		return driver;

	}

	
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException

	{
		 driver = initializeDriver();

		 landingPage = new LandingPage(driver);

		 landingPage.openApp();

		return landingPage;

	}

	
	
	@AfterMethod
	public void tearDown() throws IOException
	
	{
		
		driver.close();
		
	}
}
