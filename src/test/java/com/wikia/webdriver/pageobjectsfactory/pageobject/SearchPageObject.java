package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SearchPageObject extends WikiBasePageObject {

  public SearchPageObject(WebDriver driver) {
    super(driver);
  }

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
  @FindBy(css = ".paginator-page")
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

  @FindBy(css = ".everything")
  private WebElement filterEverything;

  protected By paginationContainerBy = By.cssSelector(".wikia-paginator");

  public void clickNextPaginator() {
    scrollAndClick(paginatorNext);
    PageObjectLogging.log("clickNextPaginator", "next paginator clicked", true);
  }

  public void clickPrevPaginator() {
    scrollAndClick(paginatorPrev);
    PageObjectLogging.log("clickPrevPaginator", "prev paginator clicked", true);
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
    PageObjectLogging.log("clickSearchButton", "Search button was clicked", true, driver);
  }

  public void setSearchTab(SearchTab tab) {
	  WebElement tabElem = driver.findElement(tab.getBy());
	  scrollAndClick(tabElem);
  }

  public enum SearchTab {
    ARTICLES(".articles"), PHOTOS_AND_VIDEOS(".photos-and-videos"), PEOPLE(".people"), EVERYTHING(
        ".everything");

    private final String cssSelector;

    private SearchTab(String cssSelector) {
      this.cssSelector = cssSelector;
    }

    public By getBy() {
      return By.cssSelector(cssSelector);
    }
  }
}
