package com.wikia.webdriver.elements.communities.desktop.components.navigation.global;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notifications;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.NotificationsDropdown;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GlobalNavigation extends BasePageObject {

  By loggedOutUserAvatar = By.cssSelector(".wds-global-navigation__user-anon");
  @FindBy(css = ".wds-global-navigation__search-toggle")
  private WebElement searchButton;
  @FindBy(css = ".wds-global-navigation__search-input-wrapper input")
  private WebElement searchInput;
  @FindBy(css = ".wds-global-navigation__search-submit")
  private WebElement searchSubmitButton;
  @FindBy(css = ".wds-global-navigation__logo")
  private WebElement fandomLogo;
  @FindBy(css = ".wds-global-navigation__user-logged-in")
  private WebElement userAvatar;
  @FindBy(css = ".wds-global-navigation__user-anon")
  private WebElement anonAvatar;
  @FindBy(css = ".wds-global-navigation__user-logged-in .wds-dropdown__content")
  private WebElement userMenu;
  @FindBy(css = ".wds-global-navigation__user-logged-in a[href*='User:']")
  private WebElement viewProfile;
  @FindBy(css = ".wds-sign-out__button")
  private WebElement signOutButton;
  @FindBy(css = "a[data-tracking-label*='link.games']")
  private WebElement gamesHubLink;
  @FindBy(css = "a[data-tracking-label*='link.movies']")
  private WebElement moviesHubLink;
  @FindBy(css = "a[data-tracking-label*='link.tv']")
  private WebElement tvHubLink;
  @FindBy(css = "a[href*='TV']")
  private WebElement tvDEHubLink;
  @FindBy(css = "a[href*='video']")
  private WebElement videoHubLink;
  @FindBy(css = ".wds-global-navigation__dropdown-toggle span")
  private WebElement wikisMenu;
  @FindBy(css = ".wds-global-navigation__link-group .wds-global-navigation__dropdown-content")
  private WebElement wikisMenuContent;
  @FindBy(css="a[data-tracking-label*='link.community-central']")
  private WebElement communityCentralLink;
  @FindBy(css = ".wds-global-navigation__notifications-dropdown")
  private WebElement notificationsIcon;
  @FindBy(css = ".wds-global-navigation__start-a-wiki")
  private WebElement startWikiButton;
  @FindBy(css = "a[data-tracking-label*='link.explore']")
  private WebElement exploreWikisLink;
  @FindBy(css = "a[href*='Videospiele']")
  private WebElement videospieleHubLink;
  @FindBy(css = "a[href*='Filme']")
  private WebElement filmeHubLink;
  @FindBy(css = ".wds-global-navigation__user-menu")
  private WebElement myAccount;
  @FindBy(xpath = "//div[contains(@class,\"wds-global-navigation__user-menu\")]//li[1]/a")
  private WebElement signInLink;
  @FindBy(xpath = "//div[contains(@class,\"wds-global-navigation__user-menu\")]//li[2]/a")
  private WebElement registerLink;
  @FindBy(css = ".wds-global-navigation__search-close")
  private WebElement clearSearchPhraseButton;
  @FindBy(css = ".wds-global-navigation__search__suggestion")
  private List<WebElement> searchSuggestionsList;

  @FindBy(css = ".wds-is-linked a[href*='exploruj']")
  private WebElement explorujWikisLink;
  @FindBy(css = "a[href*='Gry']")
  private WebElement gryHubLink;
  @FindBy(css = "a[href*='Filmy']")
  private WebElement filmyHubLink;

  @Getter
  private NotificationsDropdown notificationsDropdown = new NotificationsDropdown();

  public AttachedSignInPage clickOnSignIn() {
    wait.forElementClickable(myAccount).click();
    wait.forElementClickable(signInLink).click();
    return new AttachedSignInPage();
  }

  public AttachedRegisterPage clickOnRegister() {
    wait.forElementClickable(myAccount).click();
    wait.forElementClickable(registerLink).click();
    return new AttachedRegisterPage();
  }

  public SearchPageObject clickSearch() {
    wait.forElementClickable(searchButton).click();

    return new SearchPageObject();
  }

  public GlobalNavigation clearSearchPhrase() {
    wait.forElementClickable(clearSearchPhraseButton).click();

    return this;
  }

  public void clickNthSearchResult(int n) {
    wait.forElementVisible(searchSuggestionsList.get(n)).click();
  }

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

  public String getFandomLogoLink() {
    wait.forElementVisible(fandomLogo);
    return fandomLogo.getAttribute("href");
  }

  public String getGamesHubLink() {
    wait.forElementVisible(gamesHubLink);
    return gamesHubLink.getAttribute("href");
  }
  public String getMoviesHubLink() {
    wait.forElementVisible(moviesHubLink);
    return moviesHubLink.getAttribute("href");
  }

  public String getTVHubLink() {
    wait.forElementVisible(tvHubLink);
    return tvHubLink.getAttribute("href");
  }

  public GlobalNavigation openWikisMenu() {
    wait.forElementClickable(wikisMenu).click();
    Log.log("openWikisMenu", "clicked on wikis menu in global nav bar", true);

    return this;
  }

  public HomePage clickExploreWikisLink() {
    wait.forElementClickable(exploreWikisLink).click();
    Log.info("clicked on explore wikis link in global nav bar");

    return new HomePage();
  }

  public HomePage clickCommunityCentralLink() {
    wait.forElementClickable(communityCentralLink).click();
    Log.log("clickCommunityCentralLink",
            "clicked on community central link in global nav bar",
            true
    );

    return new HomePage();
  }

  public SearchPageObject search(String query) {
    wait.forElementClickable(searchButton).click();
    searchInput.sendKeys(query);
    searchSubmitButton.submit();

    Log.info("search query typed and submitted");
    return new SearchPageObject();
  }

  /*
    typeInSearch() does not clicks the submit button
   */
  public GlobalNavigation typeInSearch(String query) {
    wait.forElementClickable(searchButton).click();
    searchInput.sendKeys(query);

    Log.info("search query typed");
    return this;
  }

  public String getCurrentSearchPhrase() {
    wait.forElementClickable(searchButton).click();
    wait.forElementVisible(searchInput);

    return searchInput.getText();
  }

  public GlobalNavigation clickUserAvatar() {
    wait.forElementClickable(userAvatar).click();
    Log.info("clicked on user avatar in global nav bar");

    return this;
  }

  public GlobalNavigation clickAnonUserAvatar() {
    wait.forElementClickable(anonAvatar).click();
    Log.info("clicked on anon user avatar in global nav bar");

    return this;
  }

  public void clickAvatarAndSignOut() {
    clickUserAvatar();
    wait.forElementClickable(signOutButton).click();
    Log.info("link to sign out clicked");
  }

  public boolean isUserMenuOpened() {
    return isElementDisplayed(userMenu);
  }

  public void clickViewProfile() {
    wait.forElementClickable(viewProfile).click();
  }

  public boolean isFandomLogoVisible() {
    return isElementDisplayed(fandomLogo, 3);
  }

  public boolean isUserLoggedOut() {
    return driver.findElements(loggedOutUserAvatar).size() > 0;
  }

  public boolean isGamesHubVisible() {
    return isElementDisplayed(gamesHubLink, 3);
  }

  public boolean isVideospieleHubVisible() {
    return isElementDisplayed(videospieleHubLink, 3);
  }

  public boolean isGryHubVisible() {
    return isElementDisplayed(gryHubLink, 3);
  }

  public boolean isMoviesHubVisible() {
    return isElementDisplayed(moviesHubLink, 3);
  }

  public boolean isFilmyHubVisible() {
    return isElementDisplayed(filmyHubLink, 3);
  }

  public boolean isFilmeHubVisible() {
    return isElementDisplayed(filmeHubLink, 3);
  }

  public boolean isTVHubVisible() {
    return isElementDisplayed(tvHubLink, 3);
  }

  public boolean isTVDEHubVisible() {
    return isElementDisplayed(tvDEHubLink, 3);
  }

  public boolean isVideoHubVisible() {
    return isElementDisplayed(videoHubLink, 3);
  }

  public boolean isWikisMenuVisible() {
    return isElementDisplayed(wikisMenu, 3);
  }

  public boolean isSearchInputVisible() {
    return isElementDisplayed(searchButton, 3);
  }

  public boolean isUserAvatarVisible() {
    return isElementDisplayed(userAvatar, 3);
  }

  public boolean isNotificationsIconVisible() {
    return isElementDisplayed(notificationsIcon, 3);
  }

  public boolean isStartWikiButtonVisible() {
    return isElementDisplayed(startWikiButton, 3);
  }

  public String getNthSearchResultText(int n) {
    return wait.forElementVisible(searchSuggestionsList.get(n)).getText();
  }

  public Notifications getNotifications() {
    return new Notifications();
  }
}
