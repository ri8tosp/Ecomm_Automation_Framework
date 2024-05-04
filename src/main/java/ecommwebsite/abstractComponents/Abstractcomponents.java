package ecommwebsite.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommwebsite.pageobjects.MyCartPage;
import ecommwebsite.pageobjects.OrderPage;

public class Abstractcomponents

{

	WebDriver driver;

	

	public Abstractcomponents(WebDriver driver) {

		this.driver = driver;

	}
	
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement cartButton;
	
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	
	
	

	public void waitForElementToAppear(By findBy) 
	
	{
		
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	
	public void waitForWebElementToAppear(WebElement findBy) 
	
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	public void waitForElementToDisappear(WebElement findEle)
	{
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  
		  wait.until(ExpectedConditions.invisibilityOf(findEle));
	}
	
	
	public MyCartPage goToCart()
	
	{
		cartButton.click();
		
		return new MyCartPage(driver);
	}
	
	
	public  OrderPage goToOrderPage()
	
	{
		orderHeader.click();
	    OrderPage orderPage = new OrderPage(driver);
	    
	    return orderPage;
		
	}
	
		

}
