package com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LightboxComponentObject extends WikiBasePageObject {

  private static final Integer VIDEO_WIDTH_LIGHTBOX = 737;

  @FindBy(css = ".LightboxHeader button.close.wikia-chiclet-button")
  protected WebElement closeModalButton;
  @FindBy(css = ".thumb.thumbinner")
  private WebElement imageThumbnail;
  @FindBy(css = "#LightboxModal")
  private WebElement lightBoxModal;
  @FindBy(css = "span.pin")
  private WebElement pinButton;
  @FindBy(css = ".WikiaLightbox .share")
  private WebElement shareScreen;
  @FindBy(css = ".WikiaLightbox a.share-button.secondary")
  private WebElement shareButton;
  @FindBy(css = "div.hero-inner img")
  private WebElement moreInfoThumbnail;
  @FindBy(css = "a.facebook")
  private WebElement facebookShareLink;
  @FindBy(css = "a.twitter")
  private WebElement twitterShareLink;
  @FindBy(css = "a.reddit")
  private WebElement redditShareLink;
  @FindBy(css = "a.plusone")
  private WebElement plusoneShareLink;
  @FindBy(css = ".video-media")
  private WebElement videoContainer;
  @FindBy(css = ".LightboxHeader h1 a")
  private WebElement titleLink;
  @FindBy(css = ".more-info-button")
  private WebElement moreInfoLink;
  @FindBy(css = ".WikiaLightbox div.media img")
  private WebElement imageContainer;
  @FindBy(css = "span.arrow.next")
  private WebElement carouselRight;
  @FindBy(css = "span.arrow.previous:not(.disabled)")
  private WebElement carouselLeft;
  @FindBy(css = "button.more-info-close")
  private WebElement closeShareScreenButton;

  private By carouselLeftDisabled = new By.ByCssSelector("span.arrow.previous.disabled");

  public LightboxComponentObject(WebDriver driver) {
    super();
  }

  public void verifyLightboxPopup() {
    wait.forElementVisible(lightBoxModal);
    Log.log("verifyLightboxPopup", "Lightbox appeared", true);
  }

  public void verifyLightboxVideo() {
    wait.forElementVisible(videoContainer);
    Log.log("verifyLightboxVideo", "Lightbox video appeared", true);
  }

  public void verifyLightboxImage() {
    wait.forElementVisible(imageContainer);
    Log.log("verifyLightboxImage", "Lightbox image appeared", true);
  }

  public LightboxComponentObject openLightbox() {
    wait.forElementVisible(imageThumbnail);
    scrollAndClick(imageThumbnail);
    Log.log("openLightbox", "opened ligthbox", true);
    return new LightboxComponentObject(driver);
  }

  public void makeHeaderVisible() {
    wait.forElementVisible(titleLink);
    jsActions.execute("$('.LightboxHeader').css('opacity', '1')");
  }

  public void clickCloseButton() {
    wait.forElementVisible(closeModalButton);
    scrollAndClick(closeModalButton);
    Log.log("clickCloseButton ", "lightbox closed", true);
  }

  public void clickPinButton() {
    builder.moveToElement(lightBoxModal).
        click(pinButton).
        build().
        perform();
    Log.log("clickPinButton", "pin button was clicked", true);
  }

  public void clickShareButton() {
    wait.forElementVisible(shareButton);
    shareButton.click();
    wait.forElementVisible(moreInfoThumbnail);
    Log.log("clickShareButton", "share button is clicked", true);
  }

  public void clickCloseShareScreenButton() {
    wait.forElementVisible(closeShareScreenButton);
    closeShareScreenButton.click();
    Log.log("clickCloseShareScreenButton", "close share screen button was clicked", true);
  }

  public void verifyShareScreenClosed() {
    waitForElementNotVisibleByElement(shareScreen);
    Log.log("verifyShareScreenClosed", "share screen is closed", true);
  }

  public void verifyLightboxClosed() {
    waitForElementNotVisibleByElement(lightBoxModal);
    Log.log("verifyShareScreenClosed", "share lightbox is closed", true);
  }

  public void verifyShareButtons() {
    wait.forElementVisible(plusoneShareLink);
    wait.forElementVisible(redditShareLink);
    wait.forElementVisible(twitterShareLink);
    wait.forElementVisible(facebookShareLink);
    Log.log("verifyShareButtons", "all share buttons are visible", true);
  }

  public void clickFacebookShareButton() {
    facebookShareLink.click();
    Log.log("clickFacebookShareButton", "fb share button is clicked", true);
  }

  public void clickTwitterShareButton() {
    twitterShareLink.click();
    Log.log("clickTwitterShareButton", "twitter share button is clicked", true);
  }

  public void clickRedditShareButton() {
    redditShareLink.click();
    Log.log("clickRedditShareButton", "reddit share button is clicked", true);
  }

  public void clickPlusOneShareButton() {
    plusoneShareLink.click();
    Log.log("clickPlusOneShareButton", "plus one share button is clicked", true);
  }

  public FilePage clickTitle() {
    new Actions(driver).moveToElement(titleLink).perform();
    wait.forElementVisible(titleLink);
    titleLink.click();
    Log.log("clickTitleUrl", "Title url is clicked", true);
    return new FilePage();
  }

  public void clickCarouselRight() {
    wait.forElementVisible(carouselRight);
    carouselRight.click();
    Log.log("clickCarouselRight", "carousel right button is clicked", true);
  }

  public void clickCarouselLeft() {
    wait.forElementVisible(carouselLeft);
    carouselLeft.click();
    Log.log("clickCarouselLeft", "carousel left button is clicked", true);
  }

  public void verifyCarouselLeftDisabled() {
    wait.forElementPresent(carouselLeftDisabled);
    Log.log("verifyCarouselLeftDisabled", "carousel left button is disabled", true);
  }

  public void verifyVideoAutoplay(String providerName) {
    VideoComponentObject video = new VideoComponentObject(driver, videoContainer);
    video.verifyVideoAutoplay(providerName, true);
  }

  public VideoComponentObject getVideoPlayer() {
    return new VideoComponentObject(driver, videoContainer, VIDEO_WIDTH_LIGHTBOX);
  }
}
