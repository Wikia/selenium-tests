package com.wikia.webdriver.elements.communities.mobile.components.navigation.global;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.mobile.components.Search;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GlobalNavigationMobile extends BasePageObject {

  @FindBy(css = ".wds-global-navigation")
  private WebElement globalNavBar;

  @FindBy(css = ".wds-global-navigation__modal-control-anon")
  private WebElement anonUserAvatar;

  @FindBy(css = ".wds-global-navigation__modal-control-user")
  private WebElement userAvatar;

  @FindBy(css = "a.wds-global-navigation__logo")
  private WebElement logoFandom;

  @FindBy(css = ".wds-global-navigation__logo-heart-link")
  private WebElement logoFandomHeart;

  @FindBy(css = ".wds-global-navigation__modal-control-search")
  private WebElement searchEntrypoint;

  @FindBy(css = ".wds-global-navigation__modal-control-close")
  private WebElement closeButton;

  @FindBy(css = ".wds-global-navigation__search")
  private WebElement searchComponent;

  @FindBy(css = ".wds-search-modal a[data-tracking-label='link.games']")
  private WebElement gamesHub;

  @FindBy(css = ".wds-search-modal a[data-tracking-label='link.movies']")
  private WebElement moviesHub;

  @FindBy(css = ".wds-search-modal a[data-tracking-label='link.tv']")
  private WebElement tvHub;

  @FindBy(css = ".wds-search-modal a[data-tracking-label='link.video']")
  private WebElement videoHub;

  @FindBy(css = ".wds-search-modal .wds-global-navigation__link-group .wds-global-navigation__dropdown-toggle']")
  private WebElement wikisMenuLink;

  @FindBy(css = ".wds-global-navigation__dropdown-content")
  private WebElement wikisMenuDropdown;

  @FindBy(css = ".wds-search-modal a[data-tracking-label='link.start-a-wiki']")
  private WebElement startAwikiButtonInDropdown;

  private By navigationComponent = By.cssSelector(".wds-search-modal");

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

  public Search openSearch() {
    //click on search is entry point for search and navigation through hubs
    Log.info("Open search");
    wait.forElementClickable(searchEntrypoint);
    searchEntrypoint.click();

    Log.info("Search is opened");
    wait.forElementVisible(navigationComponent);

    return new Search();
  }

  public GlobalNavigationMobile clickCloseButton() {
    wait.forElementClickable(closeButton);
    closeButton.click();

    return new GlobalNavigationMobile();
  }

  public void clickFandomLogo() {
    Log.info("Click Wikia logoFandom");
    wait.forElementClickable(logoFandom).click();
  }



  public GlobalNavigationMobile clickWikisMenuLink() {
    wait.forElementClickable(wikisMenuLink);
    wikisMenuLink.click();

    return this;
  }

  public boolean isStartAwikiButtonNotVisibleinWikisDropdown() {
    wait.forElementVisible(wikisMenuDropdown);
    return isElementDisplayed(startAwikiButtonInDropdown);
  }

  public boolean isNavigationBarVisible() {return isVisible(globalNavBar); }

  public boolean isLogoVisible() {
    return isVisible(logoFandom);
  }

  public boolean isLogoHeartVisible() {return isVisible(logoFandomHeart); }

  public boolean isSearchIconVisible() {
    return isVisible(searchEntrypoint);
  }

  public boolean isSearchComponentPresent() { return isElementOnPage(searchComponent); }

  public boolean isAnonUserAvatarVisible() {return isVisible(anonUserAvatar); }

  public boolean isCloseIconVisible() {
    return isVisible(closeButton);
  }

  public boolean areInterntionalHubLinksVisible() {
    wait.forElementVisible(navigationComponent);

    return isVisible(gamesHub)
           && isVisible(moviesHub)
           && isVisible(tvHub);
  }

  public boolean isVideoHubLinkVisible() {
    wait.forElementVisible(navigationComponent);

    return isVisible(videoHub);
  }

  public Notifications getNotifications() {
    return new Notifications();
  }
}
