
package ecommwebsite.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommwebsite.abstractComponents.Abstractcomponents;

public class ConfirmationPage extends Abstractcomponents

{

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[contains(text(),' Thankyou for the order. ')]")
	WebElement confirmation;

	public String getConfirmationMsg()
	 
     {  
	
	     return confirmation.getText();
     }

}
