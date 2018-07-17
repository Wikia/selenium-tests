package com.wikia.webdriver.pageobjectsfactory.componentobject.navigation.local;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LocalNavigationMobile extends WikiBasePageObject {

  @FindBy(css = ".wds-community-bar")
  private WebElement localNav;

  @FindBy(css = ".wds-community-bar__sitename")
  private WebElement communityName;

  @FindBy(css = ".wds-community-bar__discussions")
  private WebElement discussionsEntrypoint;

  @FindBy(css = ".wds-community-bar__navigation")
  private WebElement navigationEntrypoint;

  @FindBy(css = ".wds-dropdown__content")
  private WebElement navigationDropdown;


}
