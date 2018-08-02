package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.desktop.components.navigation.global.GlobalNavigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SearchPageObject extends WikiBasePageObject {

  protected static final String PAGINATION_PAGES_CSS = ".paginator-page";

  @FindBy(css = ".wds-global-navigation__search-input-wrapper input")
  protected WebElement searchInput;
  @FindBy(css = ".wds-global-navigation__search-submit")
  protected WebElement searchSubmit;
  @FindBy(css = "#search-v2-button")
  protected WebElement searchV2Button;
  @FindBy(css = ".Results")
  protected WebElement resultsContainer;
  @FindBy(css = ".paginator-next")
  protected WebElement paginatorNext;
  @FindBy(css = ".paginator-prev")
  protected WebElement paginatorPrev;
  @FindBy(css = PAGINATION_PAGES_CSS)
  protected List<WebElement> paginationPages;
  @FindBy(css = "h1 > a.result-link")
  protected List<WebElement> resultLinks;
  @FindBys(@FindBy(css = "li.result"))
  protected List<WebElement> results;
  @FindBy(css = ".Results > :nth-child(1) h1 > a, .top-community-name")
  protected WebElement firstResultLink;
  @FindBy(css = "li.result:nth-child(1) a")
  protected WebElement firstResult;
  @FindBy(css = ".result-count.subtle")
  protected WebElement resultCountMessage;
  @FindBy(css = ".results-wrapper i")
  protected WebElement noResultsCaption;

  protected By paginationContainerBy = By.cssSelector(".wikia-paginator, .pagination");

  @FindBy(css = ".everything")
  private WebElement filterEverything;

  public SearchPageObject() {
    super();
  }

  public void clickNextPaginator() {
    scrollAndClick(paginatorNext);
    Log.log("clickNextPaginator", "next paginator clicked", true);
  }

  public void clickPrevPaginator() {
    wait.forElementVisible(By.cssSelector(".paginator-prev"));
    scrollAndClick(paginatorPrev);
    Log.log("clickPrevPaginator", "prev paginator clicked", true);
  }

  public void verifyNoResults() {
    Assertion.assertEquals(noResultsCaption.getText(), "No results found.");
  }

  public void verifyPagination() {
    wait.forElementPresent(paginationContainerBy);
    int i = 1;
    for (WebElement elem : paginationPages) {
      Assertion.assertEquals(elem.getText(), Integer.toString(i));
      i++;
    }
  }

  public boolean isResultPresent() {
    return isVisible(resultCountMessage);
  }

  public void clickSearchButton() {
    new GlobalNavigation().clickSearch();
    Log.log("clickSearchButton", "Search button was clicked", true, driver);
  }

  public void setSearchTab(SearchTab tab) {
    WebElement tabElem = driver.findElement(tab.getBy());
    scrollAndClick(tabElem);
  }

  public enum SearchTab {
    ARTICLES(".articles"), PHOTOS_AND_VIDEOS(".photos-and-videos"), PEOPLE(".people"), EVERYTHING(
        ".everything");

    private final String cssSelector;

    SearchTab(String cssSelector) {
      this.cssSelector = cssSelector;
    }

    public By getBy() {
      return By.cssSelector(cssSelector);
    }
  }
}
