package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContentRecommendationsMobile extends BasePageObject {

  @FindBy(css = ".wds-content-recommendations")
  private WebElement trendingArticlesModule;

  public boolean isTrendingArticlesModuleVisible() {
    return isVisible(trendingArticlesModule);
  }

  public boolean isTrendingArticlesModuleNotVisible() {
    wait.forElementNotVisible(trendingArticlesModule);
    return true;
  }

}
