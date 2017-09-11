package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class TopBar extends BasePageObject {

  @FindBy(css = ".site-head")
  private WebElement navBar;

  @FindBy(css = ".site-logo")
  private WebElement logo;

  @FindBy(css = ".global-navigation-mobile__logo")
  private WebElement logoFandom;

  @FindBy(css = ".site-head-icon-nav")
  private WebElement hamburgerIcon;

  @FindBy(css = ".site-head-icon-search")
  private WebElement searchIcon;

  @FindBy(css = ".site-head-icon-search > a.icon-button")
  private WebElement searchIconClickableLink;

  @FindBy(css = ".icon-button-icon > use[*|href*='close']")
  private WebElement closeButtonInnerElement;

  @FindBy(css = ".nav-menu")
  private WebElement navMenu;

  @FindBy(css = ".wds-global-navigation__search-input")
  private WebElement searchInput;

  @FindBy(css = ".wds-global-navigation__search-suggestions .wds-global-navigation__dropdown-link")
  private List<WebElement> searchSuggestions;

  private By navigationComponent = By.cssSelector(".side-nav-drawer");
  private By parentBy = By.xpath("./..");

  public Navigation openNavigation() {
    PageObjectLogging.logInfo("Open navigation");
    wait.forElementClickable(hamburgerIcon);
    hamburgerIcon.click();

    PageObjectLogging.logInfo("Navigation is opened");
    wait.forElementVisible(navMenu);

    return new Navigation(driver);
  }

  public String typeInDesktopSearchAndSelectSuggestion(String query, int suggestionIndex) {
    wait.forElementVisible(searchInput);
    searchInput.sendKeys(query);

    WebElement selectedSearchSuggestion = searchSuggestions.get(suggestionIndex);
    wait.forElementClickable(selectedSearchSuggestion);

    String selectedSearchSuggestionText = selectedSearchSuggestion.getText();
    selectedSearchSuggestion.click();

    return selectedSearchSuggestionText;
  }

  public Search openSearch() {
    PageObjectLogging.logInfo("Open search");
    wait.forElementClickable(searchIcon);
    searchIcon.click();

    PageObjectLogging.logInfo("Search is opened");
    wait.forElementVisible(navigationComponent);

    return new Search();
  }

  public Navigation clickCloseButton() {
    // Clicking on the inner element doesn't always work so we click the parent (<svg>) instead
    WebElement closeButton = closeButtonInnerElement.findElement(parentBy);

    PageObjectLogging.logInfo("Click close button");
    wait.forElementClickable(closeButton);
    closeButton.click();

    return new Navigation(driver);
  }

  public void clickWikiaLogo() {
    PageObjectLogging.logInfo("Click Wikia logo");
    wait.forElementClickable(logo);
    logo.click();
    wait.forElementVisible(logoFandom);
  }


  public boolean isNavigationBarVisible() {
    try {
      return navBar.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isLogoVisible() {
    try {
      return logo.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isHamburgerIconVisible() {
    try {
      return hamburgerIcon.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isSearchIconVisible() {
    try {
      return searchIcon.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isSearchIconClickable() {
    try {
      searchIconClickableLink.isDisplayed();
      wait.forElementClickable(searchIconClickableLink, 0);
      return true;
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isCloseIconVisible() {
    try {
      return closeButtonInnerElement.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }
}
