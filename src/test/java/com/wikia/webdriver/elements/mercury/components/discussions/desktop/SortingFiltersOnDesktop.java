package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.SortOption;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SortingFiltersOnDesktop extends BasePageObject {

  @FindBy(css = ".sort-latest")
  private WebElement latestFilter;

  @FindBy(css = ".sort-trending")
  private WebElement trendingFilter;

  public SortingFiltersOnDesktop chooseSortingOption(SortOption option) {
    if (option == SortOption.LATEST) {
      waitAndClick(latestFilter);
    } else if (option == SortOption.TRENDING) {
      waitAndClick(trendingFilter);
    } else {
      throw new IllegalArgumentException(String.format("Option %s not supported in sorting", option));
    }
    return this;
  }

}
