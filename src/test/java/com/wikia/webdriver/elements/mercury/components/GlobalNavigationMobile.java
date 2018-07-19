package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GlobalNavigationMobile extends BasePageObject {

  @FindBy(css = ".wds-global-navigation")
  private WebElement navBar;

  @FindBy(css = ".wds-global-navigation__modal-control-anon")
  private WebElement anonUserAvatar;

  @FindBy(css = ".wds-global-navigation__modal-control-user")
  private WebElement userAvatar;

  @FindBy(css = ".wds-global-navigation__logo-image")
  private WebElement logoFandom;

  @FindBy(css = ".wds-global-navigation__modal-control-search")
  private WebElement searchIcon;

  @FindBy(css = ".wds-global-navigation__modal-control-search > svg.wds-icon")
  private WebElement searchIconClickableLink;

  @FindBy(css = ".wds-global-navigation__modal-control-close")
  private WebElement closeButton;

  @FindBy(css = ".wds-global-navigation__modal-control-search")
  private WebElement navMenu;

  @FindBy(css = ".wds-global-navigation__search-input-wrapper")
  private WebElement searchInput;

  @FindBy(css = ".wds-global-navigation__search-suggestions .wds-global-navigation__dropdown-link")
  private List<WebElement> searchSuggestions;

  @FindBy(css = ".wds-community-bar__navigation > .wds-dropdown__toggle")
  private WebElement navigationHamburgerIcon;

  @FindBy(css = ".wds-community-bar__level-1")
  private WebElement firstLevelNavigationMenu;

  private By navigationComponent = By.cssSelector(".wds-search-modal");
  private By parentBy = By.xpath("./..");

  public SignInPage clickOnAnonAvatar() {
    //When anon clicks avatar placeholder - auth page is opened
    Log.info("Wait and click on anon user avatar");
    wait.forElementVisible(anonUserAvatar).click();

    return new AttachedSignInPage();
  }

  public GlobalNavigationMobile clickOnLoggedInUserAvatar() {
    //When logged in clicks avatar - profile & notifications part is visible
    Log.info("Wait and click on logged in user avatar");
    wait.forElementVisible(userAvatar).click();

    return new GlobalNavigationMobile();
  }

  public GlobalNavigationMobile openNavigation() {
    wait.forElementVisible(navigationHamburgerIcon).click();

    return this;
  }

  public Search openSearch() {
    Log.info("Open search");
    wait.forElementClickable(searchIcon);
    searchIcon.click();

    Log.info("Search is opened");
    wait.forElementVisible(navigationComponent);

    return new Search();
  }

  public GlobalNavigation clickCloseButton() {
    // Clicking on the inner element doesn't always work so we click the parent (<svg>) instead
    wait.forElementClickable(closeButton);
    closeButton.click();

    return new GlobalNavigation();
  }

  public void clickFandomLogo() {
    Log.info("Click Wikia logoFandom");
    wait.forElementClickable(logoFandom).click();
  }

  public boolean isFirstLevelMenuVisible() {
    return isVisible(firstLevelNavigationMenu);
  }

  public boolean isNavigationBarVisible() {
    return isVisible(navBar);
  }

  public boolean isLogoVisible() {
    return isVisible(logoFandom);
  }

  public boolean isSearchIconVisible() {
    return isVisible(searchIcon);
  }

  public boolean isCloseIconVisible() {
    return isVisible(closeButton);
  }

  public Notifications getNotifications() {
    return new Notifications();
  }
}
