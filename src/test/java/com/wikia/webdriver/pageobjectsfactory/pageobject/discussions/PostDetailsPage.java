package com.wikia.webdriver.pageobjectsfactory.pageobject.discussions;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PostDetailsPage extends WikiBasePageObject{

  @FindBy(css = ".replies-list")
  private List<WebElement> repliesList;

  private static final String PATH = "d/p/%s";
  private static final String DEFAULT_POST_ID = "2621148372316194193";

  public PostDetailsPage(WebDriver driver) {
    super(driver);
  }

  public PostDetailsPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki().replace("/wiki", "") + String.format(PATH, wikiID));
    return this;
  }

  public PostDetailsPage open() {
    return open(DEFAULT_POST_ID);
  }

  public boolean isPostDetailsListEmpty() {
    return repliesList.isEmpty();
  }
}
