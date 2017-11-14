package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeaturedVideoComponentObject extends WikiBasePageObject{

  @FindBy(css = ".featured-video")
  private WebElement featuredVideo;

  public FeaturedVideoComponentObject openWikiArticle(String articleName) {
    getUrl(getWikiUrl() + URLsContent.WIKI_DIR + articleName);
    PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);

    return this;
  }

  public boolean isFeaturedVideo() {
    wait.forElementVisible(featuredVideo);
    return featuredVideo.isDisplayed(); }

}
