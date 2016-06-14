package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BackButtons extends BasePageObject {

  @FindBy(css = ".back-button")
  private WebElement backToWiki;

  public BackButtons clickBackToWikiLink() {
    backToWiki.click();
    return this;
  }
}
