package dotfoods.com.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import dotfoods.com.baseTest.BaseTest;
import dotfoods.com.pageObjectModels.CartPage;
import dotfoods.com.pageObjectModels.CheckOutPage;
import dotfoods.com.pageObjectModels.LoginPage;
import dotfoods.com.pageObjectModels.OrderConfirmationPage;
import dotfoods.com.pageObjectModels.OrderFormPage;
import dotfoods.com.pageObjectModels.PLPPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

	private LoginPage loginPage;
	private PLPPage plpPage;
	private CartPage cartPage;
	private CheckOutPage checkOutPage;
	private OrderConfirmationPage orderConfPage;
	private OrderFormPage orderFormPage;

	@Given("Landed on the Dot Foods URL")
	public void I_landed_On_DotFoods_Page() throws IOException {
		loginPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_In_UserName_Password(String userName, String password) {
		loginPage.login(userName, password);
	}

	@When("Navigate to PLP Page")
	public void navigated_on_PLPPage() {
		plpPage = loginPage.goToPlpPage();
	}

	@When("^Search for the product (.+)$")
	public void search_for_Product(String dotNumber) {
		plpPage.searchForProduct(dotNumber);
	}

	@Then("^Verify the search result for (.+)$")
	public void verify_Search_result(String dotNumber) {
		Boolean match1 = plpPage.verifySearchResult(dotNumber);
		Assert.assertTrue(match1);
	}

	@When("AddProduct to Cart")
	public void addProduct_To_Cart() {
		plpPage.addToCart();
	}

	@When("Navigate to Cart Page")
	public void naviagte_To_CartPage() throws InterruptedException {
		cartPage = plpPage.goToCartPage();
	}

	@Then("^Verify the product in cart for (.+)$")
	public void verify_Product_In_Cart(String dotNumber) {
		Boolean match = cartPage.verifyProductIncart(dotNumber);
		Assert.assertTrue(match);
	}

	@When("Checkout to checkoutPage")
	public void checkout_process() throws InterruptedException {
		checkOutPage = cartPage.goToCheckOutPage();
	}

	@Then("PlaceOrder for the Product")
	public void placeOrder() throws InterruptedException {
		orderConfPage = checkOutPage.placeOrder();
	}

	@And("^Verify the orderPlacement Message (.+)$")
	public void verify_confirmation_message(String message) {
		Boolean match2 = orderConfPage.verifyConfrimationMessage(message);
		Assert.assertTrue(match2);
	}

	@When("Navigate to Order From page")
	public void Navigate_To_OrderForm() {
		orderFormPage = loginPage.goToOrderFormPage();
	}

	@When("^Specify the DotNumber (.+) and quantity (.+)$")
	public void Specify_Dotnum_Qty(String dotNum1, String qty) {
		orderFormPage.specifyTheDotNumberAndQty(dotNum1, qty);
	}

	@When("^Add new Row and specify the dotNumber (.+) and quantity (.+)$")
	public void add_New_Row(String dotNum2, String qty) throws InterruptedException {
		orderFormPage.addRow(dotNum2, qty);
	}

	@Then("Verify the Display of product result")
	public void Verify_The_Result() {
		Boolean productInfo = orderFormPage.verifyDisplayOfProductInfo();
		Assert.assertTrue(productInfo);
	}

	@When("Add all products to cart")
	public void add_AllProduct_ToCart() {
		orderFormPage.addAllToCart();
	}

	@And("^Verify the product in cart page for (.+)$")
	public void verify_Product_In_Cart_Page(String dotNumber) {
		cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductIncart(dotNumber);
		Assert.assertTrue(match);
	}
}