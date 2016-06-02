package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Social Wikia
 */
public class SortingTool extends WikiBasePageObject{

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
