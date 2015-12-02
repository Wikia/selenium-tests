package com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.mobile;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;

/**
 * @ownership Social Wikia.
 */
class PostDetailsPage extends WikiBasePageObject{

  private static final String PATH = "d/p/%s";
  private static final String DEFAULT_ID = "2621148372316194193";

  public PostDetailsPage(WebDriver driver) {
    super(driver);
  }

  public PostDetailsPage openPost(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki().replace("/wiki", "") + String.format(PATH, wikiID));
    return this;
  }
}
