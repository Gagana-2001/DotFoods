package dotfoods.com.pageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {

	public WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".page-title")
	private WebElement confMessage;

	public boolean verifyConfrimationMessage(String message) {
		waitForWebElementToAppear(confMessage);
		boolean match = confMessage.getText().equalsIgnoreCase(message);

		return match;
	}
}