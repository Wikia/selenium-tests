package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.CategorizationActivity;


public class CategorizationActivityPageCreator implements ActivityPageCreator{

  private static final String CATEGORIZATION_ACTIVITY_CSS_SELECTOR = "activity-type-categorization";

  @Override
  public Activity createPage() {
    return new CategorizationActivity();
  }

  @Override
  public boolean match(String activityCssSelector) {
    return activityCssSelector.contains(CATEGORIZATION_ACTIVITY_CSS_SELECTOR);
  }
}
