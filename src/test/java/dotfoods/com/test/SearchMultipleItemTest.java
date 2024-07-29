package dotfoods.com.test;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import dotfoods.com.baseTest.BaseTest;
import dotfoods.com.data.ExcelDriven;
import dotfoods.com.pageObjectModels.HomePage;

public class SearchMultipleItemTest extends BaseTest {

	// Search Multiple Items functionality

	private String[] getDotNumbersFromExcel() throws IOException {
		ExcelDriven excel = new ExcelDriven();
		return excel.getData();
	}

	@Test
	public void searchMultipleItems() throws IOException {
		String[] dotNumber = getDotNumbersFromExcel();
		HomePage homePage = loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		log.info("Loggged in to application with username {} and password {}", prop.getProperty("userName"),
				prop.getProperty("password"));
		homePage.searchMultipleItems(dotNumber);
		log.info("Searching for Items with Dot Numbers {}", (Object) dotNumber);
	}

	@Test(dependsOnMethods = { "searchMultipleItems" }) // retryAnalyzer = Retry.class
	public void verifySearchResult() throws IOException {
		String[] expectedDotNumber = getDotNumbersFromExcel();
		HomePage homePage = new HomePage(driver);
		String[] actualDotNumber = homePage.verifyTheSearchResult();
		log.info("Verifying Search result");
		boolean equal = Arrays.equals(expectedDotNumber, actualDotNumber);
		Assert.assertTrue(equal);
	}
}