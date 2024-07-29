package dotfoods.com.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import dotfoods.com.baseTest.BaseTest;
import dotfoods.com.pageObjectModels.HomePage;

public class FooterLinkTest extends BaseTest {

	@Test
	public void verifyBrokenLink() throws MalformedURLException, IOException {

		// Verfying Footer links broken links
		HomePage homePage = loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		log.info("Loggged in to application with username {} and password {}", prop.getProperty("userName"),
				prop.getProperty("password"));
		boolean match = homePage.footerLink();
		log.info("Verifying footer links based on status code");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertFalse(match, "Link Broken");
		log.info("Footer links verification completed. Assertion result: {}",
				match ? "No broken links" : "Link Broken");
		softAssert.assertAll();
	}
}