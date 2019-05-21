package com.wikia.webdriver.pageobjectsfactory.pageobject.search.crosswikisearch;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiArticleHomePage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Author: Artur Dwornik Date: 28.03.13 Time: 19:29
 */
public class CrossWikiSearchPageObject extends SearchPageObject {

  @FindBy(css = ".result")
  private List<WebElement> searchResultList;
  @FindBy(css = ".Results > :nth-child(1), .top-community-content")
  private WebElement firstResult;
  @FindBy(css = ".Results > :nth-child(1) > .result-description > :nth-child(2), .top-community-topic")
  private WebElement firstResultVertical;
  @FindBy(css = ".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(1), .top-community-stats > :nth-child(1)")
  private WebElement firstResultStatisticsPageCount;
  @FindBy(css = ".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(2), .top-community-stats > :nth-child(2)")
  private WebElement firstResultStatisticsPageImages;
  @FindBy(css = ".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(3), .top-community-stats > :nth-child(3)")
  private WebElement firstResultStatisticsPageVideos;
  @FindBy(css = ".Results > :nth-child(1) .result-description > .description, .top-community-text")
  private WebElement firstResultDescription;
  @FindBy(css = ".results-wrapper i, .no-results")
  private WebElement noResultsCaption;
  @FindBy(css = ".wikiPromoteThumbnail")
  private List<WebElement> thumbnails;
  @FindBy(css = ".description")
  private List<WebElement> descriptions;
  @FindBy(css = ".wiki-statistics>li:nth-child(1)")
  private List<WebElement> statisticsPages;
  @FindBy(css = ".wiki-statistics>li:nth-child(2)")
  private List<WebElement> statisticsImages;
  @FindBy(css = ".wiki-statistics>li:nth-child(3)")
  private List<WebElement> statisticsVideos;
  @FindBy(css = ".other-communities-link a")
  private WebElement otherCommunitiesLink;

  private By otherCommunitiesLinkBy = By.cssSelector(".other-communities-link a");

  public CrossWikiSearchPageObject() {
    super();
  }

  public void verifyQuery(String query) {
    boolean isPresent = false;
    for (WebElement element : resultLinks) {
      if (element.getText().contains(query)) {
        isPresent = true;
        break;
      }
    }
    Assertion.assertTrue(isPresent, "there is no result link in the page");
  }

  public void goToSearchPage(String searchUrl) {
    try {
      getUrl(searchUrl + "/index.php?title=Special:Search");
    } catch (TimeoutException e) {
      Log.log("goToSearchPage", "timeouted when opening search page", false);
    }
  }

  public CrossWikiSearchPageObject searchFor(String term) {
    this.getGlobalNavigation().clickSearch();
    wait.forElementClickable(searchInput).clear();
    searchInput.sendKeys(term + Keys.ENTER);
    Log.log("searchFor", "Search button clicked", true, driver);
    return this;
  }

  public void searchForWiki(String searchUrl, String term) {
    try {
      getUrl(searchUrl + "/Special:Search?search=" + term);
    } catch (TimeoutException e) {
      Log.log("searchForWiki", "timeout", false);
    }
  }

  public void verifyFirstResultTitle(String wikiName) {
    wait.forTextInElement(firstResultLink, wikiName);
  }

  public void verifyFirstResultVertical(String vertical) {
    wait.forTextInElement(firstResultVertical, vertical);
    Assertion.assertFalse(firstResultVertical.getText().isEmpty(),
                          "Vertical (Hub) string is empty."
    );
  }

  public void verifyFirstResultDescription() {
    wait.forElementVisible(firstResultVertical);
    Assertion.assertFalse(firstResult.getText().isEmpty(), "There is no article description.");
  }

  public void verifyFirstResultPageCount() {
    wait.forElementVisible(firstResultStatisticsPageCount);
    Assertion.assertFalse(firstResultStatisticsPageCount.getText().isEmpty(),
                          "Page count string is empty."
    );
  }

  public void verifyFirstResultPageImages() {
    wait.forElementVisible(firstResultStatisticsPageImages);
    Assertion.assertFalse(firstResultStatisticsPageImages.getText().isEmpty(),
                          "Images count is empty."
    );
  }

