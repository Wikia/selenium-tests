package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Social Wikia
 */
public class BackButtons {

  @FindBy(css = ".back-button")
  private WebElement backToWiki;

  public BackButtons clickBackToWikiLink() {
    backToWiki.click();
    return this;
  }

}
