package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopBar extends WikiBasePageObject {

  @FindBy(css = ".site-head")
  private WebElement navBar;

  @FindBy(css = ".wikia-logo")
  private WebElement logo;

  @FindBy(css = ".site-head-icon-nav")
  private WebElement hamburgerIcon;

  @FindBy(css = ".site-head-icon-search")
  private WebElement searchIcon;

  @FindBy(css = ".icon-button-icon > use[*|href*='close']")
  private WebElement closeButton;

  private By navigationComponent = By.cssSelector(".side-nav-menu");

  private Wait wait;

  public TopBar(WebDriver driver) {
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public Navigation openNavigation() {
    PageObjectLogging.logInfo("Open navigation");
    wait.forElementClickable(hamburgerIcon);
    hamburgerIcon.click();

    PageObjectLogging.logInfo("Navigation is opened");
    wait.forElementVisible(navigationComponent);

    return new Navigation(driver);
  }

  public Search openSearch() {
    PageObjectLogging.logInfo("Open search");
    wait.forElementClickable(searchIcon);
    searchIcon.click();

    PageObjectLogging.logInfo("Search is opened");
    wait.forElementVisible(navigationComponent);

    return new Search(driver);
  }

  public Navigation closeNavigation() {
    PageObjectLogging.logInfo("Close navigation");
    wait.forElementClickable(closeButton);
    closeButton.click();

    PageObjectLogging.logInfo("Navigation is closed");
    wait.forElementNotVisible(navigationComponent);

    return new Navigation(driver);
  }

  public boolean isNavigationBarVisible() {
    try {
      wait.forElementVisible(navBar);
      PageObjectLogging.logInfo("Navigation bar is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("Navigation bar is not visible");
      return false;
    }
  }

  public boolean isLogoVisible() {
    try {
      wait.forElementVisible(logo);
      PageObjectLogging.logInfo("Wikia logo is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("Wikia logo is not visible");
      return false;
    }
  }

  public boolean isHamburgerIconVisible() {
    try {
      wait.forElementVisible(hamburgerIcon);
      PageObjectLogging.logInfo("Hamburger icon is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("Hamburger icon is not visible");
      return false;
    }
  }

  public boolean isSearchIconVisible() {
    try {
      wait.forElementVisible(searchIcon);
      PageObjectLogging.logInfo("Search icon is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("Search icon is not visible");
      return false;
    }
  }

  public boolean isCloseIconVisible() {
    try {
      wait.forElementVisible(closeButton);
      PageObjectLogging.logInfo("Close icon is visible");
      return true;
    } catch (Exception e) {
      PageObjectLogging.logInfo("Close icon is not visible");
      return false;
    }

  }
}
