package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private ExtentManager() {
	}

	private static ExtentReports extent;
	private static ExtentSparkReporter sparkReporter;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static ExtentReports getInstance() {
		if (extent == null) {
			String reportPath = "./reports/TestExecutionReport.html";
			// String reportPath = Configuration.getReportDirectory();
			sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setTheme(Theme.STANDARD);
			sparkReporter.config().setDocumentTitle("Automation Report");
			sparkReporter.config().setReportName("Mumzworld - Automation Test Report");

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "mumzworld");
			extent.setSystemInfo("Environment", "Prod");
			extent.setSystemInfo("Tester", "Nikhil"); 
		}
		return extent;
	}

	public static void flush() {
		if (extent != null) {
			extent.flush();
		}
	}

	public static void createTest(String testName) {
		ExtentTest extentTest = extent.createTest(testName);
		test.set(extentTest);
	}

	public static ExtentTest getTest() {
		return test.get();
	}

}
