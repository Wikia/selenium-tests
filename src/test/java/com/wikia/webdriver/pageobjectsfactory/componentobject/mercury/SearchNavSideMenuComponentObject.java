package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
* @author: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
* */
public class SearchNavSideMenuComponentObject extends MercuryBasePageObject{

	public SearchNavSideMenuComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".ember-text-field")
	private WebElement searchInput;
	@FindBy(css = ".local-nav-menu > li")
	private WebElement firstLevelMenuPositions;
	@FindBy(css = ".local-nav-menu > title")
	private WebElement menuTitle;
	@FindBy(css = "li[class='needsclick']")
	private WebElement needsClickMenuPositions;
	@FindBy(css = ".cancel")
	private WebElement cancelSearchCaption;
	@FindBy(css = ".local-wikia-search > li > a")
	private WebElement searchResults;
	@FindBy(css = "li[class='back']")
	private WebElement menuBackButton;
	@FindBy(css = ".icon chevron")
	private WebElement needsClickChevrons;
	@FindBy(css = ".local-wikia-search a")
	private List<WebElement> searchSuggestions;
	@FindBy(css = ".local-wikia-search span")
	private WebElement searchAlerts;
	@FindBy(css = ".search")
	private WebElement searchView;
	@FindBy(css = "div[class='drawer']")
	private WebElement menuView;
	@FindBy(css = ".local-nav-menu > li > div")
	private List<WebElement> chevrons;
	@FindBy(css = ".local-nav-menu > li > a")
	private List<WebElement> noChevrons;
	@FindBy(css= ".back")
	private WebElement backChevron;
	@FindBy(css = ".overlay")
	private WebElement overlay;

	public void clickSearchField() {
		waitForElementByElement(searchInput);
		searchInput.click();
		PageObjectLogging.log("clickSearchField", "Search field was clicked", true);
	}

	public MercuryBasePageObject clickSearchSuggestion(int index) {
		waitForElementByElement(searchSuggestions.get(index));
		searchSuggestions.get(index).click();
		return new MercuryBasePageObject(driver);
	}

	public void clickCancelButton() {
		waitForElementVisibleByElement(cancelSearchCaption);
		cancelSearchCaption.click();
	}

	public String getSearchResultHref(int searchPosition) {
		waitForElementVisibleByElement(searchSuggestions.get(searchPosition));
		return searchSuggestions.get(searchPosition).getAttribute("href");
	}

	public void typeInSearchField(String content, int length) {
		waitForElementVisibleByElement(searchInput);
		searchInput.sendKeys(content.substring(0, length));
		PageObjectLogging.log(
				"typeInSearchField",
				"String >>" + content.substring(0, length) + "<< was typed in string field",
				true
		);
	}

	public void verifySearchSuggestionsWereVisible() {
		waitForElementByElement(searchSuggestions.get(0));
		Assertion.assertTrue(checkIfElementOnPage(searchSuggestions.get(0)));
		PageObjectLogging.log("verifySearchSuggestionsWereVisible", "Search suggestions were visible", true, driver);
	}

	public void verifySearchView() {
		Assertion.assertTrue(checkIfElementOnPage(searchView));
		PageObjectLogging.log("verifySearchView", "Search view was displayed on full size", true, driver);
	}

	public void verifyMenuView() {
		Assertion.assertTrue(checkIfElementOnPage(menuView));
		PageObjectLogging.log("verifyMenuView", "Menu view was visible", true, driver);
	}

	public void verifySearchNotMatch() {
		waitForElementByElement(searchAlerts);
		//PageObjectLogging.log("verifySearchNotMatch", searchAlerts.getText(), true);
		Assertion.assertTrue(searchAlerts.getText().contains(MercuryContent.MERCURY_SEARCH_NOT_MATCH));
	}

	public void verifyOpeningArticleInNav(int anchorIndex) {
		waitForElementVisibleByElement(noChevrons.get(anchorIndex));
		if (noChevrons.size() != 0) {
			noChevrons.get(anchorIndex).click();
		}
	}

	public void verifyOpeningNextLevelInNav(int anchorIndex) {
		waitForElementVisibleByElement(chevrons.get(anchorIndex));
		if (chevrons.size() != 0) {
			chevrons.get(anchorIndex).click();
			waitForElementVisibleByElement(backChevron);
			PageObjectLogging.log("verifyOpeningNextLevelInNav", "Back button is visible", true, driver);
		}
	}

	public void verifyBackLinkFunctionality(int anchorIndex) {
		waitForElementVisibleByElement(chevrons.get(anchorIndex));
		if (chevrons.size() != 0) {
			chevrons.get(anchorIndex).click();
			waitForElementVisibleByElement(backChevron);
			backChevron.click();
			PageObjectLogging.log("verifyOpeningNextLevelInNav", "Back button is working", true, driver);
		}
	}

	public void verifyClosingNav() {
		waitForElementVisibleByElement(overlay);
//		tapOnElement(overlay);
		PageObjectLogging.log("verifyClosingNav", "Nav menu is closed", true, driver);
	}

	public void verifyTextEllipsis(int anchorIndex) {
		waitForElementVisibleByElement(noChevrons.get(anchorIndex));
		waitForElementVisibleByElement(chevrons.get(anchorIndex));
		//x = noChevrons.get(anchorIndex).getCssValue("text-overflow");		
		if (noChevrons.get(anchorIndex).getCssValue("text-overflow").contains("ellipsis") && chevrons.get(anchorIndex).getCssValue("text-overflow").contains("ellipsis")) {
			PageObjectLogging.log("verifyTextEllipsis", "CSS selector is set to ellipsis", true);
		} else {
			PageObjectLogging.log("verifyTextEllipsis", "CSS sellector isn't set to ellipsis", false);
		}
	}
}
