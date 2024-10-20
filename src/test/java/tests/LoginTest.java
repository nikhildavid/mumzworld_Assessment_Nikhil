package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class LoginTest extends BaseTest {

	private ExtentTest extentTest;

	@Test(dataProvider = "loginData")
	public void testLogin(String username, String password) {

		extentTest = extent.createTest("Testing login with Username: " + username + " and Password: " + password);
		// Log the test data being used
		extentTest.info("Testing login with Username: " + username + " and Password: " + password);

		// Perform login actions (replace with your actual logic)
		boolean loginSuccessful = performLogin(username, password); // Stub for login logic
		// Validate the result
		// Assert.assertTrue(loginSuccessful, "Login failed for user: " + username);
		extentTest.pass("trial");
		if (loginSuccessful) {

			extentTest.info("Login test passed for user: " + username);
			extentTest.pass("Login test passed for user: " + username);
		} else {
			extentTest.info("Login test failed for user: " + username);
			extentTest.fail("Login test failed for user: " + username);
		}
	}

	// Data Provider for login credentials
	@DataProvider(name = "loginData")
	public Object[][] loginData() {
		return new Object[][] { { "user1", "pass1" }, { "user2", "pass2" }, { "user3", "pass3" },
				{ "user4", "pass4" } };
	}

	// Stub method to simulate login
	private boolean performLogin(String username, String password) {
		// Replace with your actual login logic
		return "user2".equals(username) && "pass2".equals(password); // Example success condition
	}

}
