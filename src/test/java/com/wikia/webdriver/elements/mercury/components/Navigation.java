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

  @FindBy(css = ".side-nav-menu__item.menu")
  private List<WebElement> subMenuLinks;

  @FindBy(css = ".local-nav-menu li.mw-content a")
  private List<WebElement> localNavPageLinks;

  @FindBy(css = "a[href=\"/recent-wiki-activity\"]")
  private WebElement recentWikiActivityLink;

  @FindBy(css = ".profile-link")
  private WebElement userProfile;

  @FindBy(css = ".main")
  private WebElement navigationMainHeader;

  @FindBy(css = ".side-nav-menu__footer")
  private WebElement homeOfFandomFooter;

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
    this.openSubMenu(1);

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
      wait.forElementVisible(userProfile);
      PageObjectLogging.logInfo("User profile link is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("User profile link is not visible");
      return false;
    }
  }


  public boolean isMainHeaderVisible() {
    try {
      wait.forElementVisible(navigationMainHeader);
      PageObjectLogging.logInfo("Main header is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("Main header is not visible");
      return false;
    }
  }

  public boolean isBackButtonVisible() {
    try {
      wait.forElementVisible(backButton);
      PageObjectLogging.logInfo("Back button is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("Back button is not visible");
      return false;
    }
  }

  public boolean isFooterVisible() {
    try {
      wait.forElementVisible(homeOfFandomFooter);
      PageObjectLogging.logInfo("Footer is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("Footer is not visible");
      return false;
    }
  }
}
