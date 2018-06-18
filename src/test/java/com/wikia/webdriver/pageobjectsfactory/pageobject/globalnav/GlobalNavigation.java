package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.NotificationsDropdown;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GlobalNavigation extends BasePageObject {

  @FindBy(css = "#searchInput")
  private WebElement searchInput;

  @FindBy(css = ".wds-global-navigation__logo")
  private WebElement fandomLogo;

  @FindBy(css = ".wds-global-navigation__user-menu")
  private WebElement userAvatar;

  @FindBy(css = ".wds-global-navigation__user-menu .wds-global-navigation__dropdown-content")
  private WebElement userMenu;

  @FindBy(css =
      ".wds-global-navigation__user-menu .wds-global-navigation__dropdown-content li:first-child")
  private WebElement viewProfile;

  @FindBy(css = ".wds-sign-out__button")
  private WebElement signOutButton;

  @FindBy(css = ".wds-is-games")
  private WebElement gamesHubLink;

  @FindBy(css = ".wds-is-movies")
  private WebElement moviesHubLink;

  @FindBy(css = ".wds-is-tv")
  private WebElement tvHubLink;

  @FindBy(css = ".wds-global-navigation__wikis-menu")
  private WebElement wikisMenu;

  @FindBy(css = ".wds-global-navigation__wikis-menu .wds-dropdown__content")
  private WebElement wikisMenuContent;

  @FindBy(css = ".wds-global-navigation__notifications-menu")
  private WebElement notificationsIcon;

  @FindBy(css = ".wds-global-navigation__account-menu")
  private WebElement accountMenu;

  @FindBy(css = ".wds-global-navigation__start-a-wiki ")
  private WebElement startWikiButton;

  @FindBy(css = "a[data-tracking-label=\"link.community-central\"]")
  private WebElement communityCentralLink;

  @FindBy(css = "a[data-tracking-label=\"link.explore\"]")
  private WebElement exploreWikisLink;

  @FindBy(css = ".wds-global-navigation__partner-slot-link")
  private WebElement partnerSlotLink;

  @Getter
  private NotificationsDropdown notificationsDropdown = new NotificationsDropdown();

  public HomePage clickFandomLogo() {
    wait.forElementClickable(fandomLogo).click();
    Log.log("clickFandomLogo", "clicked on fandom logo in global nav bar", true);

    return new HomePage();
  }

  public HomePage clickGamesHubLink() {
    wait.forElementClickable(gamesHubLink).click();
    Log.log("clickGamesHubLink", "clicked on games hub link in global nav bar", true);

    return new HomePage();
  }

  public HomePage clickMoviesHubLink() {
    wait.forElementClickable(moviesHubLink).click();
    Log.log("clickMoviesHubLink", "clicked on movies hub link in global nav bar", true);

    return new HomePage();
  }

  public HomePage clickTVHubLink() {
    wait.forElementClickable(tvHubLink).click();
    Log.log("clickTVHubLink", "clicked on tv hub link in global nav bar", true);

    return new HomePage();
  }

  public GlobalNavigation openWikisMenu() {
    wait.forElementClickable(wikisMenu).click();
    Log.log("openWikisMenu", "clicked on wikis menu in global nav bar", true);

    return this;
  }

  public HomePage clickCommunityCentralLink() {
    wait.forElementClickable(communityCentralLink).click();
    Log.log("clickCommunityCentralLink", "clicked on community central link in global nav bar", true);

    return new HomePage();
  }

  public HomePage clickExploreWikisLink() {
    wait.forElementClickable(exploreWikisLink).click();
    Log.info("clicked on explore wikis link in global nav bar");

    return new HomePage();
  }

  public SearchPageObject search(String query) {
    searchInput.sendKeys(query);
    searchInput.submit();

    Log.info("search query typed and submitted");
    return new SearchPageObject(driver);
  }

  public GlobalNavigation clickUserAvatar() {
    wait.forElementClickable(userAvatar).click();
    Log.info("clicked on user avatar in global nav bar");

    return this;
  }

  public void clickSignOut() {
    clickUserAvatar();
    wait.forElementClickable(signOutButton).click();
    Log.info("link to sign out clicked");
  }

  public boolean isUserMenuOpened() {
    return isElementDisplayed(userMenu);
  }

  public void clickViewProfile() {
    viewProfile.click();
  }

  public boolean isFandomLogoVisible() {
    return isElementDisplayed(fandomLogo, 3);
  }

  public boolean isUserLoggedOut() {
    return driver.findElements(By.cssSelector(".wds-global-navigation__account-menu")).size() > 0;
  }

  public boolean isGamesHubVisible() {
    return isElementDisplayed(gamesHubLink, 3);
  }

  public boolean isMoviesHubVisible() {
    return isElementDisplayed(moviesHubLink, 3);
  }

  public boolean isTVHubVisible() {
    return isElementDisplayed(tvHubLink, 3);
  }

  public boolean isWikisMenuVisible() {
    return isElementDisplayed(wikisMenu,3 );
  }

  public boolean isSearchInputVisible() {
    return isElementDisplayed(searchInput, 3);
  }

  public boolean isUserAvatarVisible() {
    return isElementDisplayed(userAvatar, 3);
  }

  public boolean isNotificationsIconVisible() {
    return isElementDisplayed(notificationsIcon, 3);
  }

  public boolean isAccountMenuVisible() {
    return isElementDisplayed(accountMenu, 3);
  }

  public boolean isStartWikiButtonVisible() {
    return isElementDisplayed(startWikiButton, 3);
  }

  public boolean isCommunityCentralLinkVisible() {
    return isElementDisplayed(communityCentralLink, 3);
  }

  public boolean isPartnerSlotLinkVisible() {
    return isElementDisplayed(partnerSlotLink ,3);
  }
}
