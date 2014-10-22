package com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNav;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlobalNavPageObject {

	private static final String HUBS_XPATH_FORMAT = ".//span[@class='label'][contains(text(),'%s')]";

	@FindBy(css = ".hubs-entry-point")
	private WebElement menuButton;

	@FindBy(css = "nav#hubs")
	private WebElement hubsMenu;

	private WebDriver webDriver;

	public GlobalNavPageObject(WebDriver driver) {
		this.webDriver = driver;

		PageFactory.initElements(this.webDriver, this);
	}

	public GlobalNavPageObject openHub(Hub hub) {

		openHubsMenu();

		new Actions(webDriver)
				.moveToElement(hubsMenu.findElement(By.xpath(String.format(HUBS_XPATH_FORMAT, hub.getLabelText())))).
				perform();

		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return this;
	}

	private GlobalNavPageObject openHubsMenu() {
		new WebDriverWait(webDriver, 20, 2000).until(new ExpectedCondition<Boolean>() {
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

	public WebElement getMenuScreenShotArea() {
		return hubsMenu;
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
