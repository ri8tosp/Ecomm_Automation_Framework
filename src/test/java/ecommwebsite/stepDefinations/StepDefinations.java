
package ecommwebsite.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import ecommwebsite.pageobjects.CheckOutPage;
import ecommwebsite.pageobjects.ConfirmationPage;
import ecommwebsite.pageobjects.LandingPage;
import ecommwebsite.pageobjects.MyCartPage;
import ecommwebsite.pageobjects.ProductCatalogue;
import ecommwebsite.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinations extends BaseTest

{
    LandingPage landingPage;
	ProductCatalogue productCatalogue;
	MyCartPage cartPage;
	ConfirmationPage confirmedOrder;

	@Given("I am on Ecommerce page")
	public void I_am_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	
	@Given("^User logs in with (.+) and (.+) and lands into the product page$")
	
	public void user_logs_in_username_and_password_and_lands_into_the_product_page(String username, String pwd)
	{
		
		productCatalogue = landingPage.logIntoApp(username, pwd);
	}
	
	@When("^I add the product (.+) to cart$")
	public void I_add_product_to_cart(String productName)
	{

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);
	}
	
	@When("^at checkout page I validate that the (.+) is present and then submit the order$")
	public void I_validate_the_product_is_present_and_then_submit_the_order(String productName)
	{
        cartPage = productCatalogue.goToCart();

		Boolean isProductDisplayed = cartPage.isProductDisplayed(productName);

		Assert.assertTrue(isProductDisplayed);

		CheckOutPage checkOutPage = cartPage.clickingOnCheckOut();

		checkOutPage.selectingCountry("India");

		 confirmedOrder = checkOutPage.placeOrder();
	}
	
	@Then("I verify that {string} is displayed on ConfirmationPage")
	public void I_verify_that_confirmation_message_is_displayed(String confirmationMessage)
	{
		Assert.assertEquals(confirmedOrder.getConfirmationMsg(), confirmationMessage);
	}
	
	
}
