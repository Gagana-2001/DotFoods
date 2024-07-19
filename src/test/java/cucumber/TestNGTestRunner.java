package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = {
		"dotfoods.com.stepDefinitions" }, monochrome = true, tags = "@OrderForm or @OrderPlacement", plugin = {
				"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}