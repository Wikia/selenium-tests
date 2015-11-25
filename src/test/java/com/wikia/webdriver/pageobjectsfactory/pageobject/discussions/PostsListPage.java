package com.wikia.webdriver.pageobjectsfactory.pageobject.discussions;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by qaga on 2015-11-25.
 */
public class PostsListPage {

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;

  private WebDriver driver;
  private UrlBuilder urlBuilder = new UrlBuilder();

  private static final String PATH = "/d/f/3035";

  public PostsListPage openURL() {
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + PATH);
    return this;
  }

  public boolean isPostListEmpty() {
    if
  }


}
