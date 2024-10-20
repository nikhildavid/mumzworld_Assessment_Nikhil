package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class MiniCart {

	WebDriver driver;
	WebDriverUtils utility;

	public MiniCart(WebDriver driver) {
		this.driver = driver;
		utility = new WebDriverUtils(driver);
		initialiseObjects();
	}

	@FindBy(css = ".feedbackPopup-root-1VT a[title='View Bag']")
	private WebElement btnViewBag;

	public void clickViewBagButton() throws InterruptedException {
		utility.waitForElementVisible(btnViewBag, "View Bag Button");
		utility.jsClick(btnViewBag, "View Bag Button");
		Thread.sleep(3000);
		return;
	}

	private void initialiseObjects() {

		PageFactory.initElements(driver, this);

	}
}
