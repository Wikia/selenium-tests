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

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement settingsMenu;

  @FindBy(css = ".jw-title-primary")
  private WebElement primaryTitle;

  @FindBy(css = ".jw-title-secondary")
  private WebElement secondaryTitle;

  @FindBy(css = "#featured-video__player")
  private WebElement player;

  @FindBy(css = ".jw-controlbar")
  private WebElement controlbar;

  @FindBy(css = ".jw-svg-icon-play")
  private WebElement controlbarPlayIcon;

  @FindBy(css = ".jw-svg-icon-pause")
  private WebElement controlbarPauseIcon;

  @FindBy(css = ".video-feedback")
  private WebElement videoFeedback;

  @FindBy(css = "#featured-video__player-videoAutoplayToggle")
  private WebElement autoplayToggle;

  public FeaturedVideoComponentObject() {
  }

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
    this.openWikiPage(getWikiUrl() + URLsContent.WIKI_DIR + articleName + "?noads=1");

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

    return secondaryTitle.getText();
  }

  public FeaturedVideoComponentObject clickPlay() {
    wait.forElementClickable(player);
    player.click();

    return this;
  }

  public FeaturedVideoComponentObject clickPause() {
    wait.forElementClickable(player);
    player.click();

    return this;
  }

  public boolean isVideoPlaying() {
    wait.forElementVisible(videoFeedback);

    return videoFeedback.isDisplayed();
  }

  public boolean isVideoPaused() {
    wait.forElementNotVisible(videoFeedback);

    return videoFeedback.isDisplayed();
  }

  public boolean isAutoplayOn() {
    return "true".equals(autoplayToggle.getAttribute("checked"));
  }

  public FeaturedVideoComponentObject showControlBar() {
    jsActions.mouseOver(player);
    wait.forElementClickable(controlbar);

    return this;
  }

  public FeaturedVideoComponentObject openSettingsMenu() {
    showControlBar();
    wait.forElementClickable(settingsMenu)
        .click();

    return this;
  }
}

