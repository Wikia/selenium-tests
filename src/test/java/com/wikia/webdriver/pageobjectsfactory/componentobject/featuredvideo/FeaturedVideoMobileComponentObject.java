package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeaturedVideoMobileComponentObject extends WikiBasePageObject {

  private static final String AUTOPLAY_COOKIE = "featuredVideoAutoplay";

  @FindBy(css = ".jw-video")
  private WebElement featuredVideo;

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement settingsMenu;

  @FindBy(css = ".jw-title-primary")
  private WebElement primaryTitle;

  @FindBy(css = ".article-featured-video__on-scroll-video-wrapper")
  private WebElement player;

  @FindBy(css = ".article-featured-video__attribution-link")
  private WebElement attributionLink;

  @FindBy(css = ".article-featured-video__attribution-avatar")
  private WebElement attributionAvatar;

  public FeaturedVideoMobileComponentObject setAutoplayCookie(boolean autoplay) {
    driver.manage().addCookie(new Cookie(
        AUTOPLAY_COOKIE,
        autoplay ? "1" : "0",
        String.format(".%s", Configuration.getEnvType().getDomain(driver.getCurrentUrl())),
        null,
        null
    ));

    driver.navigate().refresh();

    return this;
  }

  public FeaturedVideoMobileComponentObject openWikiArticle(String articleName) {
    this.openWikiPage(getWikiUrl() + "/" + articleName + "?noads=1");

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

  public boolean isAttributionLinkVisible() {
    wait.forElementVisible(attributionLink);

    return attributionLink.isDisplayed();
  }

  public boolean isAttributionLinkNotVisible() {
    wait.forElementNotVisible(attributionLink);

    return true;
  }

  public boolean isAttributionAvatarVisible() {
    wait.forElementVisible(attributionAvatar);

    return attributionAvatar.isDisplayed();
  }
}
