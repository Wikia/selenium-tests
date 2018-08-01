package com.wikia.webdriver.elements.communities.mobile.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.elements.communities.mobile.pages.SearchResultsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Search extends BasePageObject {

  public static final int SUGGESTIONS_TIMEOUT_IN_SECONDS = 1;
  private static final String
      searchSuggestionClass
      = ".wds-search-modal .wds-global-navigation__search__suggestion";
  @FindBy(css = ".wds-search-modal input.wds-global-navigation__search-input")
  private WebElement searchInput;
  @FindBy(css = ".wds-search-modal .wds-global-navigation__search-clear")
  private WebElement clearSearchButton;
  @FindBy(css = ".wds-search-modal .wds-global-navigation__search-toggle-icon.wds-icon:not(.wds-icon-small)")
  private WebElement inputFieldSearchIcon;
  @FindBy(css = ".wds-search-modal .wds-global-navigation__search-container")
  private WebElement searchContainer;

  public boolean isPresent() {
    return isElementOnPage(searchInput);
  }

  public int getHeight() {
    return searchContainer.getSize().getHeight();
  }

  public String clickSearchSuggestion(int index) {
    wait.forElementPresent(By.cssSelector(searchSuggestionClass));

    Log.info("Select search suggestion no.: " + index);
    WebElement searchResult = driver.findElements(By.cssSelector(searchSuggestionClass)).get(index);
    wait.forElementClickable(searchResult);
    String clickedSuggestion = searchResult.getText();
    searchResult.click();
    waitForPageReload();

    return clickedSuggestion;
  }

  public Search typeInSearch(String text) {
    Log.info("Local nav is not present");
    wait.forElementClickable(searchInput);
    searchInput.click();

    Log.info("Search for query: " + text);
    searchInput.sendKeys(text);

    return this;
  }

  public String getSearchPhrase() {
    return searchInput.getAttribute("value");
  }

  public Search navigateToPage(String pageName) {
    Log.info("Type in search input field: " + pageName);
    typeInSearch(pageName);
    Log.info("Select first search suggestion");
    clickSearchSuggestion(0);

    return this;
  }

  public Search clickClearSearchButton() {
    Log.info("Cancel searching phrase ");
    wait.forElementClickable(clearSearchButton);
    clearSearchButton.click();

    return this;
  }

  public SearchResultsPage clickEnterAndNavigateToSearchResults(Skin fromSkin) {
    new Actions(driver).sendKeys(Keys.ENTER).perform();

    // Mobile wiki opens the SRP using AJAX, Mercury reloads the page and opens Mobile Wiki
    if (fromSkin == Skin.MOBILE_WIKI) {
      waitForPageReload();
    } else {
      Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));
    }

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

  public boolean areSearchSuggestionsDisplayed() {
    try {
      wait.forElementClickable(By.cssSelector(searchSuggestionClass),
                               SUGGESTIONS_TIMEOUT_IN_SECONDS
      );
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }

  private boolean isElementVisible(WebElement element) {
    try {
      wait.forElementVisible(element);
      return element.isDisplayed();
    } catch (TimeoutException e) {
      Log.info(e.getMessage());
      return false;
    }
  }
}
