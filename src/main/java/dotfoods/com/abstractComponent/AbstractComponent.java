package dotfoods.com.abstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dotfoods.com.pageObjectModels.CartPage;
import dotfoods.com.pageObjectModels.MyListPage;
import dotfoods.com.pageObjectModels.OrderFormPage;
import dotfoods.com.pageObjectModels.PLPPage;

public class AbstractComponent {

	public WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".icon-Search")
	private WebElement searchIcon;

	@FindBy(css = ".mini-cart__header")
	private WebElement cart;

	@FindBy(id = "ga4MiniCartViewCart")
	private WebElement viewCart;

	@FindBy(css = "a[href*='/shop/order-form/']")
	private WebElement orderForm;

	@FindBy(css = "a[href*='/shop/multiple-lists/']")
	private WebElement myList;

	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForWebElementsToAppear(List<WebElement> element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void waitForWebElementToClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public PLPPage goToPlpPage() {
		searchIcon.click();

		return new PLPPage(driver);
	}

	public CartPage goToCartPage() throws InterruptedException {
		cart.click();
		Thread.sleep(2000);
		waitForWebElementToAppear(viewCart);
		viewCart.click();

		return new CartPage(driver);
	}

	public OrderFormPage goToOrderFormPage() {
		orderForm.click();

		return new OrderFormPage(driver);
	}

	public MyListPage goToMyListPage() {
		myList.click();

		return new MyListPage(driver);
	}
}