package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.WallPostActivity;


public class WallPostActivityPageCreator implements ActivityPageCreator{

  private static final String WALL_POST_ACTIVITY_CSS_SELECTOR = "activity-type-talk";

  @Override
  public Activity createPage() {
    return new WallPostActivity();
  }

  @Override
  public boolean match(String activityCssSelector) {
    return activityCssSelector.contains(WALL_POST_ACTIVITY_CSS_SELECTOR);
  }
}
