package ecommwebsite.tests;

import java.io.IOException;
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
	public void placeOrderTestCase(String email, String pwd, String productName) throws IOException

	{

		ProductCatalogue productCatalogue = landingPage.logIntoApp(email, pwd);

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);

		MyCartPage cartPage = productCatalogue.goToCart();

		Boolean isProductDisplayed = cartPage.isProductDisplayed(productName);

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
		return new Object[][] { { "rahulexample@abc.com", "ABCD123@abc", "ZARA COAT 3" },
				{ "harry@styles.com", "ABCD123@abc", "IPHONE 13 PRO" } };
	}
}
