package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search {

  @FindBy(css = ".side-search__container input")
  private WebElement searchInput;

  @FindBy(css = ".side-search__clear")
  private WebElement clearSearchButton;

  private String searchResultClass = ".side-search__results li.mw-content a";
  private By loadingSearchResultsIndicator = By.cssSelector(".side-search__results li.loading");

  private WebDriver driver;
  private Wait wait;
  private Loading loading;

  public Search(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.loading = new Loading(driver);

    PageFactory.initElements(driver, this);
  }

  public Search seeNoSearchResults() {
    PageObjectLogging.logInfo("Loading search results indicator is present");
    wait.forElementPresent(loadingSearchResultsIndicator);

    PageObjectLogging.logInfo("Loading search results indicator is not present");
    wait.forElementNotPresent(loadingSearchResultsIndicator);

    PageObjectLogging.logInfo("No search results are present");
    wait.forElementNotPresent(By.cssSelector(searchResultClass));

    return this;
  }

  public Search selectSearchSuggestion(int index) {
    String oldUrl = driver.getCurrentUrl();

    PageObjectLogging.logInfo("Select search suggestion no.: " + index);
    WebElement searchResult = driver.findElements(By.cssSelector(searchResultClass)).get(index);
    wait.forElementClickable(searchResult);
    searchResult.click();
    loading.handleAsyncPageReload();

    Assertion.assertFalse(oldUrl.equalsIgnoreCase(driver.getCurrentUrl()),
                          "Navigation to selected search suggestion failed");
    PageObjectLogging.logInfo("Successfully navigated to selected search suggestion");

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

}
