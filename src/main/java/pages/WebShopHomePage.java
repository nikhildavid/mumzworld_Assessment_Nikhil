package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class WebShopHomePage {

	WebDriver driver;
	WebDriverUtils utility;

	public WebShopHomePage(WebDriver driver) {

		this.driver = driver;
		utility = new WebDriverUtils();
		initialiseObjects();
	}

	@FindBy(css = ".headerPanel-account-lMI button[title='Register']")
	private WebElement btnRegister;

	@FindBy(css = ".algoliaSearchBox-form-3JZ input[type='search']")
	private WebElement searchBox;

	@FindBy(css = ".algoliaSearchBox-submit-3Vv[type='submit'] span")
	private WebElement btnSearch;

	public void enterProductNameInSearchBox(String productName) {

		utility.enterText(searchBox, productName, "Search box");
	}

	public void clickSearchButton() {

		utility.click(btnSearch);

	}

	public void searchProduct(String productName) throws InterruptedException {
		enterProductNameInSearchBox(productName);
		Thread.sleep(4000);
		clickSearchButton();
		clickSearchButton();
		Thread.sleep(5000);
		return;
	}

	private void initialiseObjects() {

		PageFactory.initElements(driver, this);

	}

}