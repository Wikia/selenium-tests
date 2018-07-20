package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;

public class WikiArticleHomePage extends WikiBasePageObject {

  private By wikiHomePageSpecificElement = By.className("mainpage");

  /**
   * Check if current page is in fact home page of wiki
   */
  public void verifyThisIsWikiHomePage() {
    wait.forElementPresent(wikiHomePageSpecificElement);
  }
}
