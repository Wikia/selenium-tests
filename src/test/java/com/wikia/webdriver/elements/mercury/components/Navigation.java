package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.JoinTodayPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Navigation extends WikiBasePageObject {

  @FindBy(css = ".wds-global-navigation__modal-control-anon")
  private WebElement signInRegisterButton;

  @FindBy(css = ".wds-global-navigation__modal-control-close")
  private WebElement closeButton;

  @FindBy(css = ".wds-global-navigation__link-group.wds-is-clicked .wds-global-navigation__dropdown-toggle")
  private WebElement wikisDropdownMenu;

  @FindBy(css = "li.nav-menu__item a")
  private List<WebElement> localNavPageLinks;

  @FindBy(css = "a[data-tracking-label='link.games']")
  private WebElement gamesHub;

  @FindBy(css = "a[data-tracking-label='link.movies']")
  private WebElement moviesHub;

  @FindBy(css = "a[data-tracking-label='link.tv']")
  private WebElement tvHub;

  @FindBy(css = "a[data-tracking-label='link.video']")
  private WebElement videoHub;

  @FindBy(css = ".wikia-nav__avatar")
  private WebElement userAvatar;

  @FindBy(css = ".wikia-nav__avatar img")
  private WebElement userAvatarImg;

  @FindBy(css = ".wikia-nav--profile-link")
  private WebElement userProfileLink;

  @FindBy(css = ".wds-menu-chevron")
  private WebElement userProfileArrow;

  private By navigationComponent = By.cssSelector(".side-nav-menu");

  public Navigation() {
    PageFactory.initElements(driver, this);
  }

  public JoinTodayPage clickOnSignInRegisterButton() {
    Log.info("Open Join Today page on mobile");
    wait.forElementClickable(signInRegisterButton).click();

    return new JoinTodayPage();
  }

  public Navigation closeNavigation() {
    Log.info("Close sub-menu");
    wait.forElementClickable(closeButton);
    closeButton.click();

    return this;
  }

  public Navigation clickWikisMenuLink() {
    wait.forElementClickable(wikisDropdownMenu);
    this.hover(wikisDropdownMenu);
    return this;
  }
  public Navigation openSubMenu(int index) {
    Log.info("Open sub-menu no.: " + index);
    //WebElement wikiMenuLink = subMenuLinks.get(index);
    //wait.forElementClickable(wikiMenuLink);
    //wikiMenuLink.click();

    return this;
  }

  public Navigation openPageLink(int index) {
    String oldUrl = driver.getCurrentUrl();

    Log.info("Open link to page no.: " + index);
    WebElement pageLink = localNavPageLinks.get(index);
    wait.forElementClickable(pageLink);
    pageLink.click();
    waitForPageReload();

    Log.info("Navigation is closed");
    wait.forElementNotVisible(navigationComponent);

    Assertion.assertFalse(oldUrl.equalsIgnoreCase(driver.getCurrentUrl()),
                          "Navigation to selected page failed");
    Log.info("Successfully navigated to selected page");


    return this;
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

  public boolean areInterntionalHubLinksVisible() {
    return isElementVisible(gamesHub)
        && isElementVisible(moviesHub)
        && isElementVisible(tvHub);
  }

  public boolean isVideoHubLinkVisible() {
    return isElementVisible(videoHub);
  }

  private boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      Log.info(e.getMessage());
      return false;
    }
  }

  public UserProfile openUserProfile() {
    wait.forElementClickable(userProfileLink).click();
    return new UserProfile();
  }

}
