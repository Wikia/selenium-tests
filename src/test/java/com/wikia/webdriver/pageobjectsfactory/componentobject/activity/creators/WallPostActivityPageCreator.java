package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.WallPostActivity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by szymonczerwinski on 20.03.2017.
 */
public class WallPostActivityPageCreator extends ActivityPageCreator{

  public static final String WALL_POST_ACTIVITY_CSS_SELECTOR = "activity-type-talk";

  public WallPostActivityPageCreator() {
    super();
  }

  @Override
  public Activity createPage(WebDriver driver, WebElement parentElement) {
    return new WallPostActivity(driver, parentElement);
  }

  @Override
  public boolean match(String activityCssSelector) {
    return activityCssSelector.contains(WALL_POST_ACTIVITY_CSS_SELECTOR);
  }
}
