package dotfoods.com.pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class OrderFormPage extends AbstractComponent {

	public WebDriver driver;

	public OrderFormPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Dot #']")
	private WebElement dotInputs;

	@FindBy(css = "input[placeholder='Qty']")
	private WebElement qtyInputs;

	@FindBy(css = ".item-detail.product-name")
	private WebElement productInfo;

	@FindBy(css = ".add-row")
	private WebElement addRow;

	@FindBy(css = ".blue-button")
	private WebElement addAllToCart;

	public void specifyTheDotNumberAndQty(String dotNumber, String qty) {
		waitForWebElementToAppear(dotInputs);
		clearInputField(dotInputs);
		dotInputs.sendKeys(dotNumber);
		qtyInputs.click();
		clearInputField(qtyInputs);
		qtyInputs.sendKeys(qty);
	}

	private WebElement findElementByPlaceholder(String placeholder, int index) {
		String xpath = "(//input[@placeholder='" + placeholder + "'])[" + index + "]";

		return driver.findElement(By.xpath(xpath));
	}

	public void addRow(String dotNumber, String qty) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		addRow.click();
		WebElement newDotInput = findElementByPlaceholder("Dot #", 2); // Find the second element
		WebElement newQtyInput = findElementByPlaceholder("Qty", 2); // Find the second element
		clearInputField(newDotInput);
		newDotInput.sendKeys(dotNumber);
		clearInputField(newQtyInput);
		newQtyInput.sendKeys(qty);
		addRow.click();
		Thread.sleep(5000);
	}

	private void clearInputField(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", element);
	}

	public boolean verifyDisplayOfProductInfo() {
		return productInfo.isDisplayed();
	}

	public CartPage addAllToCart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		waitForWebElementToAppear(addAllToCart);
		waitForWebElementToClickable(addAllToCart);
		addAllToCart.click();

		return new CartPage(driver);
	}
}