package dotfoods.com.pageObjectModels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dotfoods.com.abstractComponent.AbstractComponent;

public class MyListPage extends AbstractComponent {

	public WebDriver driver;

	public MyListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//aside[@class='multiple-lists__sidenav']/a[2]")
	private WebElement favList;

	@FindBy(xpath = "//div[@class='wishlist-item-content__item-number'][1]/span")
	private WebElement dotNumber;

	public boolean verifyTheListItems(String dotNum) throws InterruptedException {
		Thread.sleep(5000);
		waitForWebElementToAppear(favList);
		waitForWebElementToClickable(favList);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		favList.click();
		Thread.sleep(3000);
		waitForWebElementToAppear(dotNumber);
		boolean match = dotNumber.getText().equals(dotNum);

		return match;
	}
}