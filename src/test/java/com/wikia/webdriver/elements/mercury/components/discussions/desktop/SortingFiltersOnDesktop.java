package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SortingFiltersOnDesktop extends BasePageObject {

  @FindBy(css = ".sort-latest")
  private WebElement latestFilter;

  @FindBy(css = ".sort-trending")
  private WebElement trendingFilter;

  public SortingFiltersOnDesktop clickLatestOption() {
    latestFilter.click();
    return this;
  }

  public SortingFiltersOnDesktop clickTrendingOption() {
    trendingFilter.click();
    return this;
  }
}
