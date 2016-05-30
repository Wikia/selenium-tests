package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.Search;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends WikiBasePageObject {
  @FindBy(css = ".search-results")
  private WebElement searchResultsContainer;

  @FindBy(css = ".search--no-results")
  private WebElement noResultsContainer;

  @Getter
  private final Search search = new Search();

  private static final String searchResultClass = ".search-results__list .search-results__item";

  public SearchResultsPage openForQuery(String query) {
    getUrl(String.format("%s%s", urlBuilder.getUrlForWiki(),
                         URLsContent.MOBILE_SEARCH_RESULTS_PAGE.replace("%query%", query)));

    return this;
  }

  public Search typeInSearch(String text) {
    return this.getSearch().typeInSearch(text);
  }

  public SearchResultsPage selectSearchResult(int index) {
    Loading loading = new Loading(driver);

    PageObjectLogging.logInfo("Select search result no.: " + index);
    WebElement searchResult = driver.findElements(By.cssSelector(searchResultClass)).get(index);
    wait.forElementClickable(searchResult);
    searchResult.click();
    loading.handleAsyncPageReload();

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
      wait.forElementVisible(noResultsContainer);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

}
