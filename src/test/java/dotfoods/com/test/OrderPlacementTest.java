package dotfoods.com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dotfoods.com.baseTest.BaseTest;
import dotfoods.com.java.CartPage;
import dotfoods.com.java.CheckOutPage;
import dotfoods.com.java.LoginPage;
import dotfoods.com.java.OrderConfirmationPage;
import dotfoods.com.java.PLPPage;

public class OrderPlacementTest extends BaseTest {

	// Adding product to cart from PLP page and selecting Route 1 and checkout
	// process to select shipping address and placing order

	@BeforeMethod
	public LoginPage launchApplication() throws IOException {
		driver = initialize();
		loginPage = new LoginPage(driver);

		return loginPage;
	}

	@AfterMethod
	public void closeConnection() {
		log.info("Closing the browser");
		driver.close();
	}

	@Test(dataProvider = "getData")
	public void orderPlacementThroughJson(HashMap<String, String> input) throws InterruptedException {

		loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		log.info("Loggged in to application with username {} and password {}", prop.getProperty("userName"),
				prop.getProperty("password"));
		PLPPage plpPage = loginPage.goToPlpPage();
		log.info("Navigating to PLP Page");
		plpPage.searchForProduct(input.get("dotNum"));
		log.info("Searching for product with dot number {}", input.get("dotNum"));
		Boolean match1 = plpPage.verifySearchResult(input.get("dotNum"));
		log.info("Verifying the search result for dot number {}", input.get("dotNum"));
		Assert.assertTrue(match1);
		plpPage.addToCart();
		log.info("Adding Product to cart");
		CartPage cartPage = plpPage.goToCartPage();
		log.info("Navigating to Cart Page");
		Boolean match = cartPage.verifyProductIncart(input.get("dotNum"));
		log.info("Verifying the cart for product with dotNumber {}", input.get("dotNum"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
		log.info("Navigating to CheckoutPage");
		OrderConfirmationPage orderConfPage = checkOutPage.placeOrder();
		log.info("Placing an order for product with dotNumber", input.get("dotNum"));
		Boolean match2 = orderConfPage.verifyConfrimationMessage(input.get("confMessage"));
		log.info("Verifying the confirmation message");
		Assert.assertTrue(match2);
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\dotfoods\\com\\data\\OrderPlacement.json");

		return new Object[][] { { data.get(0) } };

	}

//	@Test()
//	public void orderPlacement() throws InterruptedException {
//		String userName = prop.getProperty("userName");
//		String password = prop.getProperty("password");
//		String dotNum = prop.getProperty("dotNum");
//		String message = prop.getProperty("confMessage");
//		loginPage.login(userName, password);
//		PLPPage plpPage = loginPage.goToPlpPage();
//		plpPage.searchForProduct(dotNum);
//		Boolean match1 = plpPage.verifySearchResult(dotNum);
//		Assert.assertTrue(match1);
//		plpPage.addToCart();
//		CartPage cartPage = plpPage.goToCartPage();
//		Boolean match = cartPage.verifyProductIncart(dotNum);
//		Assert.assertTrue(match);
//		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
//		OrderConfirmationPage orderConfPage = checkOutPage.placeOrder();
//		Boolean match2 = orderConfPage.verifyConfrimationMessage(message);
//		Assert.assertTrue(match2);
//	}

	// using pagination
//	@Test()
//	public void orderPlacement() throws InterruptedException {
//
//		loginPage.login(userName, password);
//		PLPPage plpPage = loginPage.goToPlpPage();
//		plpPage.addToCart(dotNum);
//		CartPage cartPage = plpPage.goToCartPage();
//		Boolean match = cartPage.verifyProductIncart(dotNum);
//		Assert.assertTrue(match);
//		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
//		OrderConfirmationPage orderConfPage = checkOutPage.placeOrder();
//		Boolean match2 = orderConfPage.verifyConfrimationMessage(message);
//		Assert.assertTrue(match2);
//	}
}