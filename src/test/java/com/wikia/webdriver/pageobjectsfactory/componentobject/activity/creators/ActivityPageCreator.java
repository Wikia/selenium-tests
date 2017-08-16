package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;


public interface ActivityPageCreator {

  Activity createPage();
  boolean match(String activityCssSelector);

}
