package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeaturedVideoComponentObject extends WikiBasePageObject{

  @FindBy(css = ".featured-video")
  private WebElement featuredVideo;

  @FindBy(css = ".jw-title-primary")
  private WebElement primaryTitle;

  @FindBy(css = ".jw-title-secondary")
  private WebElement secondaryTitle;

  @FindBy(css = "#featured-video__player")
  private WebElement playArea;


  public FeaturedVideoComponentObject openWikiArticle(String articleName) {
    getUrl(getWikiUrl() + URLsContent.WIKI_DIR + articleName);
    PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);

    return this;
  }

  public boolean isFeaturedVideo() {
    wait.forElementVisible(featuredVideo);
    return featuredVideo.isDisplayed(); }

  public String getTitle() {
    wait.forElementVisible(primaryTitle);
    return primaryTitle.getText();
  }

  public String getSubtitle() {
    wait.forElementVisible(secondaryTitle);
    return secondaryTitle.getTagName();
  }

  public void clickPlay() {
    wait.forElementVisible(playArea);
    playArea.click();
  }



}
