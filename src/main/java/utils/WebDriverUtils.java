package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import reports.ExtentManager;
import reports.ExtentTestManager;

public class WebDriverUtils {

	private WebDriver driver;
	private WebDriverWait wait;
	private static final Logger log = LoggerFactory.getLogger(WebDriverUtils.class);
	public static final String SCRPATH = System.getProperty("user.dir") + File.separator
			+ "src\\test\\resources\\Misc\\ScreenshotIcon.png";

	private static final String SCREENSHOTS_FOLDER_PATH = System.getProperty("user.dir") + File.separator
			+ "screenshots";

	public WebDriverUtils() {
	}

	public WebDriverUtils(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to take a screenshot and attach it to the Extent report
	public void takeScreenshotAndAttachToReport(String screenshotName) {
		try {
			// Take the screenshot
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName + ".png";
			System.out.println(screenshotPath);
			FileHandler.copy(srcFile, new File(screenshotPath));

			// Attach screenshot to Extent report
			ExtentTestManager.logScreenshot(screenshotPath);
			// ExtentManager.getExtentTest().addScreenCaptureFromPath(screenshotPath);

		} catch (IOException e) {
			log.info("Failed to take screenshot: " + e.getMessage());
			// ExtentTestManager.logFail("Failed to take screenshot: " + e.getMessage());
//	ExtentManager.getTest().log(Status.FAIL, "Failed to take screenshot: "
//			 + e.getMessage());
		} catch (WebDriverException e) {
			log.info("Failed to take screenshot: " + e.getMessage());
			// ExtentTestManager.logFail("WebDriver failed to take screenshot: " +
			// e.getMessage());
			// ExtentManager.getExtentTest().log(Status.FAIL, "WebDriver failed to take
			// screenshot: " + e.getMessage());
		}
	}

	// Reusable methods
	public void clickElement(WebElement element, String elementDescription) {
		try {
			element.click();
			log.info("Clicked on Element: " + element.toString());
//		 ExtentManager.getTest().log(Status.PASS, "Clicked on the element: " +
//		element.toString());
//			attachScreenshotToReport();
			logInformationWithScreenshot("Clicked on the element: " + elementDescription);
		} catch (Exception e) {
			ExtentManager.getTest().log(Status.FAIL, "Failed to click on the element: " + element.toString());
			takeScreenshotAndAttachToReport("ClickFailure");
		}
	}

	public void enterValue(WebElement element, String value) {
		try {
			element.sendKeys(value);
			// ExtentManager.getExtentTest().log(Status.PASS,
			// "Entered value: " + value + " into element: " + element.toString());
		} catch (Exception e) {
			// ExtentManager.getExtentTest().log(Status.FAIL,
			// "Failed to enter value: " + value + " into element: " + element.toString());
			takeScreenshotAndAttachToReport("EnterValueFailure");
		}
	}

	public void click(WebElement element) {
		try {
			// waitForElementClickable(element);
			element.click();
			log.info("Clicked on element: " + element.toString());
			// ExtentTestManager.logInfo("Clicked on element: " + element.toString());
		} catch (Exception e) {

			// ExtentManager.getExtentTest().log(Status.FAIL,
			// "Failed to click on element: " + element.toString() + " into element: " +
			// element.toString());
			takeScreenshotAndAttachToReport("EnterValueFailure");
			throw e;
		}

	}

	public void waitForElementClickable(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			log.info("Element is clickable: " + element.toString());
		} catch (Exception e) {
			log.error("Element is not clickable: " + element.toString());
			throw e;
		}
	}

	public void enterText(WebElement element, String text, String elementDescription) {
		try {
			// waitForElementVisible(element);
			element.clear();
			element.sendKeys(text);
			log.info("Entered text '" + text + "' into element: " + elementDescription);
			ExtentTestManager.logInfo("Entered text '" + text + "' into element: " + elementDescription);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error("Failed to enter text into element: " + element.getAccessibleName());
			ExtentTestManager.logFail("Failed to enter text into element: " + elementDescription);
			throw e;
		}
	}

	public void waitForElementVisible(WebElement element, String elementDescription) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			log.info("Element: " + elementDescription + " is visible: ");
		} catch (Exception e) {
			log.error("Element is not visible: " + element.toString());
			throw e;
		}
	}

	public void jsClick(WebElement element, String elementDescription) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			logInformationWithScreenshot("JS click on Element: " + elementDescription);
		} catch (Exception e) {
			log.error("Element " + elementDescription + " is not clicked: ");
			throw e;
		}

	}

	public void logInformation(String message) {
		log.info(message);
		ExtentTestManager.logInfo(message);
	}

	public void logInformationWithScreenshot(String message) {
		log.info(message);
		ExtentTestManager.logInfoWithScreenshot(message, captureScreenshot(), SCRPATH);
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public Object[][] getJsonDataToObjectArray(String filepath) throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(filepath);
		Object[][] results = new Object[data.size()][1];
		int index = 0;
		for (HashMap<String, String> temp : data) {
			results[index++] = new Object[] { temp };
		}
		return results;
	}

	public WebElement waitunitlElementVisible(WebElement e, String ElementInfo) {
		// test.info("Waiting for element: " + ElementInfo);
		wait.until(ExpectedConditions.visibilityOf(e));
		// test.info(ElementInfo,
		// MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(driver),
		// "Screenshot").build());
		return e;

	}

	public void verifyElement_isDisplayed(WebElement e, String ElementInfo) {
		waitunitlElementVisible(e, ElementInfo);
		Assert.assertTrue(e.isDisplayed());
	}

	public String captureScreenshot() {
		try {
			// Take the screenshot
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			createDirectory(SCREENSHOTS_FOLDER_PATH);
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String screenshotPath = SCREENSHOTS_FOLDER_PATH + File.separator + "Screenshot_" + timestamp + ".png";
//			Shutterbug.shootPage(driver,Capture.FULL).withName("Screenshot_" + timestamp).save(SCREENSHOTS_FOLDER_PATH);
			FileHandler.copy(srcFile, new File(screenshotPath));
			return screenshotPath;
		} catch (Exception e) {
			log.info("Failed to take screenshot: " + e.getMessage());
			return null;
		}
	}

	public void attachScreenshotToReport() {
		try {
			// Attach screenshot to Extent report
			ExtentTestManager.logScreenshot(captureScreenshot());
		} catch (Exception e) {
			log.info("Failed to attach report: " + e.getMessage());
		}
	}

	public void createDirectory(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			if (dir.mkdirs()) {
				System.out.println("Directory created: " + dir.getAbsolutePath());
			} else {
				System.out.println("Failed to create directory: " + dir.getAbsolutePath());
				return; // Exit if directory creation failed
			}
		}
	}

}
