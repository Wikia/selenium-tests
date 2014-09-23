package com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SearchPageObject;

public class IntraWikiSearchPageObject extends SearchPageObject {

	public IntraWikiSearchPageObject(WebDriver driver) {
		super(driver);
	}

	private String photoExtension = ".jpg";
	private String thumbnailsVideosGroup = ".Results a.image.video.lightbox";

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
	@FindBy(css=".Results article h1 .result-link")
	private List<WebElement> titles;
	@FindBy(css=".Results article img")
	private List<WebElement> images;
	@FindBy(css=".Results article a img")
	private List<WebElement> videoImages;
	@FindBy(css=".Results article")
	private List<WebElement> descriptions;
	@FindBy(css=".Results article li > a")
	private List<WebElement> urls;
	@FindBy(css=".SearchInput .grid-1.alpha")
	private WebElement searchHeadline;
	@FindBy(css=".search-tabs.grid-1.alpha")
	private WebElement searchTabs;
	@FindBy(css="#advanced-link")
	private WebElement advancedButton;
	@FindBy(css="#AdvancedSearch")
	private WebElement advancedField;
	@FindBy(css=".top-wiki-articles.RailModule")
	private WebElement topModule;
	@FindBy(css=".top-wiki-article-thumbnail")
	private List<WebElement> topModuleArticleThumbnail;
	@FindBy(css=".top-wiki-article-text")
	private List<WebElement> topModuleArticleText;
	@FindBy(css=".top-wiki-article.result")
	private List<WebElement> topModuleResults;
	@FindBy(css="#AdvancedSearch label")
	private List<WebElement> advancedOptions;
	@FindBy(css="#AdvancedSearch label input")
	private List<WebElement> advancedOptionInputs;
	@FindBy(css=".Results .image")
	private List<WebElement> thumbnailsImages;
	@FindBy(css=".Results a.image.video.lightbox")
	private List<WebElement> thumbnailsVideos;
	@FindBy(css=".autocomplete")
	private List<WebElement> suggestionsList;
	@FindBy(css=".search-tabs.grid-1.alpha")
	private List<WebElement> filterOptions;
	@FindBy(css=".play-circle")
	private List<WebElement> playMovieImages;
	@FindBy(css=".result-description .result-link")
	private WebElement pushToTopWikiResult;
	@FindBy(css=".wikiPromoteThumbnail")
	private WebElement pushToTopWikiThumbnail;
	@FindBy(css=".search-suggest-img-wrapper")
	private List<WebElement> suggestionImagesList;
	@FindBy(css="#WikiaSearchHeader .search-suggest li:not(.all)")
	private List<WebElement> newSuggestionsList;
	@FindBy(css=".block")
	private List<WebElement> suggestionTextsList;
	@FindBy(id="searchInput")
	private WebElement searchInputInGlobalNav;
	@FindBy(id="searchForm")
	private WebElement searchFormInGlobalNav;

	private By jqueryAutocompleteBy = By.cssSelector("[src*='jquery.autocomplete']");

	/*
	 * This method is checking whether text is translatable
	 * by adding "&uselang=qqx" to URl
	 */
	public void addQqxUselang() {
		appendToUrl(URLsContent.translatableLanguage);
	}

	public void searchFor(String query) {
		searchField.sendKeys(query);
		searchButton.click();
		PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
	}

	public void verifySuggestions(String query) {
		searchField.click();
		waitForElementByBy(jqueryAutocompleteBy);
		searchField.sendKeys(query);
		waitForElementByElement(suggestionsList.get(0));
		for(int i = 0; i < suggestionsList.size(); i++) {
			Assertion.assertStringContains(query, suggestionsList.get(i).getText());
		}
	}

	private void verifyLanguageTranslation(WebElement element) {
		Assertion.assertTrue(element.getText().startsWith("("), "some phrases are not translatable");
		Assertion.assertTrue(element.getText().endsWith(")"), "some phrases are not translatable");
	}

	public void verifyLanguageTranslation() {
		for(int i = 0; i < filterOptions.size(); i++) {
			verifyLanguageTranslation(filterOptions.get(i));
		}
		verifyLanguageTranslation(resultCountMessage);
		verifyLanguageTranslation(searchHeadline);
		verifyLanguageTranslation(advancedButton);
	}

	public void verifyFirstResult(String query) {
		Assertion.assertStringContains(query.replaceAll("_", " "), firstResult.getText());
		for (WebElement elem:descriptions) {
			Assertion.assertTrue(!elem.getText().isEmpty());
		}
		Assertion.assertEquals(titles.size(), urls.size());
	}

	public void verifyFirstResultExtension(String query) {
		Assertion.assertTrue(titles.get(0).getText().endsWith(query + photoExtension));
	}

	public void verifyLastResultPage() {
		waitForElementClickableByElement(paginationPages.get(paginationPages.size()-1));
		do {
			waitForElementByElement(paginationPages.get(paginationPages.size()-1));
			scrollAndClick(paginationPages.get(paginationPages.size()-1));
		}
		while(paginationPages.size() > 6);
		Assertion.assertEquals(paginationPages.size(), 6);
		Assertion.assertTrue(titles.size() <= 25);
	}

	public void verifyFirstArticleNameTheSame(String firstResult) {
		Assertion.assertEquals(firstResult, titles.get(0).getText());
	}

	public String getTitleInnerText() {
		return titles.get(0).getText();
	}

	public void verifyFirstArticleNameNotTheSame(String firstResult) {
		Assertion.assertNotEquals(firstResult, titles.get(0).getText());
	}

