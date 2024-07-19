package dotfoods.com.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {

	public WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}