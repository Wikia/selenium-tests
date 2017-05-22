package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;

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

  public HomePage clickFandomLogo() {
    wait.forElementVisible(fandomLogo);
    fandomLogo.click();

    return new HomePage();
  }

  public SearchPageObject search(String query) {
    searchInput.sendKeys(query);
    searchInput.submit();
    return new SearchPageObject(driver);
  }

  public GlobalNavigation clickUserAvatar() {
    userAvatar.click();
    return this;
  }

  public void clickSignOut() {
    clickUserAvatar();
    wait.forElementVisible(signOutButton).click();
  }

  public boolean isUserMenuOpened() {
    return userMenu.isDisplayed();
  }

  public void clickViewProfile() {
    viewProfile.click();
  }

  public boolean isFandomLogoVisible() {
    return fandomLogo.isDisplayed();
  }

  public boolean isUserLoggedOut() {
    return driver.findElements(By.cssSelector(".wds-global-navigation__account-menu")).size() > 0;
  }
}
