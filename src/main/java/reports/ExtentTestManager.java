package reports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private ExtentTestManager() {
	}

	private static ExtentReports extent = ExtentManager.getInstance();

	private static Map<String, ExtentTest> extentTestMap = new HashMap<>();

	public static synchronized ExtentTest createTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put(Thread.currentThread().getName(), test);
		return test;
	}

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(Thread.currentThread().getName());
	}

	// Add logs to the test case
	public static synchronized void logInfo(String message) {
		getTest().info(message);
	}

	public static synchronized void logInfoWithScreenshot(String message, String screenshotPath,
			String screenshotImagePath) {
//		getTest().info(message).addScreenCaptureFromPath(screenshotPath); 
		// String screenshotIconLink="<img src='"+screenshotImagePath+"'+
		// alt='Description of image' width='25' height='25'>";
		String logMessageWithLink = "<a href='" + screenshotPath
				+ "' target='_blank' style='background-color: skyblue; padding: 5px; border-radius: 4px; color: black;'>"
				+ message + "</a>";
		getTest().info(logMessageWithLink);
		// getTest().info(screenshotIconLink);
	}

	public static synchronized void logPass(String message) {
		getTest().pass(message);
	}

	public static synchronized void logFail(String message) {
		getTest().fail(message);
	}

	public static synchronized void logWarning(String message) {
		getTest().warning(message);
	}

	public static synchronized void logScreenshot(String screenshotPath) {
		getTest().addScreenCaptureFromPath(screenshotPath);
	}

}
