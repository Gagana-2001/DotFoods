package dotfoods.com.java;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class HomePage extends AbstractComponent {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".multiple-item-number")
	private WebElement searchMultiple;

	@FindBy(css = ".modal-content")
	private WebElement modal;

	@FindBy(xpath = "//div[@class='item-number-search-modal']/textarea")
	private WebElement searchTextArea;

	@FindBy(css = ".blue-button.v-actions__submit")
	private WebElement submitBtn;

	@FindBy(xpath = "//div[@class='product-card-detail__item-numbers']/div[1]/span")
	private List<WebElement> dotNumberEle;

	@FindBy(xpath = "//div[@class='footer__column-content']/div/a")
	private List<WebElement> footerLinks;

	public void searchMultipleItems(CharSequence[] ar) {
		searchMultiple.click();
		waitForWebElementToAppear(modal);
		for (CharSequence item : ar) {
			searchTextArea.sendKeys(item);
			searchTextArea.sendKeys(Keys.ENTER);
		}
		waitForWebElementToClickable(submitBtn);
		submitBtn.click();
	}

	public String[] verifyTheSearchResult() {
		waitForWebElementsToAppear(dotNumberEle);
		String[] dotNumbers = new String[dotNumberEle.size()];
		for (int i = 0; i < dotNumberEle.size(); i++) {
			dotNumbers[i] = dotNumberEle.get(i).getText();
		}

		return dotNumbers;
	}

	public boolean footerLink() throws MalformedURLException, IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3000)");
		for (WebElement link : footerLinks) {
			String url = link.getAttribute("href");
			// Opens a connection to the URL. This is an HttpURLConnection object, allowing
			// HTTP requests and responses.
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			// Sets the request method to GET, which only retrieves the Getters from the
			// server, not the entire content.
			conn.setRequestMethod("GET");
			// Establishes the connection to the server.
			conn.connect();
			// Gets the HTTP response code from the server.
			int respCode = conn.getResponseCode();
			if (respCode >= 400) {
				System.out
						.println("The link with text " + link.getText() + " is broken with the status code" + respCode);
				return true;
			}
		}
		return false;
	}
}