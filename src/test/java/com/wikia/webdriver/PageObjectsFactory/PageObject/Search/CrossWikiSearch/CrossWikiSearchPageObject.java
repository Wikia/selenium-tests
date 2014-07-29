package com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

/**
 * Author: Artur Dwornik
 * Date: 28.03.13
 * Time: 19:29
 */
public class CrossWikiSearchPageObject extends SearchPageObject {

	@FindBy(css=".result")
	private List<WebElement> searchResultList;
	@FindBy(css=".Results > :nth-child(1)")
	private WebElement firstResult;
	@FindBy(css=".Results > :nth-child(1) > .result-description > :nth-child(2)")
	private WebElement firstResultVertical;
	@FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle")
	private WebElement firstResultStatistics;
	@FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(1)")
	private WebElement firstResultStatisticsPageCount;
	@FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(2)")
	private WebElement firstResultStatisticsPageImages;
	@FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(3)")
	private WebElement firstResultStatisticsPageVideos;
	@FindBy(css=".Results > :nth-child(1) .result-description > .description")
	private WebElement firstResultDescription;
	@FindBy(css = "a[data-event=\"search_click_match\"]")
	private WebElement match;
	@FindBy(css=".results-wrapper i")
	private WebElement noResultsCaption;
	@FindBy(css=".wikiPromoteThumbnail")
	private List<WebElement> thumbnails;
	@FindBy(css=".description")
	private List<WebElement> descriptions;
	@FindBy(css=".wiki-statistics>li:nth-child(1)")
	private List<WebElement> statisticsPages;
	@FindBy(css=".wiki-statistics>li:nth-child(2)")
	private List<WebElement> statisticsImages;
	@FindBy(css=".wiki-statistics>li:nth-child(3)")
	private List<WebElement> statisticsVideos;

