package com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

/**
 * Author: Artur Dwornik
 * Date: 28.03.13
 * Time: 19:29
 */
public class CrossWikiSearchPageObject extends BasePageObject {

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
	@FindBy(css=".Results > :nth-child(1) .result-description > .description")
	protected WebElement firstResultDescription;
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
	@FindBy(css=".results-wrapper i")
	protected WebElement noResultsCaption;
	@FindBy(css=".wikiPromoteThumbnail")
	protected List<WebElement> thumbnails;
	@FindBy(css=".description")
	protected List<WebElement> descriptions;
	@FindBy(css=".wiki-statistics>li:nth-child(1)")
	protected List<WebElement> statisticsPages;
	@FindBy(css=".wiki-statistics>li:nth-child(2)")
	protected List<WebElement> statisticsImages;
	@FindBy(css=".wiki-statistics>li:nth-child(3)")
	protected List<WebElement> statisticsVideos;
	@FindBy(css="h1 > a.result-link")
	protected List<WebElement> resultLinks;
	@FindBy(css=".Results > :nth-child(4)")
	protected WebElement fourthResult;
	@FindBy(css=".Results > :nth-child(4) h1 > a")
	protected WebElement fourthResultLink;
	@FindBy(css="#curMainImageName")
	protected WebElement specialPromoteThumbnail;
	@FindBy(css=".description-wrapper")
	protected WebElement specialPromoteDescription;
	
	private By paginationContainer = By.cssSelector(".wikia-paginator");


	public CrossWikiSearchPageObject(WebDriver driver) {
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

	public CrossWikiSearchPageObject searchFor( String term ) {
		searchBox.clear();
		searchBox.sendKeys( term );
		PageObjectLogging.log("searchFor", "Typed search term" +term, true, driver);
		scrollAndClick(searchButton);
		waitForElementByElement(searchBox);
		PageObjectLogging.log("searchFor", "Search button clicked", true, driver);
		return new CrossWikiSearchPageObject(driver);
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
		resultLinks.get(resultNumeber).click();
		return new WikiArticleHomePage(driver);
	}

	public CrossWikiSearchPageObject prevPage() {
		scrollAndClick(paginatorPrevButton);
		PageObjectLogging.log("prevPage", "Moving to prev page of search results.",
				true, driver);
		return new CrossWikiSearchPageObject(driver);
	}

	public CrossWikiSearchPageObject nextPage() {
		scrollAndClick(paginatorNextButton);
		PageObjectLogging.log("nextPage", "Moving to next page of search results.",
				true, driver);
		return new CrossWikiSearchPageObject(driver);
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
		waitForElementByElement(firstResult);
		return firstResultDescription.getText();
	}
	
	public String getFirstImageText() {
		waitForElementByElement(thumbnails.get(0));
		int firstNumber = thumbnails.get(0).getAttribute("src").indexOf("px-");
		int secondNumber = thumbnails.get(0).getAttribute("src").indexOf("-Wikia-Visualization-Main");
		return thumbnails.get(0).getAttribute("src").substring(firstNumber + 3, secondNumber - 1);
	}
	
	public void verifyFirstGTAResult(){
		waitForElementByElement(firstResult);
		String gtaWikiTitle = firstResultLink.getText();
		Assertion.assertStringContains(gtaWikiTitle, "GTA V");
		PageObjectLogging.log("verifyFirstGTAResult", "first result ",
				true);
	}
	
	public void verifyFourthGTAResult(){
		waitForElementByElement(fourthResult);
		String gtaFourthResultLink = fourthResult.getText();
		Assertion.assertStringContains(gtaFourthResultLink, "GTA V");
		PageObjectLogging.log("getFourthGTAResult", "GTA wiki title saved",
				true);
	}
	
	public void openSpecialPromote() {
		String firstLink = firstResultLink.getAttribute("href");
		getUrl(firstLink + URLsContent.specialPromote);
	}
	
	public void verifyCrossWikiSearchDescription(String firstDescription) {
		waitForElementByElement(specialPromoteDescription);
		int lastChar = firstDescription.length();
		Assertion.assertStringContains(specialPromoteDescription.getText(), firstDescription.substring(0, lastChar-3));
	}
	
	public void verifyCrossWikiSearchImage(String firstImage) {
		waitForElementByElement(specialPromoteThumbnail);
		int firstNumber = specialPromoteThumbnail.getAttribute("src").indexOf("px-");
		int secondNumber = specialPromoteThumbnail.getAttribute("src").indexOf("-Wikia-Visualization-Main");
		Assertion.assertEquals(firstImage, specialPromoteThumbnail.getAttribute("src").substring(firstNumber + 3, secondNumber - 1));
	}
}
