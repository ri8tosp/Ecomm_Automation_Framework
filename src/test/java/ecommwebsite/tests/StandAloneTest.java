package ecommwebsite.tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommwebsite.pageobjects.LandingPage;
import junit.framework.Assert;

public class StandAloneTest {

	public static void main(String[] args)

	{

		// Chrome options class for customising the browser functions

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		// Driver object creation

		WebDriver driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// hitting the url

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		// working with the locators in the login page
		

		driver.findElement(By.id("userEmail")).sendKeys("rahulexample@abc.com");
		driver.findElement(By.id("userPassword")).sendKeys("ABCD123@abc");
		driver.findElement(By.id("login")).click();

		// working on the landing page to select an element out of all the elements in
		// the website but before making sure that everything is loaded

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));

		String productName = "Zara Coat 3";

		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));

		// filtering out the element that we want to add to cart to check that
		// functionality working or not

		WebElement product = products.stream().filter(

				prod -> prod.findElement(By.xpath("//h5")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);

		product.findElement(By.xpath("//button[2]")).click();

		// making sure that product added successfully message displayed properly & then
		// clicking on the cart option

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));

		// clicking on the cart button

		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();

		// validating the elements that we clicked on are added to the cart

		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		Boolean match = cartProducts.stream().anyMatch(cartProduct ->

		cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		// clicking on checkout

		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();

		// entering the country for the order

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//div[@class='form-group']/input")), "india").build().perform();

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//div[@class='form-group']//section[contains(@class,'list-group')]"))));

		List<WebElement> countryList = driver
				.findElements(By.xpath("//div[@class='form-group']//section[contains(@class,'list-group')]//button"));

		countryList.stream().filter(country -> country.getText().equalsIgnoreCase("india")).findFirst().orElse(null)
				.click();
		
		//clicking on place order 
		
		driver.findElement(By.xpath("//div[@class='actions']/a[contains(text(), 'Place Order ')]")).click();
		
		
		//Asserting that the order is placed
		
		String confirmationMsg = driver.findElement(By.xpath("//h1[contains(text(),' Thankyou for the order. ')]")).getText();
		
		Assert.assertEquals(confirmationMsg, "THANKYOU FOR THE ORDER.");
		
		//closing the browser 
		
		driver.close();
	}

}
