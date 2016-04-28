package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class Navigation {

  @FindBy(css = ".side-nav-menu__item.main")
  private WebElement signInRegisterButton;

  @FindBy(css = ".side-nav-menu__item.back")
  private WebElement backButton;

  @FindBy(css = ".side-search__container input")
  private WebElement searchInput;

  @FindBy(css = ".side-nav-menu__item.menu")
  private List<WebElement> subMenuLinks;

  @FindBy(css = ".local-nav-menu li.mw-content a")
  private List<WebElement> localNavPageLinks;

  @FindBy(css = "a[href=\"/recent-wiki-activity\"]")
  private WebElement recentWikiActivityLink;

  private By localNavMenu = By.cssSelector(".local-nav-menu");
  private By cancelSearchButton = By.cssSelector(".side-search__cancel");
  private By navigationComponent = By.cssSelector(".side-nav-menu");
  private By loadingSearchResultsIndicator = By.cssSelector(".side-search__results li.loading");

  private String searchResultClass = ".side-search__results li.mw-content a";

  private WebDriver driver;
  private Wait wait;
  private Loading loading;


  public Navigation(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.loading = new Loading(driver);

    PageFactory.initElements(driver, this);
  }

  public Navigation clickOnSignInRegisterButton() {
    PageObjectLogging.logInfo("Open login page");
    wait.forElementClickable(signInRegisterButton);
    signInRegisterButton.click();

    return this;
  }

  public Navigation cancelSearch() {
    PageObjectLogging.logInfo("Cancel search");
    WebElement cancelButton = driver.findElement(cancelSearchButton);
    wait.forElementClickable(cancelButton);
    cancelButton.click();

    PageObjectLogging.logInfo("Cancel search button is not present");
    wait.forElementNotPresent(cancelSearchButton);

    PageObjectLogging.logInfo("Local nav is present");
    wait.forElementPresent(localNavMenu);

    return this;
  }

  public Navigation seeNoSearchResults() {
    PageObjectLogging.logInfo("Loading search results indicator is present");
    wait.forElementPresent(loadingSearchResultsIndicator);

    PageObjectLogging.logInfo("Loading search results indicator is not present");
    wait.forElementNotPresent(loadingSearchResultsIndicator);

    PageObjectLogging.logInfo("No search results are present");
    wait.forElementNotPresent(By.cssSelector(searchResultClass));

    return this;
  }

  public Navigation closeSubMenu() {
    PageObjectLogging.logInfo("Close sub-menu");
    wait.forElementClickable(backButton);
    backButton.click();

    return this;
  }

  public Navigation selectSearchSuggestion(int index) {
    String oldUrl = driver.getCurrentUrl();

    PageObjectLogging.logInfo("Select search suggestion no.: " + index);
    WebElement searchResult = driver.findElements(By.cssSelector(searchResultClass)).get(index);
    wait.forElementClickable(searchResult);
    searchResult.click();
    loading.handleAsyncPageReload();

    PageObjectLogging.logInfo("Navigation is closed");
    wait.forElementNotVisible(navigationComponent);

    Assertion.assertFalse(oldUrl.equalsIgnoreCase(driver.getCurrentUrl()),
                          "Navigation to selected search suggestion failed");
    PageObjectLogging.logInfo("Successfully navigated to selected search suggestion");

    return this;
  }

  public Navigation openSubMenu(int index) {
    PageObjectLogging.logInfo("Open sub-menu no.: " + index);
    WebElement wikiMenuLink = subMenuLinks.get(index);
    wait.forElementClickable(wikiMenuLink);
    wikiMenuLink.click();

    return this;
  }

  public Navigation openPageLink(int index) {
    String oldUrl = driver.getCurrentUrl();

    PageObjectLogging.logInfo("Open link to page no.: " + index);
    WebElement pageLink = localNavPageLinks.get(index);
    wait.forElementClickable(pageLink);
    pageLink.click();
    loading.handleAsyncPageReload();

    PageObjectLogging.logInfo("Navigation is closed");
    wait.forElementNotVisible(navigationComponent);

    Assertion.assertFalse(oldUrl.equalsIgnoreCase(driver.getCurrentUrl()),
                          "Navigation to selected page failed");
    PageObjectLogging.logInfo("Successfully navigated to selected page");


    return this;
  }

  public Navigation typeInSearch(String text) {
    PageObjectLogging.logInfo("Local nav is not present");
    wait.forElementClickable(searchInput);
    searchInput.click();
    wait.forElementNotPresent(localNavMenu);

    PageObjectLogging.logInfo("Search for query: " + text);
    searchInput.sendKeys(text);

    return this;
  }

  public Navigation navigateToPage(String pageName) {
    typeInSearch(pageName);
    selectSearchSuggestion(0);

    return this;
  }

  public Navigation openRecentWikiActivity() {
    this.openSubMenu(1);

    wait.forElementClickable(recentWikiActivityLink);
    recentWikiActivityLink.click();

    loading.handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("/recent-wiki-activity"),
                         "You were not redirected to the recent wiki activity page");
    PageObjectLogging.logInfo("You were redirected to the recent wiki activity page");

    return this;
  }
}
