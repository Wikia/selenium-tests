package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeaturedVideoComponentObject extends WikiBasePageObject {

  private static final String AUTOPLAY_COOKIE = "featuredVideoAutoplay";

  @FindBy(css = ".featured-video")
  private WebElement featuredVideo;

  @FindBy(css = ".jw-title-primary")
  private WebElement primaryTitle;

  @FindBy(css = ".jw-title-secondary")
  private WebElement secondaryTitle;

  @FindBy(css = "#featured-video__player")
  private WebElement playArea;

  public FeaturedVideoComponentObject setAutoplayCookie(boolean autoplay) {
    driver.manage().addCookie(new Cookie(
        AUTOPLAY_COOKIE,
        autoplay ? "1" : "0",
        String.format(".%s", Configuration.getEnvType().getWikiaDomain()),
        null,
        null
    ));

    return this;
  }

  public FeaturedVideoComponentObject openWikiArticle(String articleName) {
    this.openWikiPage(getWikiUrl() + URLsContent.WIKI_DIR + articleName);

    return this;
  }

  public boolean isFeaturedVideo() {
    wait.forElementVisible(featuredVideo);
    return featuredVideo.isDisplayed();
  }

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
