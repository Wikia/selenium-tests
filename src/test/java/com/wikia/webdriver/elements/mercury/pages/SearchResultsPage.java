package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.Search;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends WikiBasePageObject {
  @FindBy(css = ".search-results")
  private WebElement searchResultsContainer;

  @FindBy(css = ".search-error-not-found")
  private WebElement noResultsContainer;

  @FindBy(css = ".search-error-not-found__action")
  private WebElement tryAnotherSearchLink;

  @FindBy(css = ".search-results__load-more-wrapper .wikia-button")
  private WebElement loadMoreButton;

  @FindBy(css = ".search-results__list .wikia-card")
  private List<WebElement> resultCards;

  @Getter
  private final Search search = new Search();

  private static final String SEARCH_RESULT_SELECTOR = ".search-results__list .wikia-card > a";
  private static final String SPINNER_SELECTOR = ".spinner";

  public SearchResultsPage openForQuery(String query) {
    getUrl(String.format("%s%s", urlBuilder.getUrlForWiki(),
                         URLsContent.MOBILE_SEARCH_RESULTS_PAGE.replace("%query%", query)));

    return this;
  }

  public String clickSearchResult(int index) {
    Loading loading = new Loading(driver);
    String clickedLink;

    PageObjectLogging.logInfo("Select search result no.: " + index);
    WebElement searchResult = driver.findElements(By.cssSelector(SEARCH_RESULT_SELECTOR)).get(index);
    wait.forElementClickable(searchResult);

    clickedLink = searchResult.getAttribute("href");

    searchResult.click();
    loading.handleAsyncPageReload();

    return clickedLink;
  }

  public SearchResultsPage clickTryAnotherSearch() {
    wait.forElementClickable(this.tryAnotherSearchLink).click();
    return this;
  }

  public boolean isSearchResultsPageOpen() {
    try {
      wait.forElementVisible(searchResultsContainer);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isNoResultsPagePresent() {
    try {
      wait.forElementVisible(noResultsContainer, 0);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    } catch (TimeoutException e) {
      return false;
    }
  }

  public boolean isLoadMoreButtonVisible() {
    return isElementVisible(loadMoreButton);
  }

  public boolean areResultsPresent() {
    try {
      wait.forElementClickable(By.cssSelector(SEARCH_RESULT_SELECTOR), 0);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    } catch (TimeoutException e) {
      return false;
    }
  }

  public SearchResultsPage clickLoadMoreButton() {
    PageObjectLogging.logInfo("Click Load More button ");
    wait.forElementClickable(loadMoreButton);
    loadMoreButton.click();

    return this;
  }

  public SearchResultsPage waitForResultsLoaded() {
    wait.forElementNotVisible(By.cssSelector(SPINNER_SELECTOR));
    return this;
  }

  public int getResultCardsNumber() {
    waitForResultsLoaded();
    return resultCards.size();
  }

  private boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }
}
