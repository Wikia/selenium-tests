package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.discussions;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;

/**
 * Created by qaga on 2015-12-01.
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
