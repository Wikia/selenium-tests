package com.wikia.webdriver.pageobjectsfactory.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;

public class SearchPageObject extends WikiBasePageObject {

  protected static final String PAGINATION_PAGES_CSS = ".paginator-page";

  @FindBy(css = "#searchInput")
  protected WebElement searchInput;
  @FindBy(css = "#search-v2-button")
  protected WebElement searchButton;
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
  @FindBy(css = ".Results > :nth-child(1) h1 > a")
  protected WebElement firstResultLink;
  @FindBy(css = "li.result:nth-child(1) a")
  protected WebElement firstResult;
  @FindBy(css = ".result-count.subtle")
  protected WebElement resultCountMessage;
  @FindBy(css = ".results-wrapper i")
  protected WebElement noResultsCaption;

  protected By paginationContainerBy = By.cssSelector(".wikia-paginator");

  @FindBy(css = ".everything")
  private WebElement filterEverything;

  public SearchPageObject(WebDriver driver) {
    super(driver);
  }

  public void clickNextPaginator() {
    scrollAndClick(paginatorNext);
    LOG.success("clickNextPaginator", "next paginator clicked");
  }

  public void clickPrevPaginator() {
    wait.forElementVisible(By.cssSelector(".paginator-prev"));
    scrollAndClick(paginatorPrev);
    LOG.success("clickPrevPaginator", "prev paginator clicked");
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
    wait.forElementVisible(resultCountMessage);
    return resultCountMessage.isDisplayed();
  }

  public void clickSearchButton() {
    searchButton.click();
    LOG.success("clickSearchButton", "Search button was clicked", true);
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
