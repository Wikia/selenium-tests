package com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNav;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikia.webdriver.Common.Core.CommonExpectedConditions;
import com.wikia.webdriver.Common.Core.ElementStateHelper;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;

public class VenusGlobalNavPageObject {

	private static final String HUBS_XPATH_FORMAT = ".//a[./span[@class='label'][contains(text(),'%s')]]";

	@FindBy(css = ".hubs-entry-point")
	private WebElement menuButton;

	@FindBy(css = "nav#hubs")
	private WebElement hubsMenu;

	@FindBy(css = ".gamestar-logo")
	private WebElement gameStarLink;

	@FindBy(css = ".global-navigation-item.start-wikia-container")
	private WebElement startNewWikiButton;

	private WebDriver driver;

	public VenusGlobalNavPageObject(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(this.driver, this);
	}

	public VenusGlobalNavPageObject openHub(Hub hub) {
		openHubsMenu();

		final WebElement destinationHub = hubsMenu.findElement(By
				.xpath(String.format(HUBS_XPATH_FORMAT, hub.getLabelText())));

		new Actions(driver)
				.moveToElement(destinationHub).
				perform();

		new WebDriverWait(driver, 5, 150)
				.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(destinationHub, "class", "active"));

		return this;
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

	public boolean isGameStarLogoDisplayed() {
		return ElementStateHelper.isElementVisible(gameStarLink, driver);
	}

	public WebElement getMenuScreenShotArea() {
		return hubsMenu;
	}

	public CreateNewWikiPageObjectStep1 clickStartNewWiki(){
		startNewWikiButton.click();
		return new CreateNewWikiPageObjectStep1(driver);
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
