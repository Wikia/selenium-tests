package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

public class TopBar extends BasePageObject {

  @FindBy(css = ".site-head")
  private WebElement navBar;

  @FindBy(css = ".wikia-logo")
  private WebElement logo;

  @FindBy(css = ".site-head-icon-nav")
  private WebElement hamburgerIcon;

  @FindBy(css = ".site-head-icon-search")
  private WebElement searchIcon;

  @FindBy(css = ".site-head-icon-search > a.icon-button")
  private WebElement searchIconClickableLink;

  @FindBy(css = ".icon-button-icon > use[*|href*='close']")
  private WebElement closeButton;

  @FindBy(css = ".nav-menu")
  private WebElement navMenu;

  @FindBy(css = ".site-head-fandom-bar")
  private WebElement fandomBar;

  private By navigationComponent = By.cssSelector(".side-nav-drawer");

  public TopBar(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public Navigation openNavigation() {
    PageObjectLogging.logInfo("Open navigation");
    wait.forElementClickable(hamburgerIcon);
    hamburgerIcon.click();

    PageObjectLogging.logInfo("Navigation is opened");
    wait.forElementVisible(navMenu);

    return new Navigation(driver);
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
    PageObjectLogging.logInfo("Click close button");
    wait.forElementClickable(closeButton);
    closeButton.click();

    return new Navigation(driver);
  }

  public void clickWikiaLogo() {
    PageObjectLogging.logInfo("Click Wikia logo");
    wait.forElementClickable(logo);
    logo.click();
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
      return closeButton.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isFandomBarVisible() {
    try {
      return fandomBar.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }
}
