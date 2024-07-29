package dotfoods.com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import dotfoods.com.baseTest.BaseTest;
import dotfoods.com.pageObjectModels.MyListPage;
import dotfoods.com.pageObjectModels.PLPPage;

public class MyListTest extends BaseTest {

	@Test
	public void addToList() throws InterruptedException {

		// Adding product to MyList and verifying the product in list
		loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		log.info("Loggged in to application with username {} and password {}", prop.getProperty("userName"),
				prop.getProperty("password"));
		PLPPage plpPage = loginPage.goToPlpPage();
		log.info("Navigating to PLP Page");
		plpPage.searchForProduct(prop.getProperty("dotNumber"));
		log.info("Searching for the product with DOT number: {}", prop.getProperty("dotNumber"));
		plpPage.verifySearchResult(prop.getProperty("dotNumber"));
		log.info("Verified search result for product with DOT number: {}", prop.getProperty("dotNumber"));
		plpPage.addProductToList();
		log.info("Added product with DOT number: {} to MyList", prop.getProperty("dotNumber"));
	}

	@Test(dependsOnMethods = { "addToList" })
	public void myListProductVerification() throws InterruptedException {
		MyListPage myListPage = loginPage.goToMyListPage();
		log.info("Navigated to MyList Page");
		boolean match = myListPage.verifyTheListItems(prop.getProperty("dotNumber"));
		log.info("Verification of product with DOT number: {} in MyList returned: {}", prop.getProperty("dotNumber"),
				match);
		Assert.assertTrue(match);
		log.info("Product with DOT number: {} successfully verified in MyList", prop.getProperty("dotNumber"));
	}
}