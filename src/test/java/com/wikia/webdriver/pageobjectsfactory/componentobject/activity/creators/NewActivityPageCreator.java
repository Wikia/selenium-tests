package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.NewActivity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class NewActivityPageCreator extends ActivityPageCreator {

  private static final String NEW_ACTIVITY_CSS_SELECTOR = "activity-type-new";

  public NewActivityPageCreator() {
    super();
  }

  @Override
  public Activity createPage(WebDriver driver, WebElement parentElement) {
    return new NewActivity(driver, parentElement);
  }

  @Override
  public boolean match(String activityCssSelector) {
    return activityCssSelector.contains(NEW_ACTIVITY_CSS_SELECTOR);
  }
}
