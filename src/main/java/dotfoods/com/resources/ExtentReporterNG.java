package dotfoods.com.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {

	public static ExtentReports getExtentReport() {

		String path = System.getProperty("user.dir") + "//Test-Result//TestNG//testNg.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Dot Foods Report");
		reporter.config().setDocumentTitle("Dot Foods-Testcase Results");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setTimelineEnabled(true);

		reporter.config().setCss(".r-img { width: 50%; } .r-caption { font-size: 14px; color: #333; }");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Project", "Dot Foods");
		extent.setSystemInfo("tester", "Gagana");

		return extent;
	}
}