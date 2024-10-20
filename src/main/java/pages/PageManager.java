package pages;

import org.openqa.selenium.WebDriver;

public class PageManager {

	public WebDriver driver;

	public PageManager(WebDriver driver) {
		this.driver = driver;

	}

	WebShopHomePage WShomepage;

	public WebShopHomePage getWShomepage() {
		if (WShomepage == null) {
			WShomepage = new WebShopHomePage(driver);
			return WShomepage;
		}
		return WShomepage;
	}

	SearchResultsPage SResults;

	public SearchResultsPage getSearchResultsPage() {

		if (SResults == null) {
			System.out.println("Object SResults is null");
			SResults = new SearchResultsPage(driver);
			return SResults;
		}
		System.out.println("Object SResults is not null");
		return SResults;
	}

	ShoppingBagPage ShopBag;

	public ShoppingBagPage getShoppingBagPage() {

		// System.out.println(driver);
		if (ShopBag == null) {
			ShopBag = new ShoppingBagPage(driver);
			return ShopBag;
		}
		return ShopBag;
	}

	MiniCart MiniCart;

	public MiniCart getMiniCartPopup() {

		// System.out.println(driver);
		if (MiniCart == null) {
			MiniCart = new MiniCart(driver);
			return MiniCart;
		}
		return MiniCart;
	}

	SignInPage SignIn;

	public SignInPage getSignInPage() {

		// System.out.println(driver);
		if (SignIn == null) {
			SignIn = new SignInPage(driver);
			return SignIn;
		}
		return SignIn;
	}

	SignUpPage SignUp;

	public SignUpPage getSignUpPage() {

		// System.out.println(driver);
		if (SignUp == null) {
			SignUp = new SignUpPage(driver);
			return SignUp;
		}
		return SignUp;
	}

}
