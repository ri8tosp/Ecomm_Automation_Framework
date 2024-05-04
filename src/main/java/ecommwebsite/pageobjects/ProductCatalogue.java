package ecommwebsite.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommwebsite.abstractComponents.Abstractcomponents;

public class ProductCatalogue extends Abstractcomponents

{

	WebDriver driver;

	public ProductCatalogue(WebDriver driver)

	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

	@FindBy(xpath = "//div[contains(@class,'mb-3')]")
	List<WebElement> products;
	
	@FindBy(className = "ng-animating")
	WebElement animation;
	
	
	
	
	By productVisibility = By.xpath("//div[contains(@class,'mb-3')]");
	By addToCart = By.xpath("//button[2]");
    By toastmessage = By.id("toast-container");
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productVisibility);
		return products;
	}
	
	
	
	public WebElement getProductByName(String productName)
	{
		

		WebElement product = getProductList().stream().filter(

				prod -> prod.findElement(By.xpath("//h5")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		
		return product;
	}
	
	
	public void addProductToCart(String productName)
	
	{
		getProductByName(productName).findElement(addToCart).click();
//		waitForElementToDisappear(animation);
//		waitForElementToAppear(toastmessage);

		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	

}
