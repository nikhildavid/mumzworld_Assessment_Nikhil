package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import reports.ExtentTestManager;

//import com.aventstack.extentreports.ExtentTest;
public class HomePageTest extends BaseTest {

	@BeforeMethod
	public void setupTest() {
		// Initialize ExtentTest for this test case
		test = ExtentTestManager.createTest("Login Test");
	}
	// private ExtentTest extentTest;

	@Test(dataProvider = "productData")
	// @Test
	public void enterProductinSearchbox(String Product) {
		page.getWShomepage().enterProductNameInSearchBox(Product);
	}

	@DataProvider(name = "loginData")
	public Object[][] loginData() {
		return new Object[][] { { "user1", "pass1" }, { "user2", "pass2" }, { "user3", "pass3" },
				{ "user4", "pass4" } };
	}

	@DataProvider(name = "productData")
	public Object[] productData() {
		return new Object[] { "user1", "pass1" };
	}

}
