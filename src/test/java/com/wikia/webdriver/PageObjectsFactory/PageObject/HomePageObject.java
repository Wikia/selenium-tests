package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;

public class HomePageObject extends WikiBasePageObject {

	@FindBy(css="header.wikiahomepage-header a.button")
	private WebElement startWikiButton;
	@FindBy(css="section.grid-2.videogames a img")
	private WebElement OpenVideoGamesHub;
	@FindBy(css="section.grid-2.entertainment a img")
	private WebElement OpenEntertainmentHub;
	@FindBy(css="section.grid-2.lifestyle a img")
	private WebElement OpenLifestyleHub;
	@FindBy(css="a.ajaxLogin")
	private WebElement LoginOverlay;
	@FindBy(css="div#UserLoginDropdown input[name='username']")
	private WebElement UserNameField;
	@FindBy(css="div#UserLoginDropdown a.forgot-password")
	private WebElement ForgotYourPassword;
	@FindBy(css="#WikiaSearch button.wikia-button")
	private WebElement searchButton;
	@FindBy(css="#WikiaSearch input[name='search']")
	private WebElement searchInput;
	@FindBy(css=".hub > a")
	private WebElement hubIndicator;
	@FindBy(css=".preview-pane a.goVisit")
	private List<WebElement> visualizationWikis;
	@FindBy(css="section.grid-1 nav")
	private WebElement languageButton;

	//These Bys are being used to prevent stale browser exception
	private By languageSelectorBy = By.cssSelector(".wikia-menu-button li > a");
	private By languageButtonSelectorBy = By.cssSelector("section.grid-1 nav");

	private String languageDropdownString = "nav.wikia-menu-button";

	public HomePageObject(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public CreateNewWikiPageObjectStep1 startAWiki(String wikiURL)
	{
		startWikiButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[name='label-wiki-form']")));
		if (wikiURL.contains("preview"))
		{
			String currentUrl = driver.getCurrentUrl();
			String desiredUrl = currentUrl.replace("www.wikia.com", "preview.www.wikia.com");
			getUrl(desiredUrl);
		}
		return new CreateNewWikiPageObjectStep1(driver);
	}

	/**
	 * getting current slot setup on visualization component
	 * @return
	 */
	public HashMap<String, Integer> getVisualizationWikisSetup() {
		List<String> wikiList = new ArrayList<String>();
		HashMap<String, Integer> visualizationSetup = new HashMap<String, Integer>();
		for (WebElement element : visualizationWikis) {
			wikiList.add(element.getAttribute("href"));
		}
		int video = 0;
		int entertainment = 0;
		int lifestyle = 0;
		for (String URL : wikiList) {
			getUrl(URL);
			String hubName = hubIndicator.getText().toLowerCase();
			//example: [ Video Games ] to Video_Games
			hubName = hubName.substring(2, hubName.length() - 2).replace(" ", "_");
			switch (hubName) {
				case "video_games":
					video += 1;
					break;
				case "entertainment":
					entertainment += 1;
					break;
				case "lifestyle":
					lifestyle += 1;
					break;
			}
			hubName = hubName.substring(2, hubName.length() - 2).replace(" ", "_"); //example: [ Video Games ] to Video_Games
				if (hubName.equals(HubName.Video_Games.toString().toLowerCase())) {
					video += 1;
				}
				if (hubName.equals(HubName.Entertainment.toString().toLowerCase())) {
					entertainment += 1;
				}
				if (hubName.equals(HubName.Lifestyle.toString().toLowerCase())) {
					lifestyle += 1;
				}
		}
		visualizationSetup.put(HubName.Video_Games.toString(), video);
		visualizationSetup.put(HubName.Entertainment.toString(), entertainment);
		visualizationSetup.put(HubName.Lifestyle.toString(), lifestyle);
		return visualizationSetup;
	}

	/**
	 * comparing desired slot setup with current visualization setup
	 * @param slotDesiredSetup
	 * @param slotCurrentSetup
	 */
	public void verifyVisualizationURLs(HashMap<String, Integer> slotDesiredSetup, HashMap<String, Integer> slotCurrentSetup) {
		Assertion.assertEquals(slotCurrentSetup.get(HubName.Video_Games.toString()), slotDesiredSetup.get(HubName.Video_Games.toString()));
		Assertion.assertEquals(slotCurrentSetup.get(HubName.Entertainment.toString()), slotDesiredSetup.get(HubName.Entertainment.toString()));
		Assertion.assertEquals(slotCurrentSetup.get(HubName.Lifestyle.toString()), slotDesiredSetup.get(HubName.Lifestyle.toString()));
	}

	public HomePageObject selectLanguage(int index) {
		scrollAndClick(languageButton);
		List<WebElement> languagesList = driver.findElements(languageSelectorBy);
		String languageClass = languagesList.get(index).getAttribute("class");
		languagesList.get(index).click();
		waitForValueToBePresentInElementsAttributeByCss(languageDropdownString, "class", languageClass);
		PageObjectLogging.log("selectLanguage", "language number " + Integer.toString(index) + " selected", true);
		return new HomePageObject(driver);
	}

	public void verifyLanguageButton() {
		waitForElementByBy(languageButtonSelectorBy);
	}

	public String getLanguageURL(int index) {
		List<WebElement> languagesList = driver.findElements(languageSelectorBy);
		return languagesList.get(index).getAttribute("href");
	}

	public int getNumOfLanguages() {
		List<WebElement> languagesList = driver.findElements(languageSelectorBy);
		return languagesList.size();
	}

	public void verifyLanguageDropdownURLs() {
		int numOfLanguages = getNumOfLanguages();
		HomePageObject newHome;
		for (int i=0; i<numOfLanguages; i++) {
			waitForValueToBePresentInElementsAttributeByCss(languageDropdownString, "class", "en");
			String languageURL = getLanguageURL(i) + URLsContent.wikiaDir;
			newHome = selectLanguage(i);
			newHome.verifyLanguageButton();
			newHome.verifyURL(languageURL);
			newHome.navigateBack();
		}
	}
}
