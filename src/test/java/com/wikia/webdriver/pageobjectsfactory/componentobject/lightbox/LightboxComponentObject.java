package com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

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
  @FindBy(css = "span[data-pinned-title='Unpin top and bottom bars']")
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
  @FindBy(css = "a.stumbleupon")
  private WebElement stumbleUponShareLink;
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
  @FindBy(css = "span.carousel-arrow.next")
  private WebElement carouselRight;
  @FindBy(css = "span.carousel-arrow.previous:not(.disabled)")
  private WebElement carouselLeft;
  @FindBy(css = "span.carousel-arrow.previous.disabled")
  private WebElement carouselLeftDisabled;
  @FindBy(css = "button.more-info-close")
  private WebElement closeShareScreenButton;

  public LightboxComponentObject(WebDriver driver) {
    super();
  }

  public void verifyLightboxPopup() {
    wait.forElementVisible(lightBoxModal);
    PageObjectLogging.log("verifyLightboxPopup", "Lightbox appeared", true);
  }

  public void verifyLightboxVideo() {
    wait.forElementVisible(videoContainer);
    PageObjectLogging.log("verifyLightboxVideo", "Lightbox video appeared", true);
  }

  public void verifyLightboxImage() {
    wait.forElementVisible(imageContainer);
    PageObjectLogging.log("verifyLightboxImage", "Lightbox image appeared", true);
  }

  public LightboxComponentObject openLightbox() {
    wait.forElementVisible(imageThumbnail);
    scrollAndClick(imageThumbnail);
    PageObjectLogging.log("openLightbox", "opened ligthbox", true);
    return new LightboxComponentObject(driver);
  }

  public void makeHeaderVisible() {
    wait.forElementVisible(titleLink);
    jsActions.execute("$('.LightboxHeader').css('opacity', '1')");
  }

  public void clickCloseButton() {
    wait.forElementVisible(closeModalButton);
    scrollAndClick(closeModalButton);
    PageObjectLogging.log("clickCloseButton ", "lightbox closed", true);
  }

  public void clickPinButton() {
    builder.moveToElement(lightBoxModal).
        click(pinButton).
        build().
        perform();
    PageObjectLogging.log("clickPinButton", "pin button was clicked", true);
  }

  public void clickShareButton() {
    wait.forElementVisible(shareButton);
    shareButton.click();
    wait.forElementVisible(moreInfoThumbnail);
    PageObjectLogging.log("clickShareButton", "share button is clicked", true);
  }

  public void clickCloseShareScreenButton() {
    wait.forElementVisible(closeShareScreenButton);
    closeShareScreenButton.click();
    PageObjectLogging
        .log("clickCloseShareScreenButton", "close share screen button was clicked", true);
  }

  public void verifyShareScreenClosed() {
    waitForElementNotVisibleByElement(shareScreen);
    PageObjectLogging.log("verifyShareScreenClosed", "share screen is closed", true);
  }

  public void verifyLightboxClosed() {
    waitForElementNotVisibleByElement(lightBoxModal);
    PageObjectLogging.log("verifyShareScreenClosed", "share lightbox is closed", true);
  }

  public void verifyShareButtons() {
    wait.forElementVisible(plusoneShareLink);
    wait.forElementVisible(redditShareLink);
    wait.forElementVisible(stumbleUponShareLink);
    wait.forElementVisible(twitterShareLink);
    wait.forElementVisible(facebookShareLink);
    PageObjectLogging.log("verifyShareButtons", "all share buttons are visible", true);
  }

  public void clickFacebookShareButton() {
    facebookShareLink.click();
    PageObjectLogging.log("clickFacebookShareButton", "fb share button is clicked", true);
  }

  public void clickTwitterShareButton() {
    twitterShareLink.click();
    PageObjectLogging.log("clickTwitterShareButton", "twitter share button is clicked", true);
  }

  public void clickStumbleUponShareButton() {
    stumbleUponShareLink.click();
    PageObjectLogging
        .log("clickStumbleUponShareButton", "stumbleupon share button is clicked", true);
  }

  public void clickRedditShareButton() {
    redditShareLink.click();
    PageObjectLogging.log("clickRedditShareButton", "reddit share button is clicked", true);
  }

  public void clickPlusOneShareButton() {
    plusoneShareLink.click();
    PageObjectLogging.log("clickPlusOneShareButton", "plus one share button is clicked", true);
  }

  public void verifyTitleUrl(String expectedUrl) {
    String titleUrl = titleLink.getAttribute("href");
    Assertion.assertEquals(titleUrl, expectedUrl);
    PageObjectLogging.log("verifyTitleUrl", "Title URL is correct", true);
  }

  public FilePagePageObject clickTitle() {
    new Actions(driver).moveToElement(titleLink).perform();
    wait.forElementVisible(titleLink);
    titleLink.click();
    PageObjectLogging.log("clickTitleUrl", "Title url is clicked", true);
    return new FilePagePageObject();
  }

  public void verifyMoreInfoUrl(String expectedUrl) {
    String moreInfoUrl = moreInfoLink.getAttribute("href");
    Assertion.assertEquals(moreInfoUrl, expectedUrl);
    PageObjectLogging.log("verifyMoreInfoUrl", "More Info URL is correct", true);
  }

  public void clickCarouselRight() {
    wait.forElementVisible(carouselRight);
    carouselRight.click();
    PageObjectLogging.log("clickCarouselRight", "carousel right button is clicked", true);
  }

  public void clickCarouselLeft() {
    wait.forElementVisible(carouselLeft);
    carouselLeft.click();
    PageObjectLogging.log("clickCarouselLeft", "carousel left button is clicked", true);
  }

  public void verifyCarouselLeftDisabled() {
    wait.forElementVisible(carouselLeftDisabled);
    PageObjectLogging.log("verifyCarouselLeftDisabled", "carousel left button is disabled", true);
  }

  public void verifyVideoAutoplay(String providerName) {
    VideoComponentObject video = new VideoComponentObject(driver, videoContainer);
    video.verifyVideoAutoplay(providerName, true);
  }

  public VideoComponentObject getVideoPlayer() {
    return new VideoComponentObject(driver, videoContainer, VIDEO_WIDTH_LIGHTBOX);
  }

}
