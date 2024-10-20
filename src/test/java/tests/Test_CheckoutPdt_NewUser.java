package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test_CheckoutPdt_NewUser extends BaseTest {

	@Test(description = "Validate product checkout for new user", dataProvider = "getData")
	public void validateProductCheckoutForNewUser(HashMap<String, String> input) throws InterruptedException {
		page.getWShomepage().searchProduct(input.get("searchkey"));
		page.getSearchResultsPage().addProductToCart(input.get("productName"));
		page.getMiniCartPopup().clickViewBagButton();
		page.getShoppingBagPage().increaseProductQuantityByCount(input.get("productName"), 4);
		page.getShoppingBagPage().clickProceedToCheckoutButton();
		page.getSignInPage().clickOnSignUp();
		page.getSignUpPage().signupToAccount(input.get("firstName"), input.get("lastName"), input.get("email"),
				input.get("password"));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\TestData.json";
		return utility.getJsonDataToObjectArray(filePath);

	}

}