	public CrossWikiSearchPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyQuery(String query) {
		boolean isPresent = false;
		for (WebElement element:resultLinks) {
			if (element.getText().contains(query)){
				isPresent = true;
				break;
			}
		}
		Assertion.assertTrue(isPresent, "there is no result link in the page");
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

	public CrossWikiSearchPageObject searchFor( String term ) {
		searchInput.clear();
		searchInput.sendKeys( term );
		PageObjectLogging.log("searchFor", "Typed search term" +term, true, driver);
		scrollAndClick(searchButton);
		waitForElementByElement(searchInput);
		PageObjectLogging.log("searchFor", "Search button clicked", true, driver);
		return new CrossWikiSearchPageObject(driver);
	}

	public CrossWikiSearchPageObject searchForEnter( String term ) {
		searchInput.clear();
		searchInput.sendKeys( term );
		PageObjectLogging.log("searchForEnter", "Typed search term" +term, true, driver);
		searchInput.sendKeys(Keys.ENTER);
		waitForElementByElement(searchInput);
		PageObjectLogging.log("searchForEnter", "Search button entered", true, driver);
		return new CrossWikiSearchPageObject(driver);
	}

	public void verifyFirstResultTitle(String wikiName) {
		waitForTextToBePresentInElementByElement(firstResultLink, wikiName);
	}

	public void verifyFirstResultVertical(String vertical) {
		waitForTextToBePresentInElementByElement(firstResultVertical, vertical);
		Assertion.assertFalse(firstResultVertical.getText().isEmpty(), "Vertical (Hub) string is empty.");
	}

	public void verifyFirstResultDescription() {
		waitForElementByElement(firstResultVertical);
		Assertion.assertFalse(firstResult.getText().isEmpty(), "There is no article description.");
	}

	public void verifyFirstResultPageCount() {
		waitForElementByElement(firstResultStatisticsPageCount);
		Assertion.assertFalse(firstResultStatisticsPageCount.getText().isEmpty(), "Page count string is empty.");
	}

	public void verifyFirstResultPageImages() {
		waitForElementByElement(firstResultStatisticsPageImages);
		Assertion.assertFalse(firstResultStatisticsPageImages.getText().isEmpty(), "Images count is empty.");
	}

	public void verifyFirstResultPageVideos() {
		waitForElementByElement(firstResultStatisticsPageVideos);
		Assertion.assertFalse(firstResultStatisticsPageVideos.getText().isEmpty(), "Results count is empty.");
	}

	 /**
	 * Verify that result count is same as expected
	 * @param expectedResultsPerPage number of results that should appear on result page
	 */
	public void verifyResultsCount( int expectedResultsPerPage ) {
		waitForElementByElement(resultsContainer);
		Assertion.assertEquals(searchResultList.size(), expectedResultsPerPage, "Wrong number of results per page.");
	}

	/**
	 * Verify data-pos attributes are present
	 * @param pageNumber search result page number
	 * @param resultsPerPage expected pages per result
	 */
	public void verifyResultsPosForPage(int pageNumber, int resultsPerPage) {
		waitForElementByElement(resultsContainer);
		int curNo = pageNumber * resultsPerPage + 1;
		for(WebElement link: resultLinks) {
			String dataPos = link.getAttribute("data-pos");
			int pos = Integer.parseInt(dataPos);
			Assertion.assertEquals( pos, curNo, "Wrong data-pos. Verify paging.");
			curNo++;
		}
	}

	/**
	 * Clicks on nth result
	 * @param resultNumber zero based number of result to click
	 * @return result page
	 */
	public WikiArticleHomePage openResult(int resultNumeber) {
		WebElement resultLink = resultLinks.get(resultNumeber);
		waitForElementByElement(resultLink);
		resultLink.click();
		return new WikiArticleHomePage(driver);
	}

	public CrossWikiSearchPageObject prevPage() {
		scrollAndClick(paginatorPrev);
		PageObjectLogging.log("prevPage", "Moving to prev page of search results.",
				true, driver);
		return new CrossWikiSearchPageObject(driver);
	}

	public CrossWikiSearchPageObject nextPage() {
		scrollAndClick(paginatorNext);
		PageObjectLogging.log("nextPage", "Moving to next page of search results.",
				true, driver);
		return new CrossWikiSearchPageObject(driver);
	}

	public void verifyResultsNumber(int number){
		waitForElementByElement(searchResultList.get(0));
		Assertion.assertNumber(number, searchResultList.size(), "checking number of search results");
	}

	public void verifyNoPagination(){
		waitForElementNotPresent(paginationContainerBy);
		PageObjectLogging.log("verifyNoPagination", "pagination is not visible on the page",
				true);
	}

	public void verifyNoResultsCaption(){
		waitForElementByElement(noResultsCaption);
		Assertion.assertEquals("No results found.", noResultsCaption.getText());
		PageObjectLogging.log("verifyNoResultsCaption", "verified no results caption",
				true);
	}

	public void verifyThumbnails(int number){
		Assertion.assertNumber(number, thumbnails.size(), "checking number of thumbnails");
		for (WebElement elem:thumbnails){
			Assertion.assertStringContains(elem.getAttribute("src"), ".png");
		}
		PageObjectLogging.log("verifyThumbnails", "thumbnails verified",
				true);
	}

	public void verifyDescription(int number){
		Assertion.assertNumber(number, descriptions.size(), "checking number of thumbnails");
		for (WebElement elem:descriptions){
			Assertion.assertTrue(!elem.getText().isEmpty(), "checking if description is not empty");
		}
		PageObjectLogging.log("verifyDescriptions", "descriptions verified",
				true);
	}

	public void verifyStatistics(int number){
		Assertion.assertEquals(statisticsPages.size(), number);
		Assertion.assertEquals(statisticsImages.size(), number);
		Assertion.assertEquals(statisticsVideos.size(), number);
		for (int i=0; i<number; i++){
			Assertion.assertStringContains(statisticsPages.get(i).getText(), "PAGE");
			Assertion.assertStringContains(statisticsImages.get(i).getText(), "IMAGE");
			Assertion.assertStringContains(statisticsVideos.get(i).getText(), "VIDEO");
		}
	}

	public String getFirstDescription() {
		return firstResultDescription.getText();
	}

	/*
	 * Method fetches specific string related to an image by storing index start position and
	 * finish position, and then selects characters in between those indexes by using substring method.
	 */
	public String getFirstImageText() {
		int indexComparisonStart = thumbnails.get(0).getAttribute("src").indexOf("px-");
		int indexComparisonFinish = thumbnails.get(0).getAttribute("src").indexOf("-Wikia-Visualization-Main");
		return thumbnails.get(0).getAttribute("src").substring(indexComparisonStart + 3, indexComparisonFinish - 1);
	}
}
