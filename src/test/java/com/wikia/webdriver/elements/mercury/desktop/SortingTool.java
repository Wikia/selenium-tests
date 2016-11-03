package com.wikia.webdriver.elements.mercury.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SortingTool extends BasePageObject {

  @FindBy(css = ".sort-latest")
  private WebElement latestTabOnDesktop;

  @FindBy(css = ".sort-trending")
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
