package com.wikia.webdriver.PageObjectsFactory.PageObject.CrossWikiSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

/**
 * Author: Artur Dwornik
 * Date: 28.03.13
 * Time: 19:29
 */
public class CrossWikiSearchPage extends BasePageObject {

	@FindBy(css="#search-v2-input")
	protected WebElement searchInput;
	@FindBy(css="#search-v2-button")
	protected WebElement searchButton;
	@FindBy(css=".result")
	protected List<WebElement> searchResultList;
	@FindBy(css=".Results")
	protected WebElement resultsContainer;
	@FindBy(css=".Results > :nth-child(1)")
	protected WebElement firstResult;
	@FindBy(css=".Results > :nth-child(1) h1 > a")
	protected WebElement firstResultLink;
	@FindBy(css=".Results > :nth-child(1) > .result-description > :nth-child(2)")
	protected WebElement firstResultVertical;
	@FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle")
	protected WebElement firstResultStatistics;
	@FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(1)")
	protected WebElement firstResultStatisticsPageCount;
	@FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(2)")
	protected WebElement firstResultStatisticsPageImages;
	@FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(3)")
	protected WebElement firstResultStatisticsPageVideos;
	@FindBy(css = "#search-v2-input")
	private WebElement searchBox;
	@FindBys(@FindBy(css = "li.result"))
	private List<WebElement> results;
	@FindBy(css = "a[data-event=\"search_click_match\"]")
	private WebElement match;
	@FindBy(css=".paginator-next.button.secondary")
	protected WebElement paginatorNextButton;
	@FindBy(css=".paginator-prev.button.secondary")
	protected WebElement paginatorPrevButton;

	protected By resultLinks = By.cssSelector(".Results .result > a");
	private By paginationContainer = By.cssSelector(".wikia-paginator");


	public CrossWikiSearchPage(WebDriver driver) {
		super(driver);
	}



	public void goToSearchPage(String searchUrl) {
		try{
			getUrl(searchUrl+"wiki/Special:Search");
		}
		catch (TimeoutException e)
		{
			PageObjectLogging.log("goToSearchPage", "timeouted when opening search page", false);
		}
	}

	public CrossWikiSearchPage searchFor( String term ) {
		searchBox.clear();
		searchBox.sendKeys( term );
		PageObjectLogging.log("searchFor", "Typed search term" +term, true, driver);
		clickAndWait(searchButton);
		waitForElementByElement(searchBox);
		PageObjectLogging.log("searchFor", "Search button clicked", true, driver);
		return new CrossWikiSearchPage(driver);
	}

	public void verifyMatchResultUrl( String url ) {
		String href = match.getAttribute("href");
		if ( href.contains(url) ) {
			PageObjectLogging.log("verifyMatchResultUrl", "match result page matches url "+url+ ": "+href, true, driver);
		} else {
			PageObjectLogging.log("verifyMatchResultUrl", "match result page does not match url "+url+ ": "+href, false, driver);
		}
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
		List<WebElement> elements = driver.findElements(resultLinks);
		int curNo = pageNumber * resultsPerPage + 1;
		for(WebElement link: elements) {
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
	public WikiArticleHomePage openResult(int resultNumber) {
		WebElement webElement = getResultWikiNameLink(resultNumber);
		clickAndWait(webElement);
		return new WikiArticleHomePage(driver);
	}

	public CrossWikiSearchPage prevPage() {
		clickAndWait(paginatorPrevButton);
		PageObjectLogging.log("prevPage", "Moving to prev page of search results.",
				true, driver);
		return new CrossWikiSearchPage(driver);
	}

	public CrossWikiSearchPage nextPage() {
		clickAndWait(paginatorNextButton);
		PageObjectLogging.log("nextPage", "Moving to next page of search results.",
				true, driver);
		return new CrossWikiSearchPage(driver);
	}

	protected WebElement getResultWikiNameLink(int no) {
		return driver.findElements(resultLinks).get(no);
	}

	public void verifyResultsNumber(int number){
		waitForElementByElement(searchResultList.get(0));
		Assertion.assertNumber(number, searchResultList.size(), "checking number of search results");
	}

	public void verifyNoPagination(){
		waitForElementNotPresent(paginationContainer);
		PageObjectLogging.log("verifyNoPagination", "pagination is not visible on the page",
				true);
	}
}
