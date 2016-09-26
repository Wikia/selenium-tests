package com.wikia.webdriver.pageobjectsfactory.pageobject.search;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPagePageObject extends WikiBasePageObject {

  @FindBy(css = "#search-v2-input")
  private WebElement searchInput;

  @FindBy(css = "#search-v2-button")
  private WebElement searchButton;

  @FindBy(css = ".exact-wiki-match__result")
  private WebElement relatedCommunityModule;

  private static final String SPECIAL_SEARCH_PAGE = "/wiki/Special:Search";
  private static final String EXISTING_COMMUNITY_NAME = "pokemon";

  public SearchPagePageObject() {
    super();
  }

  public SearchPagePageObject navigateToSearchPage() {
    new Navigate().toPage(SPECIAL_SEARCH_PAGE);

    return this;
  }

  public SearchPagePageObject typeInSearchInputCommunityName() {
    wait.forElementClickable(searchInput);
    searchInput.sendKeys(EXISTING_COMMUNITY_NAME);

    return this;
  }

  public SearchPagePageObject clickSearchButton() {
    wait.forElementClickable(searchButton);
    searchButton.click();

    return this;
  }

  public void relatedCommunityModuleIsVisible() {
    PageObjectLogging.logInfo("Is related community module visible?");
    wait.forElementVisible(relatedCommunityModule);
    PageObjectLogging.logInfo("Related community module is visible");
  }
}
