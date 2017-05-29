package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.EditActivity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by szymonczerwinski on 20.03.2017.
 */
public class CategorizationActivityPageCreator extends ActivityPageCreator{

  public static final String CATEGORIZATION_ACTIVITY_CSS_SELECTOR = "activity-type-categorization";

  public CategorizationActivityPageCreator() {
    super();
  }

  @Override
  public Activity createPage(WebDriver driver, WebElement parentElement) {
    return new EditActivity(driver, parentElement);
  }

  @Override
  public boolean match(String activityCssSelector) {
    return activityCssSelector.contains(CATEGORIZATION_ACTIVITY_CSS_SELECTOR);
  }
}
