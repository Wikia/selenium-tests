package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * Author: Artur Dwornik Date: 02.04.13 Time: 17:31
 */
public class WikiArticleHomePage extends WikiBasePageObject {

  @FindBy(css = "#HOME_TOP_RIGHT_BOXAD")
  private WebElement wikiHomePageSpecificElement;

  /**
   * Check if current page is in fact home page of wiki
   */
  public void verifyThisIsWikiHomePage() {
    wait.forElementVisible(wikiHomePageSpecificElement);
  }
}