  public void verifyFirstResultPageVideos() {
    wait.forElementVisible(firstResultStatisticsPageVideos);
    Assertion.assertFalse(firstResultStatisticsPageVideos.getText().isEmpty(),
                          "Results count is empty."
    );
  }

  /**
   * Verify that result count is same as expected
   *
   * @param expectedResultsPerPage number of results that should appear on result page
   */
  public void verifyResultsCount(int expectedResultsPerPage) {
    wait.forElementVisible(resultsContainer);
    Assertion.assertEquals(searchResultList.size(),
                           expectedResultsPerPage,
                           "Wrong number of results per page."
    );
  }

  /**
   * Verify data-pos attributes are present
   *
   * @param pageNumber     search result page number
   * @param resultsPerPage expected pages per result
   */
  public void verifyResultsPosForPage(int pageNumber, int resultsPerPage) {
    wait.forElementVisible(resultsContainer);
    int curNo = pageNumber * resultsPerPage + 1;
    for (WebElement link : resultLinks) {
      String dataPos = link.getAttribute("data-pos");
      int pos = Integer.parseInt(dataPos);
      Assertion.assertEquals(pos, curNo, "Wrong data-pos. Verify paging.");
      curNo++;
    }
  }

  /**
   * Clicks on nth result
   *
   * @param resultNumber zero based number of result to click
   * @return result page
   */
  public WikiArticleHomePage openResult(int resultNumber) {
    WebElement resultLink = resultLinks.get(resultNumber);
    wait.forElementVisible(resultLink);
    scrollAndClick(resultLink);
    return new WikiArticleHomePage();
  }

  public CrossWikiSearchPageObject prevPage() {
    scrollAndClick(paginatorPrev);
    Log.log("prevPage", "Moving to prev page of search results.", true, driver);
    return this;
  }

  public CrossWikiSearchPageObject nextPage() {
    scrollAndClick(paginatorNext);
    Log.log("nextPage", "Moving to next page of search results.", true, driver);
    return this;
  }

  public void verifyNoPagination() {
    wait.forElementNotPresent(paginationContainerBy);
    Log.log("verifyNoPagination", "pagination is not visible on the page", true);
  }

  public void verifyNoCommunitiesLink() {
    wait.forElementNotPresent(otherCommunitiesLinkBy);
    Log.log("verifyNoCommunitiesLink", "other communities link is not visible", true);
  }

  public void verifyNoResultsCaption() {
    wait.forElementVisible(noResultsCaption);
    String caption = noResultsCaption.getText();
    Assertion.assertTrue(caption.contains("No results found.") || caption.contains("no matches"));
    Log.log("verifyNoResultsCaption", "verified no results caption", true);
  }

  public void verifyThumbnailsAmount(int number) {
    Assertion.assertNumber(thumbnails.size(), number, "checking number of thumbnails");
    Log.log("verifyThumbnailsAmount", "thumbnails verified", true);
  }

  public boolean areThumbnailsContainImages() {
    String jpgOrPngImage = ".*(\\.png|\\.jpg|Wikia-hero-image).*";
    for (WebElement elem : thumbnails) {
      boolean isImage = Pattern.matches(jpgOrPngImage, elem.getAttribute("src"));
      Log.log("isImage", elem.getAttribute("src"), true);
      try {
        if (!isImage) {
          throw new AssertionError();
        }
      } catch (AssertionError ass) {
        Log.log("Thumbnail does not contain image", ass, true);
        return false;
      }
    }
    return true;
  }

  public void verifyDescription(int number) {
    Assertion.assertNumber(descriptions.size(), number, "checking number of thumbnails");
    for (WebElement elem : descriptions) {
      Assertion.assertTrue(!elem.getText().isEmpty(), "checking if description is not empty");
    }
    Log.log("verifyDescriptions", "descriptions verified", true);
  }

  public void verifyStatistics(int number) {
    Assertion.assertEquals(statisticsPages.size(), number);
    Assertion.assertEquals(statisticsImages.size(), number);
    Assertion.assertEquals(statisticsVideos.size(), number);
    for (int i = 0; i < number; i++) {
      Assertion.assertStringContains(statisticsPages.get(i).getText(), "PAGE");
      Assertion.assertStringContains(statisticsImages.get(i).getText(), "IMAGE");
      Assertion.assertStringContains(statisticsVideos.get(i).getText(), "VIDEO");
    }
  }
}
