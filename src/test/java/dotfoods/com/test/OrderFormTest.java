package dotfoods.com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dotfoods.com.baseTest.BaseTest;
import dotfoods.com.java.CartPage;
import dotfoods.com.java.OrderFormPage;

public class OrderFormTest extends BaseTest {

	// Adding multiple products to cart through order Form and verifying the cart
	// page

	@Test(dataProvider = "getData")
	public void orderForm(HashMap<String, String> input) throws InterruptedException {

		loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		log.info("Loggged in to application with username {} and password {}", prop.getProperty("userName"),
				prop.getProperty("password"));
		OrderFormPage orderFormPage = loginPage.goToOrderFormPage();
		log.info("Navigating to Order Form Page");
		orderFormPage.specifyTheDotNumberAndQty(input.get("dotNumber1"), input.get("quantity"));
		log.info("Specifying DotNumber {} and Qty {}", input.get("dotNumber1"), input.get("quantity"));
		orderFormPage.addRow(input.get("dotNumber2"), input.get("quantity"));
		log.info("Adding New Row");
		log.info("Specifying DotNumber {} and Qty {} to new row", input.get("dotNumber2"), input.get("quantity"));
		Boolean productInfo = orderFormPage.verifyDisplayOfProductInfo();
		log.info("Verifying the result dispaly");
		Assert.assertTrue(productInfo);
		orderFormPage.addAllToCart();
		log.info("Adding product to cart with dot Numbers {} {}", input.get("dotNumber1"), input.get("dotNumber2"));
	}

	@Test(dependsOnMethods = { "orderForm" }, dataProvider = "getData")
	public void verifyCartPageProducts(HashMap<String, String> input) throws InterruptedException {
		CartPage cartPage = new CartPage(driver);
		Boolean product1 = cartPage.verifyProductIncart(input.get("dotNumber1"));
		log.info("Verifying the product with dot number {} in cartPage", input.get("dotNumber1"));
		Assert.assertTrue(product1);
		Boolean product2 = cartPage.verifyProductIncart(input.get("dotNumber2"));
		log.info("Verifying the product with dot number {} in cartPage", input.get("dotNumber1"));
		Assert.assertTrue(product2);
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\dotfoods\\com\\data\\OrderForm.json");

		return new Object[][] { { data.get(0) } };
	}
}