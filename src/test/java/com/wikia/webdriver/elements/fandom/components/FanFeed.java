package com.wikia.webdriver.elements.fandom.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class FanFeed extends BasePageObject {

  @FindBy(css = "section.fan-feed")
  private WebElement fanFeed;

  public boolean isFanFeedDisplayed() {
    try {
      return fanFeed.isDisplayed();
    } catch (ElementNotFoundException e) {
      return false;
    }
  }
}
