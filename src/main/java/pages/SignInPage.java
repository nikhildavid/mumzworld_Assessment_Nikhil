package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class SignInPage {

	private WebDriver driver;
	WebDriverUtils utility;

	public SignInPage(WebDriver driver) {

		this.driver = driver;
		utility = new WebDriverUtils(driver);
		initialiseObjects();
	}

	@FindBy(css = "[class*='signInPage-content'] button[title='Sign up']")
	private WebElement btnSignUp;

	public void clickOnSignUp() {
		utility.jsClick(btnSignUp, "Sign Up Button");
		return;
	}

	private void initialiseObjects() {

		PageFactory.initElements(driver, this);

	}
}
