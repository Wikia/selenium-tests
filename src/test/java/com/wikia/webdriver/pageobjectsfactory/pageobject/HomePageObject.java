package com.wikia.webdriver.pageobjectsfactory.pageobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;

public class HomePageObject extends WikiBasePageObject {

	@FindBy(css = "header.wikiahomepage-header a.button")
	private WebElement startWikiButton;
	@FindBy(css = ".hub > a")
	private WebElement hubIndicator;
	@FindBy(css = ".preview-pane a.goVisit")
	private List<WebElement> visualizationWikis;
	@FindBy(css = "section.grid-1 nav")
	private WebElement languageButton;

	//These Bys are being used to prevent stale browser exception
	private By languageSelectorBy = By.cssSelector(".wikia-menu-button li > a");
	private By languageButtonSelectorBy = By.cssSelector("section.grid-1 nav");

	private String languageDropdownString = "nav.wikia-menu-button";

	public HomePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public CreateNewWikiPageObjectStep1 startAWiki(String wikiURL) {
		startWikiButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[name='label-wiki-form']")));
		return new CreateNewWikiPageObjectStep1(driver);
	}

	/**
	 * getting current slot setup on visualization component
	 *
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
			if (hubName.equals(HubName.VIDEO_GAMES.toString().toLowerCase())) {
				video += 1;
			}
			if (hubName.equals(HubName.ENTERTAINMENT.toString().toLowerCase())) {
				entertainment += 1;
			}
			if (hubName.equals(HubName.LIFESTYLE.toString().toLowerCase())) {
				lifestyle += 1;
			}
		}
		visualizationSetup.put(HubName.VIDEO_GAMES.toString(), video);
		visualizationSetup.put(HubName.ENTERTAINMENT.toString(), entertainment);
		visualizationSetup.put(HubName.LIFESTYLE.toString(), lifestyle);
		return visualizationSetup;
	}

	/**
	 * comparing desired slot setup with current visualization setup
	 *
	 * @param slotDesiredSetup
	 * @param slotCurrentSetup
	 */
	public void verifyVisualizationURLs(HashMap<String, Integer> slotDesiredSetup, HashMap<String, Integer> slotCurrentSetup) {
		Assertion.assertEquals(slotCurrentSetup.get(HubName.VIDEO_GAMES.toString()), slotDesiredSetup.get(HubName.VIDEO_GAMES.toString()));
		Assertion.assertEquals(slotCurrentSetup.get(HubName.ENTERTAINMENT.toString()), slotDesiredSetup.get(HubName.ENTERTAINMENT.toString()));
		Assertion.assertEquals(slotCurrentSetup.get(HubName.LIFESTYLE.toString()), slotDesiredSetup.get(HubName.LIFESTYLE.toString()));
	}

	public HomePageObject selectLanguage(int index) {
		scrollAndClick(languageButton);
		List<WebElement> languagesList = driver.findElements(languageSelectorBy);
		String languageClass = languagesList.get(index).getAttribute("class");
		languagesList.get(index).click();
		waitForStringInURL(languageClass);
		if (!checkIfPageIsHub()) {
			waitForValueToBePresentInElementsAttributeByCss(languageDropdownString, "class", languageClass);
		} else {
			PageObjectLogging.log("selectLanguage", "page is a Hub and language dropdown is not present", true);
		}
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

	public Boolean checkIfPageIsHub() {
		return !driver.findElement(By.tagName("body")).getAttribute("class").contains("WikiaHome");
	}

	public int getNumOfLanguages() {
		List<WebElement> languagesList = driver.findElements(languageSelectorBy);
		return languagesList.size();
	}

	public void verifyLanguageDropdownURLs() {
		int numOfLanguages = getNumOfLanguages();
		HomePageObject newHome;

		for (int i = 0; i < numOfLanguages; i++) {
			waitForValueToBePresentInElementsAttributeByCss(languageDropdownString, "class", "en");
			String languageURL = getLanguageURL(i) + URLsContent.WIKIA_DIR;
			newHome = selectLanguage(i);

			// Brasilian page is a corporate page, but actually it is hacked hub page and it doesn't have corporate footer
			// (and language dropDown)
			if (!checkIfPageIsHub()) {
				newHome.verifyLanguageButton();
			} else {
				PageObjectLogging.log(
					"selectLanguage",
					"page is a Hub and language dropdown is not present and main url is different",
					true
				);
			}
			newHome.verifyURL(languageURL);
			newHome.navigateBack();
		}
	}
}
