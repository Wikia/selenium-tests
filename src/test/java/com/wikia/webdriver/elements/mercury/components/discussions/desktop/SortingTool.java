package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SortingTool extends BasePageObject {

  @FindBy(xpath = "//li[text()='Latest']")
  private WebElement latestTabOnDesktop;

  @FindBy(xpath = "//li[text()='Trending']")
  private WebElement trendingTabOnDesktop;

  public SortingTool clickLatestTabOnDesktop() {
    latestTabOnDesktop.click();
    return this;
  }

  public SortingTool clickTrendingTabOnDesktop() {
    trendingTabOnDesktop.click();
    return this;
  }
}
