package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;

	// Locators
	private By usernameField = By.id("username");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		WebElement usernameElement = driver.findElement(usernameField);
		usernameElement.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordElement = driver.findElement(passwordField);
		passwordElement.sendKeys(password);
	}

	public void clickLogin() {
		WebElement loginElement = driver.findElement(loginButton);
		loginElement.click();
	}
}
