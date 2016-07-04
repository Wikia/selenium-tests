package com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LatestVideoAdminPageObject extends WikiBasePageObject {

  private static final String FEATURE_TAB_CSS = ".left-menu-tabs a[title=Featured]";
  @FindBy(css = FEATURE_TAB_CSS)
  private WebElement featuredTab;

  public LatestVideoAdminPageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public FeaturedVideoAdminPageObject clickFeaturedTab(WebDriver driver) {
    wait.forElementVisible(featuredTab);
    scrollAndClick(featuredTab);
    return new FeaturedVideoAdminPageObject(driver);
  }
}
