package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.NewPageActivity;


public class NewPageActivityPageCreator implements ActivityPageCreator {

  private static final String NEW_PAGE_ACTIVITY_CSS_SELECTOR = "activity-type-new";

  @Override
  public Activity createPage() {
    return new NewPageActivity();
  }

  @Override
  public boolean match(String activityCssSelector) {
    return activityCssSelector.contains(NEW_PAGE_ACTIVITY_CSS_SELECTOR);
  }
}
