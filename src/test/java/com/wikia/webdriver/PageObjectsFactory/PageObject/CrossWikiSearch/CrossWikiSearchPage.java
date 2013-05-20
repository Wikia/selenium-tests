package com.wikia.webdriver.PageObjectsFactory.PageObject.CrossWikiSearch;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

	@FindBy(css=".paginator-next.button.secondary")
	protected WebElement paginatorNextButton;
	@FindBy(css=".paginator-prev.button.secondary")
	protected WebElement paginatorPrevButton;

	protected By results = By.cssSelector(".Results .result");
	protected By resultLinks = By.cssSelector(".Results .result > a");

	public CrossWikiSearchPage(WebDriver driver) {
		super(driver);
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
		List<WebElement> elements = driver.findElements(resultLinks);
		Assertion.assertEquals( elements.size(), expectedResultsPerPage, "Wrong number of results per page.");
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
}
