package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pages.PageManager;
import reports.ExtentManager;
import utils.BrowserManager;
import utils.WebDriverUtils;

public class BaseTest {

	public static ExtentReports extent; // Class-level variable for ExtentReports
	protected static ExtentTest test;
	BrowserManager B = new BrowserManager();
	public WebDriver driver;
	String browserName;
	String baseURL;
	Properties prop = new Properties();
	PageManager page;
	WebDriverUtils utility;

	public static final Logger log = LoggerFactory.getLogger(BaseTest.class);

	@BeforeSuite
	public void setupExtentReports() {
		// extent = ExtentManager.createInstance("reports/Test-Automation-Report.html");
		extent = ExtentManager.getInstance();

	}

	@BeforeTest
	public void intialiseBrowser() {

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
			prop.load(fis);
			browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: prop.getProperty("browser");
			baseURL = prop.getProperty("baseURL");
		} catch (IOException e) {

			e.printStackTrace();
		}
		driver = B.getDriver(browserName);
		page = new PageManager(driver);
		utility = new WebDriverUtils(driver);

		System.out.println(driver);
		launchURL();
	}

	@AfterTest
	public void tearDownBrowser() {
		driver.close();
	}

	@AfterSuite
	public void tearDownExtentReports() {
		extent.flush();

	}

	public void launchURL() {
		driver.get(baseURL);
	}
}
