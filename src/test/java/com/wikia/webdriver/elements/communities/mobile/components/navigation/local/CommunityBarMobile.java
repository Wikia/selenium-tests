package com.wikia.webdriver.elements.communities.mobile.components.navigation.local;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class CommunityBarMobile extends WikiBasePageObject {

  @FindBy(css = ".wds-community-bar")
  private WebElement communityBar;

  @FindBy(css = ".wds-community-bar .wds-community-bar__sitename")
  private WebElement communityName;

  @FindBy(css = ".wds-community-bar__discussions")
  private WebElement discussionsEntrypoint;

  @FindBy(css = ".wds-community-bar__navigation")
  private WebElement navigationEntrypoint;

  @FindBy(css = ".wds-dropdown__content")
  private WebElement navigationToogleDropdown;

  @FindBy(css = ".wds-community-bar__level-1 ")
  private WebElement subMenu;

  @FindBy(css = ".wds-community-bar__level-1 > li > a")
  private List<WebElement> firstLevelMenuLinks
      ;

  @FindBy(css = ".wds-dropdown__content .wds-community-bar__navigation-back")
  private WebElement backButton;

  @FindBy(css = ".wds-community-bar__navigation-header")
  private WebElement secondLevelMenuHeader;


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

  public CommunityBarMobile clickCommunityName() {
    wait.forElementClickable(communityName);
    communityName.click();
    return this;
  }

  public CommunityBarMobile clickDiscussionsEntrypoint() {
    wait.forElementClickable(discussionsEntrypoint);
    discussionsEntrypoint.click();
    return this;
  }

  public CommunityBarMobile clickNavigationEntrypoint() {
    wait.forElementClickable(navigationEntrypoint);
    navigationEntrypoint.click();
    return this;
  }

  public boolean isSubMenuOpened() {
    wait.forElementVisible(subMenu);
    return isElementVisible(subMenu);
  }

  public boolean isSubMenuClosed() {
    wait.forElementNotVisible(subMenu);
    return true;
  }
  public CommunityBarMobile clickBackButton() {
    Log.info("Go back to previous navigation level");
    wait.forElementClickable(backButton);
    backButton.click();

    return this;
  }

  public CommunityBarMobile open1stlevelMenuElement(int index) {
    Log.info("Open sub-menu no.: " + index);
    WebElement menuElement = firstLevelMenuLinks.get(index);
    wait.forElementClickable(menuElement);
    menuElement.click();

    return this;
  }

  public boolean isBackButtonVisible() {
    return isElementVisible(backButton);
  }

  public boolean is2ndLevelMenuHeaderVisible() {
    return isElementVisible(secondLevelMenuHeader);
  }


}
