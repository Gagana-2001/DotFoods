package dotfoods.com.pageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input28")
	private WebElement userId;

	@FindBy(css = ".button-primary")
	private WebElement next;

	@FindBy(xpath = "(//div[@class=\"authenticator-description\"])[3]/div[2]/a")
	private WebElement selectEle;

	@FindBy(id = "input90")
	private WebElement passwordEle;

	@FindBy(css = ".btn.btn--accent.btn--small")
	private WebElement cookieCta;

	public HomePage login(String userName, String password) {
		userId.sendKeys(userName);
		next.click();
		waitForWebElementToAppear(selectEle);
		selectEle.click();
		waitForWebElementToAppear(passwordEle);
		passwordEle.sendKeys(password);
		next.click();
		waitForWebElementToAppear(cookieCta);
		cookieCta.click();

		return new HomePage(driver);
	}
}