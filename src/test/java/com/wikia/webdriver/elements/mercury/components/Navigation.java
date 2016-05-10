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
import org.openqa.selenium.NoSuchElementException;

public class Navigation {

  @FindBy(css = ".side-nav-menu__item.main")
  private WebElement signInRegisterButton;

  @FindBy(css = ".side-nav-menu__item.back")
  private WebElement backButton;

  @FindBy(css = ".side-nav-menu__item.menu")
  private List<WebElement> subMenuLinks;

  @FindBy(css = "li.side-nav-menu__item a")
  private List<WebElement> localNavPageLinks;

  @FindBy(css = "a[href=\"/recent-wiki-activity\"]")
  private WebElement recentWikiActivityLink;

  @FindBy(css = ".profile-link")
  private WebElement userProfile;

  @FindBy(css = ".main")
  private WebElement navigationMainHeader;

  @FindBy(css = ".side-nav-menu__footer")
  private WebElement homeOfFandomFooter;

  @FindBy(css = "li.games")
  private WebElement gamesHub;

  @FindBy(css = "li.movies")
  private WebElement moviesHub;

  @FindBy(css = "li.tv")
  private WebElement tvHub;


  private By localNavMenu = By.cssSelector(".local-nav-menu");
  private By navigationComponent = By.cssSelector(".side-nav-menu");
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

  public Navigation clickBackButton() {
    PageObjectLogging.logInfo("Go back to previous navigation level");
    wait.forElementClickable(backButton);
    backButton.click();

    return this;
  }

  public Navigation closeSubMenu() {
    PageObjectLogging.logInfo("Close sub-menu");
    wait.forElementClickable(backButton);
    backButton.click();

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

  public Navigation openRecentWikiActivity() {
    wait.forElementClickable(recentWikiActivityLink);
    recentWikiActivityLink.click();

    loading.handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("/recent-wiki-activity"),
                         "You were not redirected to the recent wiki activity page");
    PageObjectLogging.logInfo("You were redirected to the recent wiki activity page");

    return this;
  }

  public boolean isUserProfileLinkVisible() {
    try {
      return userProfile.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }


  public boolean isMainHeaderVisible() {
    try {
      return navigationMainHeader.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isBackButtonVisible() {
    try {
      return backButton.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isFooterVisible() {
    try {
      return homeOfFandomFooter.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean areHubLinksVisible() {
    try {
      return gamesHub.isDisplayed()
          && moviesHub.isDisplayed()
          && tvHub.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }
}
