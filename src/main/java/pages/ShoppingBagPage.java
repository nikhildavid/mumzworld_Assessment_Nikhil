package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class ShoppingBagPage {

	WebDriver driver;
	WebDriverUtils utility;

	public ShoppingBagPage(WebDriver driver) {
		this.driver = driver;
		utility = new WebDriverUtils(driver);
		initialiseObjects();
	}

	@FindBy(css = ".product-root-Eaj .product-root-19v")
	private List<WebElement> bagProducts;

	@FindBy(css = ".proceedToCheckoutBtn-root-2ra button[title='Proceed to Checkout']")
	private WebElement btnCheckout;

	By txtCartProductName = By.cssSelector(".product-name-2ST");
	By btnIncreaseQuantity = By.cssSelector(".product-detailsRight-2ix button[aria-label='Increase Quantity'] span");

	public List<WebElement> getCartProducts() {
		return bagProducts;
	}

	public Boolean isProductDisplayedInCart(String productName) {
		Boolean match = getCartProducts().stream().anyMatch(
				cartProduct -> cartProduct.findElement(txtCartProductName).getText().equalsIgnoreCase(productName));
		return match;
	}

	public WebElement getCartProduct(String productName) {
		WebElement prod = getCartProducts().stream()
				.filter(cartProduct -> cartProduct.findElement(txtCartProductName).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void clickIncreaseQuantityButton(String productName) {
		getCartProduct(productName).findElement(btnIncreaseQuantity).click();
		utility.logInformation(String.format("Increased product quantity for %s", productName));
	}

	public void increaseProductQuantityByCount(String productName, int count) {
		for (int i = 0; i < count; i++) {
			clickIncreaseQuantityButton(productName);
		}
		return;
	}

	public void clickProceedToCheckoutButton() {
		utility.jsClick(btnCheckout, "Checkout Button");
	}

	private void initialiseObjects() {

		PageFactory.initElements(driver, this);

	}

}
