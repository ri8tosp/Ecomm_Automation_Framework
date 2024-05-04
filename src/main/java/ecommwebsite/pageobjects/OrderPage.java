package ecommwebsite.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommwebsite.abstractComponents.Abstractcomponents;

public class OrderPage extends Abstractcomponents

{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) 
	
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	
	@FindBy(xpath="//tr/td[2]")
	List <WebElement> allOrders;

  public Boolean verifyOrderIsDisplayed (String productName)
  {
	  
	 Boolean orderElement = allOrders.stream().anyMatch(myOrder -> 
	  myOrder.getText().equalsIgnoreCase(productName));
	 
	 return orderElement;
  }
  
  public String displayAddedProductName(String productName)
  {
	 String  addedProduct = allOrders.stream().filter(myOrder -> 
	  myOrder.getText().equalsIgnoreCase(productName)).findFirst().orElse(null).getText();
	 
	 return addedProduct;
  }

}
