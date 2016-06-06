package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.pages.SearchResultsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Search extends BasePageObject {

  @FindBy(css = ".wikia-search__container input.side-search__input")
  private WebElement searchInput;

  @FindBy(css = ".wikia-search__clear")
  private WebElement clearSearchButton;

  @FindBy(css = ".wikia-search__search-icon > svg > use[*|href*='#search']")
  private WebElement inputFieldSearchIcon;

  public static final int FOCUS_TIMEOUT_IN_SECONDS = 1;
  public static final int SUGGESTIONS_TIMEOUT_IN_SECONDS = 1;

  private static final String searchSuggestionClass = ".wikia-search__suggestions li.mw-content a";
  private static final String focusedSearchInput = ".wikia-search--focused .text-field-input";

  public String clickSearchSuggestion(int index) {
    Loading loading = new Loading(driver);
    String clickedLink;

    PageObjectLogging.logInfo("Select search suggestion no.: " + index);

    WebElement searchResult = driver.findElements(By.cssSelector(searchSuggestionClass)).get(index);
    wait.forElementClickable(searchResult);
    clickedLink = searchResult.getAttribute("href");

    searchResult.click();
    loading.handleAsyncPageReload();

    return clickedLink;
  }

  public Search typeInSearch(String text) {
    PageObjectLogging.logInfo("Local nav is not present");
    wait.forElementClickable(searchInput);
    searchInput.click();

    PageObjectLogging.logInfo("Search for query: " + text);
    searchInput.sendKeys(text);

    return this;
  }

  public String getSearchPhrase() {
    return searchInput.getAttribute("value");
  }

  public Search navigateToPage(String pageName) {
    PageObjectLogging.logInfo("Type in search input field: " + pageName);
    typeInSearch(pageName);
    PageObjectLogging.logInfo("Select first search suggestion");
    clickSearchSuggestion(0);

    return this;
  }

  public Search clickClearSearchButton() {
    PageObjectLogging.logInfo("Cancel searching phrase ");
    wait.forElementClickable(clearSearchButton);
    clearSearchButton.click();

    return this;
  }

  public SearchResultsPage clickEnterAndNavigateToSearchResults() {
    new Actions(driver).sendKeys(Keys.ENTER).perform();

    return new SearchResultsPage();
  }

  public boolean isInputFieldSearchIconVisible() {
    return isElementVisible(inputFieldSearchIcon);
  }

  public boolean isClearSearchButtonVisible() {
    return isElementVisible(clearSearchButton);
  }

  public boolean isSearchInputFieldVisible() {
    return isElementVisible(searchInput);
  }

  public boolean isSearchInputFieldEditable() {
    try {
      wait.forElementClickable(searchInput, 0).click();
      return true;
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    } catch (WebDriverException e) {
      // @TODO: this would be best pushed to Selenium upstream to actually throw a more specific
      // exception
      if (e.getMessage().contains("Element is not clickable at point")) {
        return false;
      } else {
        throw e;
      }
    }
  }

  public boolean areSearchSuggestionsDisplayed() {
    try {
      wait.forElementClickable(By.cssSelector(searchSuggestionClass),
                               SUGGESTIONS_TIMEOUT_IN_SECONDS);
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }

  public boolean isInputFieldFocused() {
    try {
      wait.forElementPresent(By.cssSelector(focusedSearchInput), FOCUS_TIMEOUT_IN_SECONDS);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    } catch (TimeoutException e) {
      return false;
    }
  }

  private boolean isElementVisible(WebElement element) {
    try {
      wait.forElementVisible(element);
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }
}
