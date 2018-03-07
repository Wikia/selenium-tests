package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FeaturedVideoMobileComponentObject extends WikiBasePageObject{

  private static final String AUTOPLAY_COOKIE = "featuredVideoAutoplay";

  @FindBy(css = ".jw-video")
  private WebElement featuredVideo;

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement settingsMenu;

  @FindBy(css = ".jw-title-primary")
  private WebElement primaryTitle;

  @FindBy(css = ".article-featured-video__on-scroll-video-wrapper")
  private WebElement player;

  @FindBy(css = ".jw-controlbar")
  private WebElement controlbar;

  @FindBy(css = "*[aria-label='Start playback']")
  private WebElement playButton;

  @FindBy(css = "*[aria-label='Pause']")
  private WebElement pauseButton;

  @FindBy(css = ".jw-state-playing")
  private WebElement playerStatePlaying;

  @FindBy(css = ".jw-state-paused")
  private WebElement playerStatePaused;

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement videoQualityButton;

  @FindBy(css = ".wds-toggle__label")
  private WebElement autoplayToggle;

  @FindBy(css = ".jw-icon-tooltip.jw-icon-volume.jw-off")
  private WebElement volumeMuted;

  @FindBy(css = ".wikia-jw-settings__list .wikia-jw-settings__captions-button")
  private WebElement videoCaptionsButton;

  @FindBy(css = ".wikia-jw-settings__submenu")
  private List<WebElement> videoSettingsSubmenu;

  public FeaturedVideoMobileComponentObject setAutoplayCookie(boolean autoplay) {
    driver.manage().addCookie(new Cookie(
        AUTOPLAY_COOKIE,
        autoplay ? "1" : "0",
        String.format(".%s", Configuration.getEnvType().getWikiaDomain()),
        null,
        null
    ));

    driver.navigate().refresh();

    return this;
  }

  public FeaturedVideoMobileComponentObject openWikiArticle(String articleName) {
    this.openWikiPage(getWikiUrl() + articleName + "?noads=1");

    return this;
  }

  public boolean isFeaturedVideoDisplayed() {
    wait.forElementVisible(featuredVideo);

    return featuredVideo.isDisplayed();
  }

  public String getTitle() {
    wait.forElementVisible(primaryTitle);

    return primaryTitle.getText();
  }

}
