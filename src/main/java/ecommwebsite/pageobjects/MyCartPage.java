package ecommwebsite.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommwebsite.abstractComponents.Abstractcomponents;

public class MyCartPage extends Abstractcomponents

{

	WebDriver driver;

	public MyCartPage(WebDriver driver)

	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutButton;
	
	
	public Boolean isProductDisplayed(String productName)
	{
		Boolean isProductPresent = cartProducts.stream().anyMatch(cartProduct ->

		cartProduct.getText().equalsIgnoreCase(productName));
		
		return isProductPresent;
	}
	
	public CheckOutPage clickingOnCheckOut()
	{
		checkOutButton.click();
		
		return new CheckOutPage(driver);
	}
	
	
}
