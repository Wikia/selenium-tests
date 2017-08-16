package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.EditActivity;


public class EditActivityPageCreator implements ActivityPageCreator {

  private static final String EDIT_ACTIVITY_CSS_SELECTOR = "activity-type-edit";

  @Override
  public Activity createPage() {
    return new EditActivity();
  }

  @Override
  public boolean match(String activityCssSelector) {
    return activityCssSelector.contains(EDIT_ACTIVITY_CSS_SELECTOR);
  }
}
