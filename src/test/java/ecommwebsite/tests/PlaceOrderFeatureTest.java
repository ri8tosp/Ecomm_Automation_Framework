package ecommwebsite.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	public void placeOrderTestCase(HashMap<String, String> map) throws IOException

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

		Assert.assertEquals(confirmedOrder.getConfirmationMsg(), "THANKYOU FOR THE ORDER.");

	}

	// Verifying that the product ordered is available under the orders tab only
	// after order is placed

	@Test(dependsOnMethods = { "placeOrderTestCase" }, groups="Purchase")
	public void validatingOrderHistoryPage() {

		String productName = "Zara Coat 3";

		ProductCatalogue productCatalogue = landingPage.logIntoApp("rahulexample@abc.com", "ABCD123@abc");

		OrderPage orderPage = productCatalogue.goToOrderPage();

		Assert.assertTrue(orderPage.verifyOrderIsDisplayed(productName));

		System.out.println("The product you added in cart is " + orderPage.displayAddedProductName(productName));

	}
	

	
	
	@DataProvider()
	public Object[][] getData() throws IOException

	{

		List < HashMap <String, String >> data = jsonToHashMap(System.getProperty("user.dir")+ 
				"/src/test/java/ecommwebsite/data/PurchaseOrder.json");

		return new Object[][] { {data.get(0)}, { data.get(1)} };

	}
	
	
	
	/*

	

	*/

	/*
	 * 
	 * //using the Array way of DataProvider
	 * 
	 * @DataProvider()
	 *  public Object[][] getData()
	 * 
	 * {
	 * 
	 * using the Array way of DataProvider
	 * 
	 * return new Object[][] { { "rahulexample@abc.com", "ABCD123@abc",
	 * "ZARA COAT 3" }, { "harry@styles.com", "ABCD123@abc", "IPHONE 13 PRO" } };
	 * 
	 * 
	 * }
	 */

}
