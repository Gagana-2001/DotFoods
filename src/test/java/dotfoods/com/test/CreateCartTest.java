package dotfoods.com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import dotfoods.com.baseTest.BaseTest;
import dotfoods.com.pageObjectModels.CartPage;

public class CreateCartTest extends BaseTest {

	// Creating cart and verifying the created cart and status of created cart

	@Test
	public void createCart() throws InterruptedException {
		loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		log.info("Loggged in to application with username {} and password {}", prop.getProperty("userName"),
				prop.getProperty("password"));
		CartPage cartPage = new CartPage(driver);
		cartPage.createCart(prop.getProperty("cartName"));
		log.info("Creating cart with cartName {}", prop.getProperty("cartName"));
	}

	@Test(dependsOnMethods = { "createCart" })
	public void verifyCreatedCart() throws InterruptedException {
		CartPage cartPage = new CartPage(driver);
		log.info("Navigating to View All cart Page");
		cartPage.viewAllCart();
		String actualName = cartPage.verifyCartCreation(prop.getProperty("cartName"));
		log.info("Verifying the created cart with cart-name {}", prop.getProperty("cartName"));
		Assert.assertEquals(actualName, prop.getProperty("cartName"));
	}

	@Test(dependsOnMethods = { "verifyCreatedCart" })
	public void verifyStatusOfCreatedCart() {
		CartPage cartPage = new CartPage(driver);
		Boolean active = cartPage.verifyActiveCart(prop.getProperty("cartName"));
		log.info("Verifying the status of created cart with cart-name {}", prop.getProperty("cartName"));
		Assert.assertTrue(active);
	}
}