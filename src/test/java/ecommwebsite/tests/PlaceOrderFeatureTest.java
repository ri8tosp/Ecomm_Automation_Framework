package ecommwebsite.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommwebsite.pageobjects.CheckOutPage;
import ecommwebsite.pageobjects.ConfirmationPage;
import ecommwebsite.pageobjects.MyCartPage;
import ecommwebsite.pageobjects.OrderPage;
import ecommwebsite.pageobjects.ProductCatalogue;
import ecommwebsite.testComponents.BaseTest;

public class PlaceOrderFeatureTest extends BaseTest {

	@Test(dataProvider = "getData", groups = "Purchase")
	public void placeOrderTestCase(HashMap <String, String > map) throws IOException

	{

		ProductCatalogue productCatalogue = landingPage.logIntoApp(map.get("email"), map.get("pwd"));

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(map.get("productName"));

		MyCartPage cartPage = productCatalogue.goToCart();

		Boolean isProductDisplayed = cartPage.isProductDisplayed(map.get("productName"));

		Assert.assertTrue(isProductDisplayed);

		CheckOutPage checkOutPage = cartPage.clickingOnCheckOut();

		checkOutPage.selectingCountry("India");

		ConfirmationPage confirmedOrder = checkOutPage.placeOrder();

		AssertJUnit.assertEquals(confirmedOrder.getConfirmationMsg(), "THANKYOU FOR THE ORDER.");

	}

	// Verifying that the product ordered is available under the orders tab only
	// after order is placed

	@Test(dependsOnMethods = { "placeOrderTestCase" })
	public void validatingOrderHistoryPage() {

		String productName = "Zara Coat 3";

		ProductCatalogue productCatalogue = landingPage.logIntoApp("rahulexample@abc.com", "ABCD123@abc");

		OrderPage orderPage = productCatalogue.goToOrderPage();

		Assert.assertTrue(orderPage.verifyOrderIsDisplayed(productName));

		System.out.println("The product you added in cart is " + orderPage.displayAddedProductName(productName));

	}

	@DataProvider()
	public Object[][] getData()

	{

		// sending data using HashMap, this is because if there are more than 3
		// parameters in placeOrderTestCase()
		// then it will be very difficult to manage so many parameters in the method as
		// a result better way to handle this is by HashMaP
		
		HashMap <String, String > map = new HashMap <String , String>();
		
	    map.put("email", "rahulexample@abc.com");
	    map.put("pwd", "ABCD123@abc");
	    map.put("productName", "ZARA COAT 3");
	    
	    
		HashMap <String, String > map1 = new HashMap <String , String>();
		
	    map1.put("email", "harry@styles.com");
	    map1.put("pwd", "ABCD123@abc");
	    map1.put("productName", "IPHONE 13 PRO");
	    
	    return new Object[][] { {map},{map1} };
	    
	    
	    /* using the Array way of DataProvider

		return new Object[][] { { "rahulexample@abc.com", "ABCD123@abc", "ZARA COAT 3" },
				{ "harry@styles.com", "ABCD123@abc", "IPHONE 13 PRO" } };
				
		*/		
	}
}
