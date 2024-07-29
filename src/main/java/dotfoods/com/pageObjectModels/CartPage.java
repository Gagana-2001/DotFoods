package dotfoods.com.pageObjectModels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	public WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".item-wrapper")
	private List<WebElement> cartProduct;

	@FindBy(xpath = "//div[@class='identity-bar']/div[1]/span")
	private List<WebElement> dotNumber;

	@FindBy(css = ".vs__selected")
	private WebElement selectRoute;

	@FindBy(xpath = "//li[@id='vs1__option-0']//div[1]")
	private WebElement route1;

	@FindBy(css = "div[class*='row-reverse'] button:nth-child(1)")
	private WebElement saveBtn;

	@FindBy(css = "button[class='btn btn--large btn--accent']")
	private WebElement checkoutBtn;

	@FindBy(css = ".mini-cart__header")
	private WebElement cartEle;

	@FindBy(id = "ga4MiniCartCreateCart")
	private WebElement createCartBtn;

	@FindBy(css = ".modal-content")
	private WebElement cartModal;

	@FindBy(xpath = "//div[@class='create-cart-form']/div[2]/div/div/div/input")
	private WebElement poNumber;

	@FindBy(css = ".checkmark")
	private WebElement activeCheckBox;

	@FindBy(css = ".blue-button.v-actions__submit")
	private WebElement createCartCta;

	@FindBy(id = "ga4MiniCartViewAllCarts")
	private WebElement viewAllCart;

	@FindBy(xpath = "//a[@class='cart no-highlights active']")
	private WebElement activeCart;

	@FindBy(css = ".cart-carousel-item.no-highlights.active.selected")
	private WebElement cartOfCartPage;

	@FindBy(xpath = "//div[@class='cart-carousel-item no-highlights active selected']/div[1]/div/div")
	private WebElement poNum;

	public Boolean verifyProductIncart(String dotNum) {
		waitForWebElementsToAppear(cartProduct);
		Boolean match = dotNumber.stream().anyMatch(product -> product.getText().equals(dotNum));

		return match;
	}

	public void selectRoute() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);
		waitForWebElementToAppear(selectRoute);
		waitForWebElementToClickable(selectRoute);
		selectRoute.click();
		waitForWebElementToAppear(route1);
		route1.click();
		saveBtn.click();
	}

	public CheckOutPage goToCheckOutPage() throws InterruptedException {
		selectRoute();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		waitForWebElementToClickable(checkoutBtn);
		checkoutBtn.click();

		return new CheckOutPage(driver);
	}

	public void createCart(String cartName) throws InterruptedException {
		cartEle.click();
		Thread.sleep(2000);
		waitForWebElementToAppear(createCartBtn);
		createCartBtn.click();
		waitForWebElementToAppear(cartModal);
		Thread.sleep(3000);
		poNumber.clear();
		poNumber.sendKeys(cartName);
		activeCheckBox.isSelected();
		createCartCta.click();
	}

	public void viewAllCart() throws InterruptedException {
		Thread.sleep(3000);
		waitForWebElementToAppear(cartEle);
		cartEle.click();
		Thread.sleep(2000);
		waitForWebElementToAppear(viewAllCart);
		viewAllCart.click();
	}

	public String verifyCartCreation(String cartName) throws InterruptedException {
		waitForWebElementToAppear(activeCart);
		String name = activeCart.findElement(By.xpath("./div[1]/span")).getText();

		return name;
	}

	public boolean verifyActiveCart(String cartName) {
		if (activeCart.findElement(By.cssSelector(".cart-tag.green")) != null) {
			return true;
		}

		return null != null;
	}
}