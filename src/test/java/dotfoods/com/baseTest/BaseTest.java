package dotfoods.com.baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dotfoods.com.java.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;
	public Properties prop;
	public Logger log;

	public WebDriver initialize() throws IOException {
		log = LogManager.getLogger(BaseTest.class);

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\dotfoods\\com\\resources\\GlobalData.properties");
		prop.load(fis);
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browser = prop.getProperty("browser");
		log.info("Intializing {} Browser", browser);
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Tools\\Selenium Grid\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "D:\\Tools\\Selenium Grid\\msedgedriver.exe");
			driver = new EdgeDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("AppURL"));
		log.info("Launching on application");

		return driver;
	}

	@BeforeClass
	public LoginPage launchApplication() throws IOException {
		driver = initialize();
		loginPage = new LoginPage(driver);

		return loginPage;
	}

	@AfterClass
	public void closeConnection() {
		log.info("Closing the browser");
		driver.close();
	}

	public String getScreenShot(String testCase, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File path = new File(System.getProperty("user.dir") + "//Test-Result//TestNG//" + testCase + ".png");
		FileUtils.copyFile(source, path);

		return System.getProperty("user.dir") + "//Test-Result//TestNG//" + testCase + ".png";
	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
}
