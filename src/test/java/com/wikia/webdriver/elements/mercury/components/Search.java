package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.pages.SearchResultsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

  private static final String searchSuggestionClass = ".wikia-search__suggestions li.mw-content a";

  private Loading loading;

  public Search() {
    this.loading = new Loading(driver);
  }

  public Search selectSearchSuggestion(int index) {
    PageObjectLogging.logInfo("Select search suggestion no.: " + index);
    WebElement searchResult = driver.findElements(By.cssSelector(searchSuggestionClass)).get(index);
    wait.forElementClickable(searchResult);
    searchResult.click();
    loading.handleAsyncPageReload();

    return this;
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
    selectSearchSuggestion(0);

    return this;
  }

  public Search clickClearSearchButton() {
    PageObjectLogging.logInfo("Cancel searching phrase ");
    wait.forElementClickable(clearSearchButton);
    clearSearchButton.click();

    return this;
  }

  public boolean isInputFieldSearchIconVisible() {
    try {
      wait.forElementVisible(inputFieldSearchIcon);
      return inputFieldSearchIcon.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isClearSearchButtonVisible() {
    try {
      wait.forElementVisible(clearSearchButton);
      return clearSearchButton.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isSearchInputFieldVisible() {
    try {
      wait.forElementVisible(searchInput);
      return searchInput.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public SearchResultsPage clickEnterAndNavigateToSearchResults() {
    new Actions(driver).sendKeys(Keys.ENTER).perform();

    return new SearchResultsPage();
  }

}
