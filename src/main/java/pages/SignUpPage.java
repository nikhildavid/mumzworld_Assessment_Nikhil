package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class SignUpPage {

	private WebDriver driver;
	WebDriverUtils utility;

	public SignUpPage(WebDriver driver) {

		this.driver = driver;
		utility = new WebDriverUtils(driver);
		initialiseObjects();
	}

	@FindBy(css = "[name='customer.firstname']")
	private WebElement txtFirstName;

	@FindBy(css = "[name='customer.lastname']")
	private WebElement txtLastName;

	@FindBy(css = "[name='customer.email']")
	private WebElement txtEmail;

	@FindBy(css = "[name='password']")
	private WebElement txtPassword;

	@FindBy(css = "button[type='submit'][title='Register']")
	private WebElement btnRegister;

	public void enterFirstName(String firstName) {
		utility.enterText(txtFirstName, firstName, "First Name");
	}

	public void enterLastName(String lastName) {
		utility.enterText(txtLastName, lastName, "Last Name");
	}

	public void enterEmail(String email) {
		utility.enterText(txtEmail, email, "Email");
	}

	public void enterPassword(String password) {
		utility.enterText(txtPassword, password, "Password");
	}

	public void clickRegisterButton() {
		utility.click(btnRegister);
	}

	public void signupToAccount(String firstName, String lastName, String email, String password)
			throws InterruptedException {
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmail(email);
		enterPassword(password);
		clickRegisterButton();
		Thread.sleep(5000);
	}

	private void initialiseObjects() {

		PageFactory.initElements(driver, this);

	}

}