	public void verifyResultsCount(int i) {
		Assertion.assertNumber(i, titles.size(), "checking results count");
	}

	public void clickAdvancedButton() {
		advancedButton.click();
		PageObjectLogging.log("clickAdvancedButton", "Advance button was clicked", true, driver);
	}

	public void chooseAdvancedOption(int i) {
		waitForElementByElement(advancedField);
		advancedOptionInputs.get(i).click();
		PageObjectLogging.log("chooseAdvancedOption", "chosen advance option is selected", true, driver);
	}

	public void selectAllAdvancedOptions() {
		clickAdvancedButton();
		chooseAdvancedOption(0);
		PageObjectLogging.log("selectAllAdvancedOptions", "All advance options are selected", true, driver);
	}

	public void verifyDefaultNamespaces() {
		waitForElementVisibleByElement(advancedField);
		for(int i = 0; i < advancedOptions.size(); i++) {
			if(advancedOptions.get(i).getText().equals("Articles")) {
				Assertion.assertEquals(advancedOptionInputs.get(i).getAttribute("checked"), "true");
			}
			else if(advancedOptions.get(i).getText().equals("Category")) {
				Assertion.assertEquals(advancedOptionInputs.get(i).getAttribute("checked"), "true");
			}
			else {
				Assertion.assertNull(advancedOptionInputs.get(i).getAttribute("checked"));
			}
		}
	}

	public void selectPhotosVideos() {
		waitForElementVisibleByElement(photosVideos);
		photosVideos.click();
		waitForElementByElement(sortingOptions);
		PageObjectLogging.log("selectPhotosVideos", "Photos and videos option is selected", true, driver);
	}

	public void verifyPhotosOnly() {
		waitForElementByElement(thumbnailsImages.get(0));
		waitForElementNotPresent(thumbnailsVideosGroup);
		for(int i = 0; i < titles.size(); i++) {
			waitForElementByElement(titles.get(i));
			scrollToElement(titles.get(i));
			waitForElementByElement(images.get(i));
		}
	}

	public void verifyVideosOnly() {
		waitForElementByElement(thumbnailsVideos.get(0));
		Assertion.assertTrue(thumbnailsVideos.size() == 25);
		//make sure there are as many videos as play buttons
		waitForElementByElement(playMovieImages.get(0));
		Assertion.assertEquals(playMovieImages.size(), thumbnailsVideos.size());
		for(int i = 0; i < titles.size(); i++) {
			waitForElementByElement(titles.get(i));
			scrollToElement(titles.get(i));
			waitForElementByElement(videoImages.get(i));
		}
	}

	public void verifyNamespace(String namespace) {
		waitForElementByElement(titles.get(0));
		Assertion.assertTrue(titles.get(0).getText().startsWith(namespace));
	}

	public void verifySearchPageOpened() {
		Assertion.assertTrue(searchHeadline.isDisplayed());
		Assertion.assertTrue(searchTabs.isDisplayed());
		Assertion.assertTrue(searchInput.isDisplayed());
	}

	public void verifyTopModule() {
		waitForElementByElement(topModule);
		Assertion.assertNumber(7, topModuleResults.size(), "Top module has correct amount of results");
		for(int i = 0; i < topModuleResults.size(); i++) {
			Assertion.assertTrue(topModuleArticleThumbnail.get(i).isDisplayed());
			Assertion.assertTrue(topModuleArticleText.get(i).isDisplayed());
		}
	}

	public void selectPhotosOnly() {
		filterPhotos.click();
		PageObjectLogging.log("selectPhotosOnly", "Photos option is selected", true, driver);
	}

	public void verifyAllResultsImages(int numberOfResults) {
		Assertion.assertEquals(numberOfResults, thumbnailsImages.size());
	}

	public void verifyAllResultsVideos(int numberOfResults) {
		Assertion.assertEquals(numberOfResults, thumbnailsVideos.size());
	}

	public void selectVideosOnly() {
		scrollAndClick(filterVideos);
		PageObjectLogging.log("selectVideosOnly", "Videos option is selected", true, driver);
	}

	public void verifyTitlesNotEmpty() {
		for (WebElement elem:titles) {
			Assertion.assertNotNull(elem.getText());
		}
	}

	public enum sortOptions {
		relevancy, publishDate, duration;
	}

	public void sortBy(sortOptions option) {
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
		Assertion.assertNotEquals(titles1, titles2, "titles are the same");
	}

	public void verifyPushToTopWikiTitle(String searchWiki) {
		Assertion.assertStringContains(searchWiki, pushToTopWikiResult.getText());
	}

	public void verifyPushToTopWikiThumbnail() {
		waitForElementByElement(pushToTopWikiThumbnail);
		PageObjectLogging.log("verifyPushToTopWikiThumbnail", "Push to top wiki thumbnail verified", true, driver);
	}

	public void verifyNewSuggestionsTextAndImages(String query) {
		searchField.click();
		waitForElementByBy(jqueryAutocompleteBy);
		searchField.sendKeys(query);
		waitForElementByElement(newSuggestionsList.get(0));
		System.out.println(newSuggestionsList.size() );
		for(int i = 0; i < newSuggestionsList.size(); i++) {
			Assertion.assertStringContains(query, suggestionTextsList.get(i).getText());
			Assertion.assertTrue(suggestionImagesList.get(i).isDisplayed());
		}
		PageObjectLogging.log("verifyNewSuggestionsTextAndImages", "Image and text next to every suggestion is verified", true);
	}

	public void searchForInGlobalNav(String query) {
		searchInputInGlobalNav.sendKeys(query);
		searchFormInGlobalNav.submit();
	}
}
