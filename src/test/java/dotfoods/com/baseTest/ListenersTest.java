package dotfoods.com.baseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import dotfoods.com.resources.ExtentReporterNG;

public class ListenersTest extends BaseTest implements ITestListener {
	private static ExtentReports extent = ExtentReporterNG.getExtentReport();
	private ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread Safe

	@Override
	public synchronized void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getMethod().getMethodName());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		String file = null;
		try {
			file = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(file, result.getMethod().getMethodName());
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		// not implemented
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	@Override
	public synchronized void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		// not implemented
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
	}

}
