package com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class IntraWikiSearchPageObject extends BasePageObject {

	public IntraWikiSearchPageObject(WebDriver driver, String intraWikiURL) {
		super(driver);
		driver.get(intraWikiURL);
	}

	private String photoExtension = ".jpg";
	private String thumbnailsVideosGroup = ".Results a.image.video.lightbox";

	@FindBy(css=".photos-and-videos")
	private WebElement photosVideos;
	@FindBy(css="#WikiaSearchHeader input[name=search]")
	private WebElement searchField;
	@FindBy(css="#search-v2-input")
	private WebElement intraSearchField;
	@FindBy(css="#WikiaSearchHeader .wikia-button")
	private WebElement searchButton;
	@FindBy(css="#search-v2-button")
	private WebElement intraSearchButton;
	@FindBy(css="[value=is_image]")
	private WebElement filterPhotos;
	@FindBy(css="[value=is_video]")
	private WebElement filterVideos;
	@FindBy(css="[name=rank]")
	private WebElement sortingOptions;
	@FindBy(css="li.result:nth-child(1) a")
	private WebElement firstResult;
	@FindBy(css=".Results article h1 .result-link")
	private List<WebElement> titles;
	@FindBy(css=".Results article")
	private List<WebElement> descriptions;
	@FindBy(css=".Results article li > a")
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
	@FindBy(css=".result-count.subtle")
	private WebElement resultCountMessage;
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
	@FindBy(css=".sprite.play.small")
	private List<WebElement> playMovieImage;
	@FindBy(css=".result-description .result-link")
	private WebElement pushToTopWikiResult;
	@FindBy(css=".wikiPromoteThumbnail")
	private WebElement pushToTopWikithumbnail;

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
			Assertion.assertStringContains(suggestionsList.get(i).getText(), query);
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
		Assertion.assertStringContains(firstResult.getText(), query.replaceAll("_", " "));
		for (WebElement elem:descriptions) {
			Assertion.assertTrue(!elem.getText().isEmpty());
		}
		Assertion.assertEquals(titles.size(), urls.size());
	}

	public void verifyFirstResultExtension(String query) {
		Assertion.assertTrue(titles.get(0).getText().endsWith(query + photoExtension));
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
		waitForElementClickableByElement(paginationPages.get(paginationPages.size()-1));
		do {
			waitForElementByElement(paginationPages.get(paginationPages.size()-1));
			scrollAndClick(paginationPages.get(paginationPages.size()-1));
		}
		while(paginationPages.size() > 6);
		Assertion.assertEquals(paginationPages.size(), 6);
		Assertion.assertTrue(titles.size() < 25);
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

	public void clickNextPaginator() {
		scrollAndClick(paginatorNext);
		PageObjectLogging.log("clickNextPaginator", "next paginator clicked", true);
	}

	public void clickPrevPaginator() {
		scrollAndClick(paginatorPrev);
		PageObjectLogging.log("clickPrevPaginator", "prev paginator clicked", true);
	}

	public void verifyResultsCount(int i) {
		Assertion.assertNumber(i, titles.size(), "checking results count");
	}

	public void verifyNoResults() {
		Assertion.assertEquals("No results found.", noResultsCaption.getText());
	}

	public void clickAdvancedButton() {
		advancedButton.click();
		PageObjectLogging.log("clickAdvancedButton", "Advance button was clicked", true, driver);
	}

	public void chooseAdvancedOption(int i) {
		waitForElementByElement(advancedField);
		advancedOptionInputs.get(i).click();
		intraSearchButton.click();
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
		photosVideos.click();
		waitForElementByElement(sortingOptions);
		PageObjectLogging.log("selectPhotosVideos", "Photos and videos option is selected", true, driver);
	}

	public void verifyPhotosOnly() {
		waitForElementByElement(thumbnailsImages.get(0));
		waitForElementNotPresent(thumbnailsVideosGroup);
		for(int i = 0; i < titles.size(); i++) {
			Assertion.assertTrue(titles.get(i).getText().startsWith(URLsContent.fileNameSpace));
		}
	}

	public void verifyVideosOnly() {
		Assertion.assertTrue(thumbnailsVideos.size() == 25);
		Assertion.assertEquals(playMovieImage.size(), thumbnailsVideos.size());
		for(int i = 0; i < titles.size(); i++) {
			Assertion.assertTrue(titles.get(i).getText().startsWith(URLsContent.fileNameSpace));
		}
	}

	public void verifyNamespace(String namespace) {
		waitForElementByElement(titles.get(0));
		Assertion.assertTrue(titles.get(0).getText().startsWith(namespace));
	}

	public void verifySearchPageOpened() {
		Assertion.assertTrue(searchHeadline.isDisplayed());
		Assertion.assertTrue(searchTabs.isDisplayed());
		Assertion.assertTrue(intraSearchField.isDisplayed());
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
		filterVideos.click();
		PageObjectLogging.log("selectVideosOnly", "Videos option is selected", true, driver);
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

	public void verifyPushToTopWikiTitle(String searchWiki) {
		Assertion.assertStringContains(pushToTopWikiResult.getText(), searchWiki);
	}

	public void verifyPushToTopWikiThumbnail() {
		waitForElementByElement(pushToTopWikithumbnail);
	}

	public void goToSearchPage(String searchUrl) {
		try{
			getUrl(searchUrl+"index.php?title=Special:Search");
		}
		catch (TimeoutException e)
		{
			PageObjectLogging.log("goToSearchPage", "timeouted when opening search page", false);
		}
	}

	public void searchCommunityFor(String query) {
		intraSearchField.sendKeys(query);
		intraSearchButton.click();
		PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
	}
}
