package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class SearchResultsPage {

	WebDriver driver;
	WebDriverUtils utility;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		utility = new WebDriverUtils(driver);
		System.out.println("I am inside SearchResultsPage");
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='item-root-1iS']")
	public List<WebElement> resultItems;

	@FindBy(xpath = "//button[@title='Add to Cart'])[1]")
	public WebElement A2CButton;

	@FindBy(xpath = "//sfsdgrrt'])[1]")
	public WebElement A2CButton1;

	public By txtProductName = By.cssSelector(".item-nameAndBtnWrapper-4IV a.item-name-3AC span");
	public By addToCart = By.cssSelector(".galleryCartButton-cartBuyIcon-3LD");

	public List<WebElement> getSearchResultList() {
		utility.waitForElementVisible(resultItems.getFirst(), "First Product");
		return resultItems;
	}

	public WebElement getProduct(String productName) {
		WebElement prod = getSearchResultList().stream()
				.filter(product -> product.findElement(txtProductName).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProduct(productName);
		utility.clickElement(prod.findElement(addToCart), "Add to Cart button in Search Results page");
		// prod.findElement(addToCart).click();
		utility.logInformation(String.format("Clicked on add product to cart for %s", productName));
		return;
	}

}
