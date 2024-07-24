package ecommwebsite.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ecommwebsite.abstractComponents.Abstractcomponents;

public class CheckOutPage extends Abstractcomponents

{

	WebDriver driver;

	public CheckOutPage(WebDriver driver)

	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='form-group']/input")
	WebElement countryTextBox;

	@FindBy(xpath = "//div[@class='form-group']//section[contains(@class,'list-group')]//button")
	List<WebElement> individualCountry;

	@FindBy(xpath = "//div[@class='actions']/a[contains(text(), 'Place Order ')]")
	WebElement placeOrder;

	By allCountryDropdowns = By.xpath("//div[@class='form-group']//section[contains(@class,'list-group')]");

	// entering the country for the order

	public void selectingCountry(String countryName) 
	
	{
		
		Actions a = new Actions(driver);
		
		a.sendKeys(countryTextBox, countryName).build().perform();

		waitForElementToAppear(allCountryDropdowns);

		individualCountry.stream().filter(country -> country.getText().equalsIgnoreCase("india")).findFirst().orElse(null)
				.click();

	}

	public ConfirmationPage placeOrder() 
	
	{
		placeOrder.click();
		
		return new ConfirmationPage(driver);
	}
}
