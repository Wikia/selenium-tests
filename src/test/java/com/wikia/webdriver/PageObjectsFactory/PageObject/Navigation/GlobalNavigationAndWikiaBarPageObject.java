package com.wikia.webdriver.PageObjectsFactory.PageObject.Navigation;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GlobalNavigationAndWikiaBarPageObject extends WikiBasePageObject {
	@FindBy(css="li.topNav.Video_Games a")
	private WebElement VideoGamesTopNavLink;
	@FindBy(css="li.topNav.Entertainment a")
	private WebElement EntertainmentTopNavLink;
	@FindBy(css="li.topNav.LifeStyle a")
	private WebElement LifestyleTopNavLink;

	@FindBy(css=".wikiabar-button[data-index=0]")
	private WebElement VideoGamesWikiaBarLink;
	@FindBy(css=".wikiabar-button[data-index=1]")
	private WebElement EntertainmentWikiaBarLink;
	@FindBy(css=".wikiabar-button[data-index=2]")
	private WebElement LifestyleWikiaBarLink;

	@FindBy(css="#WikiaHubs")
	private WebElement WikiaHubsElement;


	public enum vertical {
		Video_games, Entertainment, LifeStyle
	}

	public GlobalNavigationAndWikiaBarPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String clickVerticalInGlobalNavigation(vertical vert) {
		WebElement inp;
		switch (vert) {
			case Video_games:
				inp = VideoGamesTopNavLink;
				break;
			case Entertainment:
				inp = EntertainmentTopNavLink;
				break;
			case LifeStyle:
			default:
				inp = LifestyleTopNavLink;
				break;
		}
		waitForElementClickableByElement(inp);
		inp.click();
		waitForElementByElement(WikiaHubsElement);

		return inp.getAttribute("href");
	}

	public String clickVerticalInWikiaBar(vertical vert) {
		WebElement inp;
		switch (vert) {
			case Video_games:
				inp = VideoGamesWikiaBarLink;
				break;
			case Entertainment:
				inp = EntertainmentWikiaBarLink;
				break;
			case LifeStyle:
			default:
				inp = LifestyleWikiaBarLink;
				break;
		}
		waitForElementClickableByElement(inp);
		inp.click();
		waitForElementByElement(WikiaHubsElement);

		return inp.getAttribute("href");
	}

	private String getVerticalTitle(vertical vert) {
		switch (vert) {
			case Video_games:
				return "Video Games";
			case Entertainment:
				return "Entertainment";
			case LifeStyle:
			default:
				return "Lifestyle";
		}
	}

	public Boolean checkCurrentPageIsVertical(vertical vert, String url) {
		return verifyTitle(getVerticalTitle(vert)) && (getCurrentUrl() == url);
	}
}
