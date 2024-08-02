package ecommwebsite.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommwebsite.abstractComponents.Abstractcomponents;

public class LandingPage extends Abstractcomponents

{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


    //using PageFactory
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	// creating action methods 
	

	public void openApp()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public ProductCatalogue logIntoApp(String username, String password)
	{
		userName.sendKeys(username);
		userPassword.sendKeys(password);
		login.click();
		
		 return new ProductCatalogue(driver);
	}
	
	
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
