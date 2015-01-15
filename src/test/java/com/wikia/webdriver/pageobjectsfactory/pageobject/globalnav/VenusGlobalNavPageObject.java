package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.ElementStateHelper;
import com.wikia.webdriver.common.core.configuration.ConfigurationFactory;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VenusGlobalNavPageObject {

	private static final String HUBS_XPATH_FORMAT = ".//a[./span[@class='label'][contains(text(),'%s')]]";

	@FindBy(css = ".hubs-entry-point")
	private WebElement menuButton;

	@FindBy(css = "#hubs")
	private WebElement hubsMenu;

	@FindBy(css = ".gamestar-logo")
	private WebElement gameStarLink;

	@FindBy(css = ".wikia-logo")
	private WebElement wikiaLogo;

	@FindBy(css = "#searchSelect")
	private WebElement searchSelect;

	@FindBy(css = "#searchLabelSingle")
	private WebElement inlineSearch;

	@FindBy(css = "#searchInput")
	private WebElement searchInput;

	private WebDriver driver;

	private DropDownComponentObject accountNavigation;

	public VenusGlobalNavPageObject(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(this.driver, this);
	}

	public WebElement openHub(Hub hub) {
		openHubsMenu();

		final WebElement destinationHub = hubsMenu.findElement(By
			.xpath(String.format(HUBS_XPATH_FORMAT, hub.getLabelText())));

		new Actions(driver)
			.moveToElement(destinationHub).
			perform();

		new WebDriverWait(driver, 5, 150)
			.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(destinationHub, "class", "active"));

		return destinationHub;
	}

	public String getHubLink(WebElement hub) {
		return hub.getAttribute("href");
	}

	private VenusGlobalNavPageObject openHubsMenu() {
		new WebDriverWait(driver, 20, 2000).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				if (!hubsMenu.isDisplayed()) {
					menuButton.click();
					return false;
				}
				return true;
			}
		});

		return this;
	}

	public VenusGlobalNavPageObject openHubsMenuViaHover() {
		new Actions(driver)
			.moveToElement(menuButton)
			.perform();

		new WebDriverWait(driver, 5).until(CommonExpectedConditions.elementVisible(hubsMenu));
		return this;
	}

	public boolean isHubsMenuOpened() {
		return hubsMenu.isDisplayed();
	}

	public boolean isGameStarLogoDisplayed() {
		return ElementStateHelper.isElementVisible(gameStarLink, driver);
	}

	public WebElement getMenuScreenShotArea() {
		return hubsMenu;
	}

	public HomePageObject clickWikiaLogo() {
		String environment = ConfigurationFactory.getConfig().getEnv();
		if (!"prod".equals(environment) && !environment.contains("dev")) {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(
				CommonExpectedConditions.valueToBePresentInElementsAttribute(wikiaLogo, "href", environment)
			);
		}

		wikiaLogo.click();
		return new HomePageObject(driver);
	}

	public SearchPageObject searchGlobally(String query) {
		new Select(searchSelect)
			.selectByValue("global");
		searchInput.sendKeys(query);
		searchInput.submit();
		return new SearchPageObject(driver);
	}

	public SignUpPageObject signUp() {
		return getAccountNavigation().openDropDown().clickSignUpLink();
	}

	public boolean isLocalSearchDisabled() {
		return
			!ElementStateHelper.isElementVisible(searchSelect, driver) &&
				ElementStateHelper.isElementVisible(inlineSearch, driver);
	}

	private DropDownComponentObject getAccountNavigation() {
		if (accountNavigation == null) {
			accountNavigation = new DropDownComponentObject(driver);
		}
		return accountNavigation;
	}

	public enum Hub {
		COMICS("Comics"), TV("TV"), MOVIES("Movies"), MUSIC("Music"),
		BOOKS("Books"), GAMES("Games"), LIFESTYLE("Lifestyle");

		private final String labelText;

		Hub(String labelText) {
			this.labelText = labelText;
		}

		public String getLabelText() {
			return labelText;
		}
	}
}
