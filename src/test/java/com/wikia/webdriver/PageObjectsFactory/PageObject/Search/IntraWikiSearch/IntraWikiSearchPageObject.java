package com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class IntraWikiSearchPageObject extends BasePageObject {

	public IntraWikiSearchPageObject(WebDriver driver, String intraWikiURL) {
		super(driver);
		driver.get(intraWikiURL);
	}

	@FindBy(css=".photos-and-videos")
	private WebElement photosVideos;
	@FindBy(css="#WikiaSearchHeader input[name=search]")
	private WebElement searchField;
	@FindBy(css="#WikiaSearchHeader .wikia-button")
	private WebElement searchButton;
	@FindBy(css="[value=is_image]")
	private WebElement filterPhotos;
	@FindBy(css="[value=is_video]")
	private WebElement filterVideos;
	@FindBy(css="[name=rank]")
	private WebElement sortingOptions;
	@FindBy(css="li.result:nth-child(1) a")
	private WebElement firstResult;
	@FindBy(css=".Results article h1")
	private List<WebElement> titles;
	@FindBy(css=".Results article")
	private List<WebElement> descriptions;
	@FindBy(css=".Results article li>a")
	private List<WebElement> urls;
	@FindBy(css=".wikia-paginator")
	private WebElement paginationContainer;
	@FindBy(css=".paginator-page")
	private List<WebElement> paginationPages;
	@FindBy(css=".paginator-next")
	private WebElement paginatorNext;
	@FindBy(css=".paginator-prev")
	private WebElement paginatorPrev;
	@FindBy(css=".results-wrapper i")
	private WebElement noResultsCaption;
	@FindBy(css=".Results .image")
	private List<WebElement> thumbnailsImages;
	@FindBy(css=".Results a.image.video.lightbox")
	private List<WebElement> thumbnailsVideos;
	@FindBy(css=".autocomplete")
	private List<WebElement> suggestionsList;

	public void typeSearchQuery(String query) {
		waitForElementByElement(searchField);
		searchField.sendKeys(query);
	}

	private void clickSearchbutton() {
		waitForElementByElement(searchButton);
		searchButton.click();
	}

	public void searchFor(String query) {
		typeSearchQuery(query);
		clickSearchbutton();
		PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
	}

	public void verifySuggestions(String query) {
		waitForElementVisibleByElement(suggestionsList.get(0));
		for(int i = 0; i < suggestionsList.size(); i++) {
			Assertion.assertStringContains(suggestionsList.get(i).getText(), query);
		}
	}

	private void verifyFirstResultName(String query) {
		waitForElementByElement(firstResult);
		Assertion.assertStringContains(firstResult.getText(), query.replaceAll("_", " "));
	}

	private void verifyDescriptions() {
		for (WebElement elem:descriptions) {
			Assertion.assertTrue(!elem.getText().isEmpty());
		}
	}

	private void verifyUrl() {
			Assertion.assertEquals(titles.size(), urls.size());
	}

	public void verifyFirstResult(String query) {
		verifyFirstResultName(query);
		verifyDescriptions();
		verifyUrl();
	}

	public void verifyPagination() {
		waitForElementByElement(paginationContainer);
		int i=1;
		for (WebElement elem:paginationPages) {
			Assertion.assertEquals(Integer.toString(i), elem.getText());
			i++;
		}
	}

	public void verifyLastResultPage() {
		waitForElementByElement(paginationPages.get(paginationPages.size()-1));
		paginationPages.get(paginationPages.size()-1).click();
		do {
			waitForElementByElement(paginationPages.get(paginationPages.size()-1));
			paginationPages.get(paginationPages.size()-1).click();
		}
		while(paginationPages.size() > 6);
		Assertion.assertEquals(paginationPages.size(), 6);
		Assertion.assertTrue(titles.size() < 25);
	}

	public void verifyArticlesTheSame(String firstResult) {
		waitForElementByElement(paginationContainer);
		Assertion.assertEquals(firstResult, titles.get(0).getText());
	}

	public String getInnerText() {
		return titles.get(0).getText();
	}

	public void verifyArticlesNotTheSame(String firstResult) {
		waitForElementByElement(paginationContainer);
		Assertion.assertNotEquals(firstResult, titles.get(0).getText());
	}

	public void clickNextPaginator() {
		waitForElementByElement(paginatorNext);
		scrollAndClick(paginatorNext);
		PageObjectLogging.log("clickNextPaginator", "next paginator clicked", true);
	}

	public void clickPrevPaginator() {
		waitForElementByElement(paginatorPrev);
		scrollAndClick(paginatorPrev);
		PageObjectLogging.log("clickPrevPaginator", "prev paginator clicked", true);
	}

	public void verifyResultsCount(int i) {
		Assertion.assertNumber(i, titles.size(), "checking results count");
	}

	public void verifyNoResults() {
		waitForElementByElement(noResultsCaption);
		Assertion.assertEquals("No results found.", noResultsCaption.getText());
	}

	public void selectPhotosVideos() {
		waitForElementByElement(photosVideos);
		photosVideos.click();
		waitForElementByElement(sortingOptions);
	}

	public void selectPhotosOnly() {
		waitForElementByElement(sortingOptions);
		filterPhotos.click();
	}

	public void verifyAllResultsImages(int numberOfResults) {
		Assertion.assertEquals(numberOfResults, thumbnailsImages.size());
	}

	public void verifyAllResultsVideos(int numberOfResults) {
		Assertion.assertEquals(numberOfResults, thumbnailsVideos.size());
	}

	public void selectVideosOnly() {
		waitForElementByElement(sortingOptions);
		filterVideos.click();
	}

	public void verifyNamespacesInTitles(String nameSpace) {
		for (WebElement elem:titles) {
			Assertion.assertStringContains(elem.getText(), nameSpace);
		}
	}

	public enum sortOptions {
		relevancy, publishDate, duration;
	}

	public void sortBy(sortOptions option) {
		waitForElementByElement(sortingOptions);
		Select dropDown = new Select(sortingOptions);
		switch (option) {
		case relevancy:
			dropDown.selectByIndex(0);
			break;
		case publishDate:
			dropDown.selectByIndex(1);
			break;
		case duration:
			dropDown.selectByIndex(2);
			break;
		}
	}

	public List<String> getTitles() {
		ArrayList<String> titleList = new ArrayList<String>();
		for (WebElement elem:titles) {
			titleList.add(elem.getText());
		}
		return titleList;
	}

	public void compareTitleListsNotEquals(List<String> titles1, List<String> titles2) {
		Assertion.assertNumber(titles1.size(), titles2.size(), "checking list length");
		for (int i=0; i<titles1.size(); i++){
			Assertion.assertTrue(!titles1.get(i).equals(titles2.get(i)));
		}
	}
}
