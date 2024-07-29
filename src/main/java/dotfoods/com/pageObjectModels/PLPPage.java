package dotfoods.com.pageObjectModels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class PLPPage extends AbstractComponent {

	public WebDriver driver;

	public PLPPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "catalogLookup")
	private WebElement searchInputEle;

	@FindBy(css = ".icon-Search")
	private WebElement searchIcon;

	@FindBy(css = ".yellow-button")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//div[@class='product-card-detail']")
	private WebElement productDetail;

	@FindBy(xpath = "//div[@class='product-card-detail__item-numbers']/div[1]/span")
	private WebElement dotNumEle;

	@FindBy(css = ".favorite-button")
	private WebElement favoiteIcon;

	@FindBy(xpath = "//div[@class='multiple-lists-dropdown__content']/label[1]/span")
	private WebElement listCheckbox;

	@FindBy(css = ".icon.icon.icon-Cross")
	private WebElement closeIcon;

	public void searchForProduct(String dotNumber) {
		searchInputEle.sendKeys(dotNumber);
		searchIcon.click();
	}

	public Boolean verifySearchResult(String dotNumber) {
		waitForWebElementToAppear(productDetail);
		Boolean match = dotNumEle.getText().equalsIgnoreCase(dotNumber);

		return match;
	}

	public void addToCart() {
		waitForWebElementToAppear(addToCartBtn);
		addToCartBtn.click();
	}

	public void addProductToList() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,50)");
		Thread.sleep(7000);
		favoiteIcon.click();
		Thread.sleep(1000);
		waitForWebElementToAppear(listCheckbox);
		waitForWebElementToClickable(listCheckbox);
		boolean isSelected = listCheckbox.isSelected();
		if (!isSelected) {
			listCheckbox.click();
		}
		waitForWebElementToClickable(closeIcon);
		closeIcon.click();
	}

//	By productsBy = By.xpath("//div[@class='product-card-detail']");
//	By addToCart = By.cssSelector("button[class='yellow-button']");
//	By favorite = By.cssSelector(".favorite-button");
//
//	@FindBy(xpath = "//div[@class='product-card-detail']")
//	private List<WebElement> products;
//
//	@FindBy(css = ".blue-navigate-next")
//	private WebElement nextBtn;

//Using pagination scanning for product in all the pages.
//	public List<WebElement> getProductList() {
//	waitForElementToAppear(productsBy);
//
//	return products;
//}
//
//public WebElement getProduct(String productName) {
//	WebElement prod = getProductList().stream().filter(product -> product
//			.findElement(By.xpath("./div[2]/div[1]/div[3]/div[1]/span")).getText().equals(productName)).findFirst()
//			.orElse(null);
//
//	return prod;
//}
//
//public CartPage addToCart(String dotNum) throws InterruptedException {
//	WebElement prod;
//	do {
//		prod = getProduct(dotNum);
//		if (prod == null) {
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("window.scrollBy(0,4000)");
//			nextBtn.click();
//			Thread.sleep(5000);
//		} else {
//			prod.findElement(addToCart).click();
//			return new CartPage(driver);
//		}
//	} while (prod == null);
//
//	return null;
//}
}