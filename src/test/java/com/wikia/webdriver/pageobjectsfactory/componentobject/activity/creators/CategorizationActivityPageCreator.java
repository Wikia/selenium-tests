package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.CategorizationActivity;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CategorizationActivityPageCreator extends ActivityPageCreator{

  private static final String CATEGORIZATION_ACTIVITY_CSS_SELECTOR = "activity-type-categorization";

  public CategorizationActivityPageCreator() {
    super();
  }

  @Override
  public Activity createPage(WebDriver driver, WebElement parentElement) {
    return new CategorizationActivity(driver, parentElement);
  }

  @Override
  public boolean match(String activityCssSelector) {
    return activityCssSelector.contains(CATEGORIZATION_ACTIVITY_CSS_SELECTOR);
  }
}
