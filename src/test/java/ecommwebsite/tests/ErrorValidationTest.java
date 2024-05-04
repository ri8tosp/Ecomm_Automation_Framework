package ecommwebsite.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import ecommwebsite.pageobjects.CheckOutPage;
import ecommwebsite.pageobjects.ConfirmationPage;
import ecommwebsite.pageobjects.MyCartPage;
import ecommwebsite.pageobjects.ProductCatalogue;
import ecommwebsite.testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest

{
	
	// A test case designed to check for negative scenarios 
	// note -> have talked with & team about test strategy and concluded that 
	// we aren't going with one test as one java file but 
	// we are clubbing multiple test cases into one java file with similar functionality in this case 
	// error validation

	@Test
	public void LoginErrorValidaiton() 
	
	{
		
		
		String productName = "Zara Coat 3";

		ProductCatalogue productCatalogue = landingPage.logIntoApp("harry@styles.com", "ABCD13@abc");
		
		AssertJUnit.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	
	
	@Test
	public void productErrorValidation() throws IOException

	{

		String productName = "Zara Coat 3";

		ProductCatalogue productCatalogue = landingPage.logIntoApp("rahulexample@abc.com", "ABCD123@abc");

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);

		MyCartPage cartPage = productCatalogue.goToCart();

		Boolean isProductDisplayed = cartPage.isProductDisplayed("Zara boat");

		Assert.assertFalse(isProductDisplayed);

		

		


	}
	

}
