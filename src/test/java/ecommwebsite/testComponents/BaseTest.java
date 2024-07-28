package ecommwebsite.testComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommwebsite.pageobjects.LandingPage;

public class BaseTest

{
	public WebDriver driver;
	public LandingPage landingPage;
	

	public WebDriver initializeDriver() throws IOException {

		

		Properties properties = new Properties();

		FileInputStream fis = new FileInputStream(

				System.getProperty("user.dir") + "/src/main/java/ecommwebsite/resources/initialConfig.properties");

		properties.load(fis);

		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : properties.getProperty("browser");

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
	
	
	// this piece of code helps to read Json data into string to Java object
	
	public List<HashMap<String, String>> jsonToHashMap(String filepath) throws IOException
	{
		
		// helps to read Json data into String 
		
		File jsonFile = new File(filepath);
	    
		String jsonContent = FileUtils.readFileToString(jsonFile, StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List <HashMap<String,String>> data = mapper.readValue(jsonContent,
				
				new TypeReference<List<HashMap<String, String>>>() {});
		
		return data;
		
		}
	
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
		
		File screenshotInLocal = new File(System.getProperty("user.dir")+"/resources/"+  testCaseName +".png");
		FileUtils.copyFile(screenshotFile, screenshotInLocal);
		
		return System.getProperty("user.dir")+"/resources/"+  testCaseName +".png";
		
	}

	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException

	{
		 driver = initializeDriver();

		 landingPage = new LandingPage(driver);

		 landingPage.openApp();

		return landingPage;

	}

	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws IOException
	
	{
		
		driver.close();
		
	}
}
