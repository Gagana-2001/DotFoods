package dotfoods.com.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	public WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement placeOrderBtn;

	@FindBy(xpath = "//input[@placeholder='Select a Shipping Address']")
	private WebElement shippingAddress;

	@FindBy(css = "#vs1__option-2")
	private WebElement shippingOption;

	public void selectShippingAddress() {
		waitForWebElementToAppear(shippingAddress);
		shippingAddress.click();
		waitForWebElementToAppear(shippingOption);
		shippingOption.click();
	}

	public OrderConfirmationPage placeOrder() throws InterruptedException {
		selectShippingAddress();
		Thread.sleep(5000);
		waitForWebElementToAppear(placeOrderBtn);
		waitForWebElementToClickable(placeOrderBtn);
		placeOrderBtn.click();

		return new OrderConfirmationPage(driver);
	}
}