package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.JoinTodayPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Navigation extends WikiBasePageObject {

  @FindBy(css = ".wikia-nav__avatar .wds-icon")
  private WebElement signInRegisterButton;

  @FindBy(css = ".wikia-nav__back")
  private WebElement backButton;

  @FindBy(css = ".nav-menu__item.nav-menu--root")
  private List<WebElement> subMenuLinks;

  @FindBy(css = "li.nav-menu__item a")
  private List<WebElement> localNavPageLinks;

  @FindBy(css = ".wikia-nav__header")
  private WebElement navigationMainHeader;

  @FindBy(css = ".nav-menu--games")
  private WebElement gamesHub;

  @FindBy(css = ".nav-menu--movies")
  private WebElement moviesHub;

  @FindBy(css = ".nav-menu--tv")
  private WebElement tvHub;

  @FindBy(css = ".wikia-nav__avatar")
  private WebElement userAvatar;

  @FindBy(css = ".wikia-nav__avatar img")
  private WebElement userAvatarImg;

  @FindBy(css = ".wikia-nav--profile-link")
  private WebElement userProfileLink;

  @FindBy(css = ".wds-menu-chevron")
  private WebElement userProfileArrow;

  @FindBy(css = ".nav-menu__header")
  private WebElement exploreWikiHeader;

  private By navigationComponent = By.cssSelector(".side-nav-menu");

  public Navigation() {
    PageFactory.initElements(driver, this);
  }

  public JoinTodayPage clickOnSignInRegisterButton() {
    PageObjectLogging.logInfo("Open Join Today page on mobile");
    wait.forElementClickable(signInRegisterButton).click();

    return new JoinTodayPage();
  }

  public Navigation clickBackButton() {
    PageObjectLogging.logInfo("Go back to previous navigation level");
    wait.forElementClickable(backButton);
    backButton.click();

    return this;
  }

  public Navigation clickExploreWikiHeader(Skin fromSkin) {
    PageObjectLogging.logInfo("Click 'Explore Wiki' header");
    wait.forElementClickable(exploreWikiHeader);

    exploreWikiHeader.click();

    // Mobile wiki opens the main page using AJAX, Mercury reloads the page and opens Mobile Wiki
    if (fromSkin == Skin.MOBILE_WIKI) {
      waitForPageReload();
    } else {
      new SkinHelper(driver).isSkin(Skin.DISCUSSIONS);
    }

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
    waitForPageReload();

    PageObjectLogging.logInfo("Navigation is closed");
    wait.forElementNotVisible(navigationComponent);

    Assertion.assertFalse(oldUrl.equalsIgnoreCase(driver.getCurrentUrl()),
                          "Navigation to selected page failed");
    PageObjectLogging.logInfo("Successfully navigated to selected page");


    return this;
  }

  public boolean isMainHeaderVisible() {
    return isElementVisible(navigationMainHeader);
  }

  public boolean isBackButtonVisible() {
    return isElementVisible(backButton);
  }

  public boolean isUserAvatarVisible() {
    return isElementVisible(userAvatar);
  }

  public boolean isUserAvatarVisible(final String username) {
    return wait.forElementVisible(userAvatarImg).isDisplayed() && userAvatarImg.getAttribute("alt").equals(username);
  }

  public boolean isUserProfileLinkVisible() {
    return isElementVisible(userProfileLink);
  }

  public boolean isNavigationToUserProfileVisible() {
    return isElementVisible(userProfileArrow);
  }

  public boolean areHubLinksVisible() {
    return isElementVisible(gamesHub)
        && isElementVisible(moviesHub)
        && isElementVisible(tvHub);
  }

  private boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public String getNavigationHeaderText() {
    wait.forElementVisible(navigationMainHeader);

    return navigationMainHeader.getText();
  }

  public UserProfile openUserProfile() {
    wait.forElementClickable(userProfileLink).click();
    return new UserProfile();
  }

}
