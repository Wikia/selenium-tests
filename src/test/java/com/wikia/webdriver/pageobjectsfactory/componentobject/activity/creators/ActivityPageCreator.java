package com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public abstract class ActivityPageCreator {

  public abstract Activity createPage(WebDriver driver, WebElement parentWebElement);

  public abstract boolean match(String activityCssSelector);
}
