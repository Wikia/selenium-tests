package com.wikia.webdriver.pageobjectsfactory.componentobject.navigation.local;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class LocalNavigationMobile extends WikiBasePageObject {

  @FindBy(css = ".wds-community-bar")
  private WebElement communityBar;

  @FindBy(css = ".site-head-container .wds-community-bar__sitename")
  private WebElement communityName;

  @FindBy(css = ".wds-community-bar__discussions")
  private WebElement discussionsEntrypoint;

  @FindBy(css = ".wds-community-bar__navigation")
  private WebElement navigationEntrypoint;

  @FindBy(css = ".wds-dropdown__content")
  private WebElement navigationToogleDropdown;

  @FindBy(css = ".wds-community-bar__level-1")
  private WebElement subMenu;

  @FindBy(css = ".wds-community-bar__level-1")
  private List<WebElement> subMenuLinks;

  @FindBy(css = ".wds-community-bar__navigation-back")
  private WebElement backButton;

  private boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      Log.info(e.getMessage());
      return false;
    }
  }

  public boolean isCommunityBarVisible() {
    wait.forElementVisible(communityBar);
    return isElementVisible(communityBar);
  }

  public boolean isCommunityNameVisible() {
    return isElementVisible(communityName);
  }

  public boolean isNavigationEntryPointVisible() {
    return isElementVisible(navigationEntrypoint);
  }

  public boolean isDiscussionsEntrypointVisible() {
    return isElementVisible(discussionsEntrypoint);
  }

  public LocalNavigationMobile clickCommunityName() {
    wait.forElementClickable(communityName);
    communityName.click();
    return this;
  }

  public LocalNavigationMobile clickDiscussionsEntrypoint() {
    wait.forElementClickable(discussionsEntrypoint);
    discussionsEntrypoint.click();
    return this;
  }

  public LocalNavigationMobile clickNavigationEntrypoint() {
    wait.forElementClickable(navigationEntrypoint);
    navigationEntrypoint.click();
    return this;
  }

  public boolean isSubMenuOpened() {
    wait.forElementVisible(subMenu);
    return isElementVisible(subMenu);
  }


  public LocalNavigationMobile clickBackButton() {
    Log.info("Go back to previous navigation level");
    wait.forElementClickable(backButton);
    backButton.click();

    return this;
  }

  public LocalNavigationMobile openSubMenu(int index) {
    Log.info("Open sub-menu no.: " + index);
    WebElement wikiMenuLink = subMenuLinks.get(index);
    wait.forElementClickable(wikiMenuLink);
    wikiMenuLink.click();

    return this;
  }




  public boolean isBackButtonVisible() {
    return isElementVisible(backButton);
  }



}
